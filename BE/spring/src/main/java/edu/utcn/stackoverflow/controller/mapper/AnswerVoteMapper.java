package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.AnswerVoteInDto;
import edu.utcn.stackoverflow.controller.dto.AnswerVoteOutDto;
import edu.utcn.stackoverflow.model.AnswerVote;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class AnswerVoteMapper {
    @Mapping(target = "answer", ignore = true)
    @Mapping(target = "author", ignore = true)
    public abstract AnswerVote answerVoteFromDto(AnswerVoteInDto answerVoteInDto);

    public abstract AnswerVoteOutDto dtoFromAnswerVote(AnswerVote answerVote);

    @IterableMapping(elementTargetType = AnswerVoteOutDto.class)
    public abstract Collection<AnswerVoteOutDto> dtosFromAnswerVotes(Collection<AnswerVote> answerVotes);
}
