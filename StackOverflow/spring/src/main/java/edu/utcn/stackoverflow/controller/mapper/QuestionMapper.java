package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.QuestionInDto;
import edu.utcn.stackoverflow.controller.dto.QuestionOutDto;
import edu.utcn.stackoverflow.model.Question;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class QuestionMapper {
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "author", ignore = true)
    public abstract Question questionFromDto(QuestionInDto questionInDto);

    public abstract QuestionOutDto dtoFromQuestion(Question question);

    @IterableMapping(elementTargetType = QuestionOutDto.class)
    public abstract Collection<QuestionOutDto> dtosFromQuestions(Collection<Question> questions);
}
