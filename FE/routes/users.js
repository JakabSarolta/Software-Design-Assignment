import {Router} from 'express';

const router = Router();

router.use('/*', (req, res, next) => {
    if (req.session.user && req.session.user.role === 1) {
        next();
    } else {
        res.redirect('/login');
    }
});

router.post('/:id/ban', (req, res) => {
    const userId = req.params.id;
    fetch('http://localhost:8080/users/' + userId + '/ban', {
        method: 'PATCH'
    })
    .then(response => {
        if (response.status === 200) {
            console.log("ok");
            res.redirect('/questions');
        } else {
            throw new Error('Server error');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
});

router.get('/unban', (req, res) => {
    fetch('http://localhost:8080/users/banned', {
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
        res.render('unban', {role: req.session.user.role, username: req.session.user.userName, ftype: null, fvalue: null, users: data});
    })
    .catch((error) => {
        console.error('Error:', error);
        res.render('astronaut', { title: 'Error', subtitle: 'Server error', description: 'An internal server error occured. Please try again!', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
    });
    // res.render('unban', {role: req.session.user.role, username: req.session.user.userName, ftype: null, fvalue: null});
});

router.post('/unban', (req, res) => {
    const user = req.fields.user;
    fetch('http://localhost:8080/users/' + user + '/unban', {
        method: 'PATCH'
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
});

export default router;
