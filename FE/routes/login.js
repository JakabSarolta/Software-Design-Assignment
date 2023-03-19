import { Router } from 'express';
import bcrypt from 'bcrypt';

const router = Router();

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
            throw new Error('User does not exist');
        }
    })
    .then(data => {
        // log in the user, store in cookie, redirect to home page
        if (bcrypt.compareSync(password, data.password)) {
            req.session.user = data;
            res.redirect('/questions');
        } else {
            throw new Error('Wrong password');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.type('text');
        res.send('Error');
    });
});

export default router;