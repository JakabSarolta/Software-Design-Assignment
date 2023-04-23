package edu.utcn.stackoverflow.controller;

import edu.utcn.stackoverflow.controller.dto.QuestionInDto;
import edu.utcn.stackoverflow.controller.dto.QuestionOutDto;
import edu.utcn.stackoverflow.controller.mapper.QuestionMapper;
import edu.utcn.stackoverflow.model.Question;
import edu.utcn.stackoverflow.model.Tag;
import edu.utcn.stackoverflow.model.User;
import edu.utcn.stackoverflow.service.QuestionService;
import edu.utcn.stackoverflow.service.TagService;
import edu.utcn.stackoverflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private TagService tagService;

    @GetMapping("/postman")
    public Collection<QuestionOutDto> getQuestionsInPostman() {
        Collection<Question> questions = questionService.getQuestionsStartingFromIndexSortedByDateDesc(1, 2);
        return questionMapper.dtosFromQuestions(questions);
    }

    @GetMapping("/{questionId}")
    public QuestionOutDto getQuestionById(@PathVariable("questionId") Long questionId) {
        Question question = questionService.getQuestionById(questionId);
        return questionMapper.dtoFromQuestion(question);
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

    @GetMapping("/last")
    public Long getLastQuestionId() {
        return questionService.getLastQuestion().getId();
    }

    @GetMapping("/first")
    public Long getFirstQuestionId() {
        return questionService.getFirstQuestion().getId();
    }

    @GetMapping
    public Collection<QuestionOutDto> getAllQuestions(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "greater", required = false) Integer greater
        ) {
        if (id != null) {
            if (greater != null && greater == 1) {
                Collection<Question> questions = questionService.findByIdGreaterThanEqual(id, 5);
                return questionMapper.dtosFromQuestions(questions);
            }
            if (id == -1) {
                id = questionService.getLastQuestion().getId();
            }
            Collection<Question> questions = questionService.findByIdLessThanEqualOrderByIdDesc(id, 5);
            return questionMapper.dtosFromQuestions(questions);
        }
        Collection<Question> questions = questionService.getAllQuestions();
        return questionMapper.dtosFromQuestions(questions);
    }

    @PostMapping
    public QuestionOutDto saveQuestion(@RequestBody @Valid QuestionInDto questionInDto) {
        if (userService.findByUserName(questionInDto.getAuthor()) == null) {
            throw new NotFoundException();
        }
        Question question = questionMapper.questionFromDto(questionInDto);
        User author = userService.findByUserName(questionInDto.getAuthor());
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
        return questionMapper.dtoFromQuestion(question2);
    }

    @PutMapping("/{questionId}")
    public QuestionOutDto updateQuestion(@PathVariable("questionId") Long questionId,
                                   @RequestBody @Valid QuestionInDto questionInDto) {
        Question question = questionMapper.questionFromDto(questionInDto);
        question.setId(questionId);
        if (Objects.equals(question.getPicture(), "null")) {
            question.setPicture(questionService.getQuestionById(questionId).getPicture());
        }
        question.setAuthor(questionService.getQuestionById(questionId).getAuthor());
        question.setAnswers(questionService.getQuestionById(questionId).getAnswers());
        question.setQuestionVotes(questionService.getQuestionById(questionId).getQuestionVotes());
        // check if tags exist, if not create them
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
        return questionMapper.dtoFromQuestion(question2);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        // maybe we will need to delete the answers, questionVotes and tags of the question before deleting it
        questionService.deleteQuestion(questionService.getQuestionById(questionId));
    }
}
