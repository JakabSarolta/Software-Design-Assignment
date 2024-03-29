import express from 'express';
import eformidable from 'express-formidable';
import session from 'express-session';
import register from './routes/register.js';
import login from './routes/login.js';
import questions from './routes/questions.js';
import answers from './routes/answers.js';
import users from './routes/users.js';
import morgan from 'morgan';
import path from 'path';
import { existsSync, mkdirSync } from 'fs';

const staticDir = path.join(process.cwd(), 'static');
const uploadDir = path.join(staticDir, 'pictures');
const app = express();
if(!existsSync(uploadDir)) mkdirSync(uploadDir);
app.use(eformidable({ uploadDir })); //atalakitja a formot jsonra, req.fields.*
app.use(morgan('tiny'));
app.set('view engine', 'ejs'); //use template engine ejs
app.use(session({
    secret: 'unicorn',
    resave: false,
    saveUninitialized: true,
}));

app.use('/users', users);
app.use('/register', register);
app.use('/login', login);


app.use('/questions', questions);
app.use('/answers', answers);

// Temporary endpoints
app.get('/logout', (req, res) => {
    req.session.user = null;
    res.redirect('/login');
});

app.get('/getCookie', (req, res) => {
    res.send(req.session.user);
});


app.use(express.static(staticDir));

app.use('/', (req, res) => {
    if (! req.session.user)
        res.render('astronaut', { title: 'Welcome', subtitle: 'To Stackoverflow', description: 'Log in to browse the questions or ask others for helpful advice. Click the button below!', buttonlink: 'http://localhost:8081/login', buttontext: 'LOGIN'});
    else
        res.render('astronaut', { title: '404', subtitle: 'Page not found', description: 'The page that you were looking for does not exist. Return to questions with the button below.', buttonlink: 'http://localhost:8081/questions', buttontext: 'QUESTIONS'});
});

app.listen(8081, () => {
    console.log('App listening on port 8081!');
});

