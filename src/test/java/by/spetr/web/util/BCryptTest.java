package by.spetr.web.util;

import org.testng.Assert;
import org.testng.annotations.Test;


public class BCryptTest {
    private static final String PASSWORD_USER_TO_BE_HASHED = "user";
    private static final String PASSWORD_ROOT_TO_BE_HASHED = "root";


    @Test
    public void testCheckUserPass() {
        String hashed_pass = BCrypt.hashpw(PASSWORD_USER_TO_BE_HASHED, BCrypt.gensalt());
        Assert.assertTrue(BCrypt.checkpw(PASSWORD_USER_TO_BE_HASHED,hashed_pass));
    }

    @Test
    public void testCheckRootPass() {
        String hashed_pass = BCrypt.hashpw(PASSWORD_ROOT_TO_BE_HASHED, BCrypt.gensalt());
        Assert.assertTrue(BCrypt.checkpw(PASSWORD_ROOT_TO_BE_HASHED,hashed_pass));
    }

    @Test
    public void testGeneratingUserPass() {
        String hashed_pass_one = BCrypt.hashpw(PASSWORD_USER_TO_BE_HASHED,BCrypt.gensalt());
        String hashed_pass_two = BCrypt.hashpw(PASSWORD_USER_TO_BE_HASHED,BCrypt.gensalt());
        Assert.assertNotEquals(hashed_pass_one, hashed_pass_two);
    }

    @Test
    public void testGeneratingRootPass() {
        String hashed_pass_once = BCrypt.hashpw(PASSWORD_ROOT_TO_BE_HASHED,BCrypt.gensalt());
        String hashed_pass_twice = BCrypt.hashpw(PASSWORD_ROOT_TO_BE_HASHED,BCrypt.gensalt());
        Assert.assertNotEquals(hashed_pass_once, hashed_pass_twice);
    }
}