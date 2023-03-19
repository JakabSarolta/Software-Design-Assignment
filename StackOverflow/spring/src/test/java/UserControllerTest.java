import edu.utcn.stackoverflow.controller.UserController;
import edu.utcn.stackoverflow.controller.dto.UserInDto;
import edu.utcn.stackoverflow.controller.dto.UserOutDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class UserControllerTest {
    private UserController userController;
    private Long id;

    @BeforeAll
    static void setUp() {
        //userController = new UserController();
    }

    @Test
    void newUserShouldBeAdded() {
        UserOutDto userOutDto = userController.saveUser(new UserInDto("Oliver", "Black", "oliverblack", "oliverblack", "oliverblack@gmail.com", "0722222222", 0));
        id = userOutDto.getId();
        Assertions.assertNotNull(userOutDto);
    }

    @Test
    void newUserShouldThrowValidationException() {
        //first and last name start with lower case letters -> doesn't match pattern
        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> userController.saveUser(new UserInDto("oliver", "black", "oliverblack", "oliverblack", "oliverblack@gmail.com", "0722222222", 0)));
    }

    @Test
    void userShouldBeUpdated() {
        UserOutDto userOutDto = userController.updateUser(id, new UserInDto("Oliver", "Black", "oliverblack", "oliverblack", "oliverblack@gmail.com", "0722333332", 0));
        Assertions.assertNotNull(userOutDto);
    }

    @Test
    void usersShouldBeReturned() {
        Assertions.assertNotNull(userController.getAllUsers());
    }

    @Test
    void userShouldBeDeleted() {
        userController.deleteUser(id);
        Assertions.assertNull(userController.getUserById(id));
    }
}
