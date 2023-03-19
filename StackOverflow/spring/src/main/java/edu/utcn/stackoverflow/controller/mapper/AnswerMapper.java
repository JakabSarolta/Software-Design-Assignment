package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.AnswerInDto;
import edu.utcn.stackoverflow.controller.dto.AnswerOutDto;
import edu.utcn.stackoverflow.model.Answer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class AnswerMapper {
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "answerVotes", ignore = true)
    public abstract Answer answerFromDto(AnswerInDto answerInDto);

    public abstract AnswerOutDto dtoFromAnswer(Answer answer);

    @IterableMapping(elementTargetType = AnswerOutDto.class)
    public abstract Collection<AnswerOutDto> dtosFromAnswers(Collection<Answer> answers);
}
