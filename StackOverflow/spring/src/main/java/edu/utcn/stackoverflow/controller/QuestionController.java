package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.QuestionInDto;
import edu.utcn.stackoverflow.controller.dto.QuestionOutDto;
import edu.utcn.stackoverflow.controller.mapper.QuestionMapper;
import edu.utcn.stackoverflow.controller.mapper.TagMapper;
import edu.utcn.stackoverflow.dao.QuestionDao;
import edu.utcn.stackoverflow.dao.TagDao;
import edu.utcn.stackoverflow.dao.UserDao;
import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.Tag;
import edu.utcn.stackoverflow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private QuestionMapper questionMapper;
//    @Autowired
//    private TagMapper tagMapper;
    @Autowired
    private TagDao tagDao;

    @GetMapping
    public Collection<QuestionOutDto> getAllQuestions() {
        Collection<Question> questions = questionDao.findAll();
        return questionMapper.dtosFromQuestions(questions);
    }

    @GetMapping("/{questionId}")
    public QuestionOutDto getQuestionById(@PathVariable("questionId") Long questionId) {
        Question question = questionDao.getById(questionId);
        QuestionOutDto questionOutDto = questionMapper.dtoFromQuestion(question);
        return questionOutDto;
    }

    @PostMapping
    public QuestionOutDto saveQuestion(@RequestBody QuestionInDto questionInDto) {
        Question question = questionMapper.questionFromDto(questionInDto);
        User author = userDao.findByUserName(questionInDto.getAuthor());
        if (author == null) {
            throw new NotFoundException();
        }
        question.setAuthor(author);
        question.setTags(new ArrayList<>());
        for (String tagName : questionInDto.getTags()) {
            Tag tag = tagDao.findByName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tag = tagDao.saveAndFlush(tag);
            }
            question.getTags().add(tag);
        }
        Question question2 = questionDao.saveAndFlush(question);
        QuestionOutDto questionOutDto = questionMapper.dtoFromQuestion(question2);
        return questionOutDto;
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionDao.delete(questionDao.getById(questionId));
    }
}
