import com.inovus.controllers.MainController;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MainControllerTest {

    private static final String INCORRECT_USERNAME_FIRST_NUMBER = "1cddds";
    private static final String INCORRECT_USERNAME_FIRST_DOT = ".sjddd";
    private static final String CORRECT_USERNAME = "NewUser123";
    private static final String CORRECT_USERNAME_WITH_DOTS = "NewUser..123";
    private static final String INCORRECT_USERNAME_TOO_SHORT = "New";

    private static final String INCORRECT_PASSWORD = "";

    @Test
    public void validateUsernameMethodForUsernameThatStartsWithDotReturnsCorrectMessage() {
        Assert.assertTrue(MainController.INCORRECT_USERNAME_MESSAGE
                .equals(MainController.validateUsername(INCORRECT_USERNAME_FIRST_DOT)));
    }

    @Test
    public void validateUsernameMethodForUsernameThatStartsWithNumberReturnsCorrectMessage() {
        Assert.assertTrue(MainController.INCORRECT_USERNAME_MESSAGE
                .equals(MainController.validateUsername(INCORRECT_USERNAME_FIRST_NUMBER)));
    }

    @Test
    public void validateUsernameMethodForTooShortUsernameReturnsCorrectMessage() {
        Assert.assertTrue(MainController.INCORRECT_USERNAME_MESSAGE
                .equals(MainController.validateUsername(INCORRECT_USERNAME_TOO_SHORT)));
    }

    @Test
    public void validateUsernameMethodForCorrectUsernameReturnsNull() {
        Assert.assertNull(MainController.validateUsername(CORRECT_USERNAME));
    }

    @Test
    public void validateUsernameMethodForCorrectUsernameWothDotsReturnsNull() {
        Assert.assertNull(MainController.validateUsername(CORRECT_USERNAME_WITH_DOTS));
    }

}
