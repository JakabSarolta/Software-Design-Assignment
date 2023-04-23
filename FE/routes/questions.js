import { Router } from 'express';
import path from 'path';

const router = Router();

router.use('/*', (req, res, next) => {
    if (req.session.user) {
        next();
    } else {
        res.redirect('/login');
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
        // Convert tags to string
        let tags = '';
        for (let i = 0; i < data.tags.length; i++) {
            tags += data.tags[i].name + ';';
        }
        tags = tags.slice(0, -1);

        // Get upvotes and downvotes and add it to the data object
        let upvotes = 0;
        let downvotes = 0;
        let alreadyVoted = false;
        for (let i = 0; i < data.answers.length; i++) {
            upvotes = 0;
            downvotes = 0;
            alreadyVoted = false;
            for (let j = 0; j < data.answers[i].answerVotes.length; j++) {
                if (data.answers[i].answerVotes[j].author.userName === req.session.user.userName) {
                    alreadyVoted = true;
                }
                if (data.answers[i].answerVotes[j].voteType === "up") {
                    upvotes += 1;
                } else {
                    downvotes += 1;
                }
            }
            data.answers[i].upvotes = upvotes;
            data.answers[i].downvotes = downvotes;
            data.answers[i].alreadyVoted = alreadyVoted;
            data.answers[i].voteCount = upvotes - downvotes;
        }

        // Calculate votecount of question and check if session user already voted
        upvotes = 0;
        downvotes = 0;
        alreadyVoted = false;
        for (let i = 0; i < data.questionVotes.length; i++) {
            if (data.questionVotes[i].author.userName === req.session.user.userName) {
                alreadyVoted = true;
            }
            if (data.questionVotes[i].voteType === "up") {
                upvotes += 1;
            } else {
                downvotes += 1;
            }
        }
        data.upvotes = upvotes;
        data.downvotes = downvotes;
        data.alreadyVoted = alreadyVoted;
        data.voteCount = upvotes - downvotes;

        data.answers.sort((a, b) => b.voteCount - a.voteCount);
        question = data;
        console.log(data.author);
        answers = data.answers;
        res.type('.html');
        res.render('answers', {question, role: req.session.user.role, answers, tags, username: req.session.user.userName, ftype: null, fvalue: null});
    })
    .catch((error) => {
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

router.get('/tag/:tag', (req, res) => {
    const tag = req.params.tag;

    fetch('http://localhost:8080/questions/filter/tag/' + tag, {
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
        let alreadyVoted = false;
        for (let i=0;i<data.length; i++) {
            upVotes = 0;
            downVotes = 0;
            alreadyVoted = false;
            for (let j=0;j<data[i].questionVotes.length; j++) {
                if (data[i].questionVotes[j].author.userName === req.session.user.userName) {
                    alreadyVoted = true;
                }
                if (data[i].questionVotes[j].voteType === "up") {
                    upVotes++;
                } else {
                    downVotes++;
                }
            }
            data[i].upVotes = upVotes;
            data[i].downVotes = downVotes;
            data[i].alreadyVoted = alreadyVoted;
            data[i].voteCount = upVotes - downVotes;
        }
        data.sort((a, b) => b.id - a.id);
        const questions = data;
        res.type('.html');
        res.render('questions', {questions, role: req.session.user.role, username: req.session.user.userName, ftype: "tag", fvalue: tag, filter_questions: true});
    })
    .catch((error) => {
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
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
        if(req.session.user.userName === data.author.userName || req.session.user.role === 1) {
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
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: error, buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
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
        let alreadyVoted = false;
        for (let i=0;i<data.length; i++) {
            upVotes = 0;
            downVotes = 0;
            alreadyVoted = false;
            for (let j=0;j<data[i].questionVotes.length; j++) {
                if (data[i].questionVotes[j].author.userName === req.session.user.userName) {
                    alreadyVoted = true;
                }
                if (data[i].questionVotes[j].voteType === "up") {
                    upVotes++;
                } else {
                    downVotes++;
                }
            }
            data[i].upVotes = upVotes;
            data[i].downVotes = downVotes;
            data[i].alreadyVoted = alreadyVoted;
            data[i].voteCount = upVotes - downVotes;
        }
        data.sort((a, b) => b.id - a.id);
        const questions = data;
        res.type('.html');
        res.render('questions', {questions, role: req.session.user.role, username: req.session.user.userName, ftype, fvalue, filter_questions: true});
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

router.get('/', (req, res) => {
    let url = 'http://localhost:8080/questions?id=-1';
    const q_id = req.query.id;
    const greater = req.query.greater;
    if (greater) {
        url = 'http://localhost:8080/questions?id=' + q_id + '&greater=' + greater;
    } else if (q_id) {
        url = 'http://localhost:8080/questions?id=' + q_id;
    }

    let questions = [];
    fetch(url, {
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
        let alreadyVoted = false;
        for (let i=0;i<data.length; i++) {
            upVotes = 0;
            downVotes = 0;
            alreadyVoted = false;
            for (let j=0;j<data[i].questionVotes.length; j++) {
                if (data[i].questionVotes[j].author.userName === req.session.user.userName) {
                    alreadyVoted = true;
                }
                if (data[i].questionVotes[j].voteType === "up") {
                    upVotes++;
                } else {
                    downVotes++;
                }
            }
            data[i].upVotes = upVotes;
            data[i].downVotes = downVotes;
            data[i].alreadyVoted = alreadyVoted;
            data[i].voteCount = upVotes - downVotes;
        }
        data.sort((a, b) => b.id - a.id);
        questions = data;
        res.type('.html');
        res.render('questions', {questions, role: req.session.user.role, username: req.session.user.userName, ftype: null, fvalue: null, filter_questions: false});
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

router.post('/:qid/upvote/:id', (req, res) => {
    const qid = req.params.qid;
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
            if (data.questionVotes[i].author.userName === user) {
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
                    res.redirect('/questions/' + qid);
                } else {
                    throw new Error('Server error');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

router.post('/:qid/downvote/:id', (req, res) => {
    const qid = req.params.qid;
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
            if (data.questionVotes[i].author.userName === user) {
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
                    res.redirect('/questions/' + qid);
                } else {
                    throw new Error('Server error');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
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
            if (data.questionVotes[i].author.userName === user) {
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
                res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
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
            if (data.questionVotes[i].author.userName === user) {
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
                res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
            });
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});
            
router.post('/update/:id', (req, res) => {
    const id = req.params.id;

    let pic = req.files.picture;

    console.log(pic);

    if (pic === undefined || pic === null || pic === '')
        pic = false;
    else
        pic = true;

    if (pic) {
        if (req.files.picture.size === 0) {
            pic = false;
        }
    }

    if (pic) {
        pic = "/pictures/" + path.basename(req.files.picture.path);
    } else {
        pic = "null";
    }

    let tags = req.fields.tags;
    if (tags[tags.length - 1] === ';') {
        tags = tags.slice(0, -1);
    }
    tags = tags.split(';');
    console.log("PIC:" + pic)
    const question = {
        title: req.fields.title,
        author: req.session.user.userName,
        content: req.fields.content,
        date: new Date(),
        picture: pic,
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
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
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
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

router.post('/', (req, res) => {
    let picture;
    const title = req.fields.title;
    const content = req.fields.content;
    let tags = req.fields.tags;
    if (tags[tags.length - 1] === ';') {
        tags = tags.slice(0, -1);
    }
    tags = tags.split(';');

    console.log(req.files.picture);

    let test_picture;
    if (req.files.picture === undefined || req.files.picture === null || req.files.picture === "") {
        test_picture = false;
    } else {
        test_picture = true;
    }

    if (test_picture) {
        if (req.files.picture.size === 0) {
            test_picture = false;
        } 
    }

    console.log(test_picture);

    if (test_picture) {
        picture = "/pictures/" + path.basename(req.files.picture.path); //basename returns the last part of a path
    } else {
        picture = "null";
    }

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
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

export default router;