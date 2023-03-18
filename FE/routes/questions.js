import { Router } from 'express';

const router = Router();

router.get('/*', (req, res, next) => {
    if (!req.session.user) {
        res.redirect('/login');
    } else {
        next(); // pass control to the next handler (mint a router.get vagy router.post)
    }
});

router.get('/:id', (req, res) => {
    const id = req.params.id;
    let question = {};
    let answers = [];
    fetch('http://localhost:8080/questions/' + id, {
        method: 'GET'
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Server error');
        }
    })
    .then(data => {
        console.log(data);
        let tags = '';
        for (let i = 0; i < data.tags.length; i++) {
            tags += data.tags[i].name + ';';
        }
        tags = tags.slice(0, -1);
        question = data;
        answers = data.answers;
        res.type('.html');
        res.render('answers', {question, answers, tags, username: req.session.user.userName});
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});
            
router.get('/update/:id', (req, res) => {
    const id = req.params.id;
    fetch('http://localhost:8080/questions/' + id, {
        method: 'GET'
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Server error');
        }
    })
    .then(data => {
        if(req.session.user.userName === data.author.userName) {
            let tags = "";
            for (let i = 0; i < data.tags.length; i++) {
                tags += data.tags[i].name + ";";
            }
            tags = tags.slice(0, -1);
            res.type('.html');
            res.render('updateQuestion', {question: data, tags});
        } else {
            throw new Error('You are not the author of this question');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

router.post('/filter', (req, res) => {
    const ftype = req.fields.filtertype;
    const fvalue = req.fields.filtervalue;

    if (fvalue === '' && ftype != 'own') {
        res.redirect('/questions');
    }

    let link = '';
    switch (ftype) {
        case "tag":
            link = 'http://localhost:8080/questions/filter/tag/' + fvalue;
            break;
        case "keyword":
            link = 'http://localhost:8080/questions/filter/title/' + fvalue;
            break;
        case "username":
            link = 'http://localhost:8080/questions/filter/author/' + fvalue;
            break;
        case "own":
            link = 'http://localhost:8080/questions/filter/author/' + req.session.user.userName;
            break;
        default:
            res.send('Not implemented');
    }

    fetch(link, {
        method: 'GET'
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Server error');
        }
    })
    .then(data => {
        let upVotes = 0;
        let downVotes = 0;
        for (let i=0;i<data.length; i++) {
            upVotes = 0;
            downVotes = 0;
            for (let j=0;j<data[i].questionVotes.length; j++) {
                if (data[i].questionVotes[j].voteType === "up") {
                    upVotes++;
                } else {
                    downVotes++;
                }
            }
            data[i].upVotes = upVotes;
            data[i].downVotes = downVotes;
        }

        res.type('.html');
        res.render('questions', {questions: data, username: req.session.user.userName, ftype, fvalue});
    })
    .catch((error) => {
        console.error('Error:', error);
        res.send('Error');
    });
});

router.get('/', (req, res) => {
    let questions = [];
    fetch('http://localhost:8080/questions', {
        method: 'GET'
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Server error');
        }
    })
    .then(data => {
        let upVotes = 0;
        let downVotes = 0;
        for (let i=0;i<data.length; i++) {
            upVotes = 0;
            downVotes = 0;
            for (let j=0;j<data[i].questionVotes.length; j++) {
                if (data[i].questionVotes[j].voteType === "up") {
                    upVotes++;
                } else {
                    downVotes++;
                }
            }
            data[i].upVotes = upVotes;
            data[i].downVotes = downVotes;
        }
        questions = data;
        res.type('.html');
        res.render('questions', {questions, username: req.session.user.userName, ftype: null, fvalue: null});
    })
    .catch((error) => {
        console.error('Error:', error);
        res.send('Error');
    });
});

router.post('/upvote/:id', (req, res) => {
    const id = req.params.id;
    const user = req.session.user.userName;

    // fetch the question from the server, check if the current session user has already voted, if yes then throw and error, else add the vote
    fetch('http://localhost:8080/questions/' + id, {
        method: 'GET'
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Server error');
        }
    })
    .then(data => {
        let alreadyVoted = false;
        for (let i=0;i<data.questionVotes.length; i++) {
            if (data.questionVotes[i].user.userName === user) {
                alreadyVoted = true;
            }
        }
        if (alreadyVoted) {
            throw new Error('You have already voted');
        } else {
            const vote = {
                question: id,
                author: user,
                voteType: "up"
            }
            fetch('http://localhost:8080/questionvotes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vote)
            })
            .then(response => {
                if (response.status === 200) {
                    res.redirect('/questions');
                } else {
                    throw new Error('Server error');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                res.send('Error');
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.send('Error');
    });
});

router.post('/downvote/:id', (req, res) => {
    const id = req.params.id;
    const user = req.session.user.userName;

    // fetch the question from the server, check if the current session user has already voted, if yes then throw and error, else add the vote
    fetch('http://localhost:8080/questions/' + id, {
        method: 'GET'
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('Server error');
        }
    })
    .then(data => {
        let alreadyVoted = false;
        for (let i=0;i<data.questionVotes.length; i++) {
            if (data.questionVotes[i].user.userName === user) {
                alreadyVoted = true;
            }
        }
        if (alreadyVoted) {
            throw new Error('You have already voted');
        } else {
            const vote = {
                question: id,
                author: user,
                voteType: "down"
            }
            fetch('http://localhost:8080/questionvotes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vote)
            })
            .then(response => {
                if (response.status === 200) {
                    res.redirect('/questions');
                } else {
                    throw new Error('Server error');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                res.send('Error');
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.send('Error');
    });
});
            
router.post('/update/:id', (req, res) => {
    const id = req.params.id;
    let tags = req.fields.tags;
    if (tags[tags.length - 1] === ';') {
        tags = tags.slice(0, -1);
    }
    tags = tags.split(';');
    const question = {
        title: req.fields.title,
        author: req.session.user.userName,
        content: req.fields.content,
        date: new Date(),
        picture: "ioasudbcia",
        tags
    }
    fetch('http://localhost:8080/questions/' + id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(question)
    })
    .then(response => {
        if (response.status === 200) {
            res.redirect('/questions');
        } else {
            throw new Error('Server error');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

router.post('/delete/:id', (req, res) => {
    const id = req.params.id;
    fetch('http://localhost:8080/questions/' + id, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.status === 200) {
            res.redirect('/questions');
        } else {
            throw new Error('Server error');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

router.post('/', (req, res) => {
    const title = req.fields.title;
    const content = req.fields.content;
    let tags = req.fields.tags;
    if (tags[tags.length - 1] === ';') {
        tags = tags.slice(0, -1);
    }
    tags = tags.split(';');
    const picture = "ioasudbcia";
    const author = req.session.user.userName;
    const date = new Date();

    const question = {
        title,
        author,
        content,
        date,
        picture,
        tags
    };
    fetch('http://localhost:8080/questions', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(question)
    })
    .then(response => {
        if (response.status === 200) {
            res.redirect('/questions');
        } else {
            throw new Error('Server error');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

export default router;