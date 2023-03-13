package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.UserInDto;
import edu.utcn.stackoverflow.controller.dto.UserOutDto;
import edu.utcn.stackoverflow.model.User;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "questions", ignore = true)
    public abstract User userFromDto(UserInDto userInDto);

    public abstract UserOutDto dtoFromUser(User user);

    public abstract Collection<UserOutDto> dtosFromUsers(Collection<User> users);
}
