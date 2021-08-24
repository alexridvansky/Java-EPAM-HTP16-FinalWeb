package model.dao;

import by.spetr.web.model.dao.DefaultUserDao;
import by.spetr.web.model.entity.User;
import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;
import by.spetr.web.model.exception.ConnectionPoolException;
import by.spetr.web.model.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Optional;

public class DefaultUserDaoTest {
    private static DefaultUserDao defaultUserDao;
    private static final User USER_BY_ID_1 = new User(
            1,
            "root",
            UserRoleType.ROOT,
            UserStateType.ENABLED,
            "admin.in.da.house@gmail.com",
            "+375292772455",
            LocalDate.of(2021, 8, 12));

    @BeforeTest
    public void prepareAll() {
        defaultUserDao = new DefaultUserDao();
    }

    @Test
    public void testFindAll() throws DaoException, ConnectionPoolException, InterruptedException {
        var result = defaultUserDao.findAll();
        result.forEach(System.out::println);
    }

    @Test
    public void testFindEntityByExistentId() throws DaoException, ConnectionPoolException {
        Optional<User> user = defaultUserDao.findById(1); // User by {@code Id: 1}
        System.out.println(user);
        Assert.assertEquals(user.get(), USER_BY_ID_1);
    }

    @Test
    public void testFindEntityByNonExistentId() throws DaoException, ConnectionPoolException {
        Optional<User> user = defaultUserDao.findById(999); // User by non-existent {@code Id: 1045}
        System.out.println(user);
        Assert.assertEquals(user, Optional.empty());
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testTestDelete() {
    }

    @Test
    public void testCreate() {
    }

    @Test
    public void testUpgrade() {
    }

    @AfterTest
    public void closeAll() {
        defaultUserDao = null;
    }
}