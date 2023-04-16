import { Router } from 'express';
import bcrypt from 'bcrypt';

const router = Router();

router.use('/*', (req, res, next) => {
    if (req.session.user) {
        res.redirect('/questions');
    } else {
        next();
    }
});

router.get('/', (req, res) => {
    res.type('.html');
    res.render('login');
});

router.post('/', (req, res) => {
    const username = req.fields.username;
    const password = req.fields.password;

    fetch('http://localhost:8080/users/username/' + username, {
        method: 'GET'
    })
    .then(response => {
        if (response.status === 200) {
            return response.json();
        } else {
            res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'User does not exist with this username or password provided.', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
        }
    })
    .then(data => {
        // log in the user, store in cookie, redirect to home page
        if (bcrypt.compareSync(password, data.password)) {
            if (data.banned) {
                res.render('astronaut', { title: 'Error', subtitle: 'User banned', description: 'User is banned. Please contact a moderator.', buttonlink: 'http://localhost:8081/login', buttontext: 'LOGIN'});
            } else {
                req.session.user = data;
                res.redirect('/questions');
            }
        } else {
            res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'Wrong password. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.type('text');
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

export default router;