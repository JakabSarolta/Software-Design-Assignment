package edu.utcn.stackoverflow.controller.mapper;

import edu.utcn.stackoverflow.controller.dto.UserInDto;
import edu.utcn.stackoverflow.controller.dto.UserOutDto;
import edu.utcn.stackoverflow.model.User;
import lombok.Data;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract User userFromDto(UserInDto userInDto);

    public abstract UserOutDto dtoFromUser(User user);
}
