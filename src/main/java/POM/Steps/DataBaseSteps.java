package POM.Steps;

import POM.Data.Dao.UserDao;
import POM.Data.Model.User;
import io.qameta.allure.Step;

import java.sql.SQLException;

public class DataBaseSteps {
    private int userId;
    private User currentUser;
    @Step("Insert New User Into Database")
    public DataBaseSteps insertNewUser() throws SQLException {
        User newUser = new User("Jesse", "Pinkman", "505148336", "WeNeedToCook@Meth.Blue", "1984-09-24", "Yeah,science!");
        userId = UserDao.insertIntoUsersTable(newUser);
        return this;
    }

    @Step("Get User From Database")
    public DataBaseSteps getUserFromDb() throws SQLException {
        currentUser = UserDao.getUserById(userId);
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
