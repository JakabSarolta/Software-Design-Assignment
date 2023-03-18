import express from 'express';
import eformidable from 'express-formidable';
import session from 'express-session';
import register from './routes/register.js';
import login from './routes/login.js';
import questions from './routes/questions.js';
import answers from './routes/answers.js';
import morgan from 'morgan';
import path from 'path';

const staticDir = path.join(process.cwd(), 'static');
const app = express();
app.use(eformidable()); //atalakitja a formot jsonra, req.fields.*
app.use(morgan('tiny'));
app.set('view engine', 'ejs'); //use template engine ejs
app.use(session({
    secret: 'unicorn',
    resave: false,
    saveUninitialized: true,
}));

app.use('/register', register);
app.use('/login', login);
app.use('/questions', questions);
app.use('/answers', answers);

// Temporary endpoints
app.get('/logout', (req, res) => {
    req.session.user = null;
    res.send('OK');
});
app.get('/getCookie', (req, res) => {
    res.send(req.session.user); 
});

app.use(express.static(staticDir));
app.listen(8081, () => {
    console.log('App listening on port 8081!');
});

