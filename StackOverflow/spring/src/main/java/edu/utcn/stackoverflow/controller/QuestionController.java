package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.QuestionInDto;
import edu.utcn.stackoverflow.controller.dto.QuestionOutDto;
import edu.utcn.stackoverflow.controller.mapper.QuestionMapper;
import edu.utcn.stackoverflow.dao.TagDao;
import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.Tag;
import edu.utcn.stackoverflow.model.User;
import edu.utcn.stackoverflow.service.QuestionService;
import edu.utcn.stackoverflow.service.TagService;
import edu.utcn.stackoverflow.service.UserService;
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
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private TagService tagService;

    @GetMapping("/{questionId}")
    public QuestionOutDto getQuestionById(@PathVariable("questionId") Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        QuestionOutDto questionOutDto = questionMapper.dtoFromQuestion(question);
        return questionOutDto;
    }

    @GetMapping("/filter/tag/{tagName}")
    public Collection<QuestionOutDto> getQuestionsByTag(@PathVariable("tagName") String tagName) {
        Collection<Question> questions = questionService.getQuestionsByTag(tagService.findByName(tagName));
        return questionMapper.dtosFromQuestions(questions);
    }

    @GetMapping("/filter/author/{authorName}")
    public Collection<QuestionOutDto> getQuestionsByAuthor(@PathVariable("authorName") String authorName) {
        Collection<Question> questions = questionService.getQuestionsByAuthor(userService.findByUserName(authorName));
        return questionMapper.dtosFromQuestions(questions);
    }

    @GetMapping("/filter/title/{keyword}")
    public Collection<QuestionOutDto> getQuestionsByTitleKeyword(@PathVariable("keyword") String keyword) {
        Collection<Question> questions = questionService.getQuestionsByTitleKeyword(keyword);
        return questionMapper.dtosFromQuestions(questions);
    }

    @GetMapping
    public Collection<QuestionOutDto> getAllQuestions() {
        Collection<Question> questions = questionService.getAllQuestions();
        return questionMapper.dtosFromQuestions(questions);
    }

    @PostMapping
    public QuestionOutDto saveQuestion(@RequestBody QuestionInDto questionInDto) {
        Question question = questionMapper.questionFromDto(questionInDto);
        User author = userService.findByUserName(questionInDto.getAuthor());
        if (author == null) {
            throw new NotFoundException();
        }
        question.setAnswers(new ArrayList<>());
        question.setQuestionVotes(new ArrayList<>());
        question.setAuthor(author);
        Collection<Tag> tags = new ArrayList<>();
        for (String tagName : questionInDto.getTags()) {
            Tag tag = tagService.findByName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tag = tagService.createTag(tag);
            }
            tags.add(tag);
        }
        question.setTags(tags);
        Question question2 = questionService.createQuestion(question);
        QuestionOutDto questionOutDto = questionMapper.dtoFromQuestion(question2);
        return questionOutDto;
    }

    @PutMapping("/{questionId}")
    public Question updateQuestion(@PathVariable("questionId") Long questionId, @RequestBody QuestionInDto questionInDto) {
        Question question = questionService.getQuestionById(questionId);
        question.setTitle(questionInDto.getTitle()); //title
        question.setContent(questionInDto.getContent()); //content
        question.setPicture(questionInDto.getPicture()); //picture
        question.setAnswers(questionService.getQuestionById(questionId).getAnswers()); //answers
        question.setQuestionVotes(questionService.getQuestionById(questionId).getQuestionVotes()); //questionVotes
        question.setTags(new ArrayList<>()); //tags
        for (String tagName : questionInDto.getTags()) {
            Tag tag = tagService.findByName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tag = tagService.createTag(tag);
            }
            question.getTags().add(tag);
        }
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        // maybe we will need to delete the answers, questionVotes and tags of the question before deleting it
        questionService.deleteQuestion(questionService.getQuestionById(questionId));
    }
}
