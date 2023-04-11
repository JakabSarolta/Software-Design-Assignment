package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.QuestionVoteInDto;
import edu.utcn.stackoverflow.controller.dto.QuestionVoteOutDto;
import edu.utcn.stackoverflow.model.QuestionVote;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class QuestionVoteMapper {
    @Mapping(target = "question", ignore = true)
    @Mapping(target = "author", ignore = true)
    public abstract QuestionVote questionVoteFromDto(QuestionVoteInDto questionVoteInDto);

    public abstract QuestionVoteOutDto dtoFromQuestionVote(QuestionVote questionVote);

    @IterableMapping(elementTargetType = QuestionVoteOutDto.class)
    public abstract Collection<QuestionVoteOutDto> dtosFromQuestionVotes(Collection<QuestionVote> questionVotes);
}
