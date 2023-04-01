import { Router } from 'express';
import path from 'path';

const router = Router();

router.get('/update/:questionId/:answerId', (req, res) => {
    const questionId = req.params.questionId;
    const answerId = req.params.answerId;
    fetch('http://localhost:8080/answers/' + answerId, {
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
            res.type('.html');
            res.render('updateAnswer', {answer: data, questionId});
        } else {
            throw new Error('You are not the author of this answer');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

router.post('/upvote/:questionId/:answerId', (req, res) => {
    const questionId = req.params.questionId;
    const answerId = req.params.answerId;
    const user = req.session.user.userName;

    // fetch the question from the server, check if the current session user has already voted, if yes then throw and error, else add the vote
    fetch('http://localhost:8080/answers/' + answerId, {
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
        for (let i=0;i<data.answerVotes.length; i++) {
            if (data.answerVotes[i].author.userName === user) {
                alreadyVoted = true;
            }
        }
        if (alreadyVoted) {
            throw new Error('You have already voted');
        } else {
            const vote = {
                answer: answerId,
                author: user,
                voteType: "up"
            }
            fetch('http://localhost:8080/answervotes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vote)
            })
            .then(response => {
                if (response.status === 200) {
                    res.redirect('/questions/' + questionId);
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

router.post('/downvote/:questionId/:answerId', (req, res) => {
    const questionId = req.params.questionId;
    const answerId = req.params.answerId;
    const user = req.session.user.userName;

    // fetch the question from the server, check if the current session user has already voted, if yes then throw and error, else add the vote
    fetch('http://localhost:8080/answers/' + answerId, {
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
        for (let i=0;i<data.answerVotes.length; i++) {
            if (data.answerVotes[i].author.userName === user) {
                alreadyVoted = true;
            }
        }
        if (alreadyVoted) {
            throw new Error('You have already voted');
        } else {
            const vote = {
                answer: answerId,
                author: user,
                voteType: "down"
            }
            fetch('http://localhost:8080/answervotes', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vote)
            })
            .then(response => {
                if (response.status === 200) {
                    res.redirect('/questions/' + questionId);
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

router.post('/update/:questionId/:answerId', (req, res) => {
    const questionId = req.params.questionId;

    let pic = req.files.picture;
    console.log(pic);
    if (pic === undefined || pic === null || pic === '') {
        pic = false;
    } else {
        pic = true;
    }

    if(pic)
        pic = "/pictures/" + path.basename(req.files.picture.path);
    else
        pic = "null";

    const answerId = req.params.answerId;
    const answer = {
        author: req.session.user.userName,
        content: req.fields.content,
        picture: pic,
        date: new Date(),
        question: questionId
    }
    fetch('http://localhost:8080/answers/' + answerId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(answer)
    })
    .then(response => {
        if (response.status === 200) {
            res.redirect('/questions/' + questionId);
        } else {
            throw new Error('Server error');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

router.post('/create/:questionId', (req, res) => {
    const questionId = req.params.questionId;

    let pic = req.files.picture;
    console.log(pic);
    if (pic === undefined || pic === null || pic === '') {
        pic = false;
    } else {
        pic = true;
    }

    if(pic)
        pic = "/pictures/" + path.basename(req.files.picture.path);
    else
        pic = "null";

    const answer = {
        author: req.session.user.userName,
        content: req.fields.content,
        picture: pic,
        date: new Date(),
        question: questionId
    }
    fetch('http://localhost:8080/answers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(answer)
    })
    .then(response => {
        if (response.status === 200) {
            res.redirect('/questions/' + questionId);
        } else {
            throw new Error('Server error');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

router.post('/delete/:questionId/:answerId', (req, res) => {
    const answerId = req.params.answerId;
    const questionId = req.params.questionId;
    fetch('http://localhost:8080/answers/' + answerId, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.status === 200) {
            res.redirect('/questions/' + questionId);
        } else {
            throw new Error('Server error');
        }
    })
    .catch((error) => {
        res.send('Error: ' + error);
    });
});

export default router;