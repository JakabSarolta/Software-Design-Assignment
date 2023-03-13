import { Router } from 'express';

const router = Router();

router.get('/', (req, res, next) => {
    if (!req.session.user) {
        res.redirect('/login');
    } else {
        next(); // pass control to the next handler (mint a router.get vagy router.post)
    }
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
        console.log('Success:', data);
        questions = data;
        res.type('.html');
        res.render('questions', {questions, username: req.session.user.userName});
    })
    .catch((error) => {
        console.error('Error:', error);
        res.send('Error');
    });
});

export default router;