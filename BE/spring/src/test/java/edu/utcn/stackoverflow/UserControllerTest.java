package edu.utcn.stackoverflow;

import edu.utcn.stackoverflow.controller.BadRequestException;
import edu.utcn.stackoverflow.controller.UserController;
import edu.utcn.stackoverflow.controller.dto.UserInDto;
import edu.utcn.stackoverflow.controller.dto.UserOutDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    void allUsersShouldNotBeNull() {
        Assertions.assertNotNull(userController.getAllUsers());
    }

    @Test
    void unknownUsernameUserShouldThrowException() {
        Assertions.assertThrowsExactly(BadRequestException.class, () -> userController.getUserByUserName("cocacola"));
    }

    //@Test
    void userShouldNotBeNull() {
        Assertions.assertNotNull(userController.getUserById(1L));
    }

    //@Test
    void userShouldBeAdded() {
        UserOutDto userOutDto = userController
                .saveUser(new UserInDto("Oliver", "Black", "oliverblack3",
                        "oliverblack", "oliverblack3@gmail.com", "0722222222", 0));
        Assertions.assertNotNull(userOutDto);
    }

    //@Test
    void userShouldBeUpdated() {
        String usernameToUpdate = "oliverblack3";
        UserOutDto userOutDto = userController
                .updateUser(userController.getUserByUserName(usernameToUpdate).getId(),
                        new UserInDto("Oliver", "Black", "oliverblack4",
                                "oliverblack", "oliverblack4@gmail.com", "0722333332", 0));
        Assertions.assertNotNull(userOutDto);
    }

    //@Test
    void userShouldBeDeleted() {
        String username = "oliverblack4";
        userController.deleteUser(userController.getUserByUserName(username).getId());
        Assertions.assertThrowsExactly(BadRequestException.class, () -> userController.getUserByUserName(username));
    }
}
