package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.TagInDto;
import edu.utcn.stackoverflow.controller.dto.TagOutDto;
import edu.utcn.stackoverflow.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TagMapper {
    @Mapping(target = "questions", ignore = true)
    public abstract Tag tagFromDto(TagInDto tagInDto);

    public abstract TagOutDto dtoFromTag(Tag tag);
}
