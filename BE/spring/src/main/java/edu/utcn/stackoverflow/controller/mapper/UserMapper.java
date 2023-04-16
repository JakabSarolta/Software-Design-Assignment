package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.UserInDto;
import edu.utcn.stackoverflow.controller.dto.UserOutDto;
import edu.utcn.stackoverflow.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "questionVotes", ignore = true)
    @Mapping(target = "answerVotes", ignore = true)
    @Mapping(target = "score", ignore = true)
    @Mapping(target = "banned", ignore = true)
    public abstract User userFromDto(UserInDto userInDto);

    public abstract UserOutDto dtoFromUser(User user);

    public abstract Collection<UserOutDto> dtosFromUsers(Collection<User> users);
}
