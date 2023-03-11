import { Router } from 'express';
import bcrypt from 'bcrypt';

const router = Router();

router.get('/', (req, res) => {
    res.type('.html');
    res.render('register');
});

router.post('/', (req, res) => {
    const username = req.fields.username;
    const password = req.fields.password;
    const passwordHashed = bcrypt.hashSync(password, 10);
    const firstname = req.fields.firstname;
    const lastname = req.fields.lastname;
    const email = req.fields.email;
    const phonenumber = req.fields.phonenumber;
    const role = 0;

    const dict = {
        'lastName': lastname,
        'firstName': firstname,
        'userName': username,
        'password': passwordHashed,
        'email': email,
        'phoneNumber': phonenumber,
        'role': role
    }
    
    fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dict),
    })
    .then(response => {
        console.log(response.status);
        if (response.status === 200) {
            return response.json();
        } else {
            throw new Error('User already exists with this username or email');
        }
    })
    .then(data => {
        console.log('Success:', data);
        res.type('text');
        res.send('OK');
    })
    .catch((error) => {
        console.error('Error:', error);
        res.type('text');
        res.send('Error');
    });
});




export default router;