import { Router } from 'express';

const router = Router();

router.get('/update/:questionId/:answerId', (req, res) => {
    const questionId = req.params.questionId;
    const answerId = req.params.answerId;
    console.log("[GET] Update answer: " + answerId + " for question: " + questionId);
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

router.post('/update/:questionId/:answerId', (req, res) => {
    const questionId = req.params.questionId;
    const answerId = req.params.answerId;
    console.log("Update answer: " + answerId + " for question: " + questionId);
    const answer = {
        author: req.session.user.userName,
        content: req.fields.content,
        picture: "heyyyhoooo",
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
    const answer = {
        author: req.session.user.userName,
        content: req.fields.content,
        picture: "heyyyhoooo",
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