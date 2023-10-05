package POM.Data.Dao;

import POM.Data.Dao.DaoUtils.SQLJDBCUtil;
import POM.Data.Model.User;

import java.sql.*;

public class UserDao {
    public static int insertIntoUsersTable(User user) throws SQLException {
        PreparedStatement pstmt = null;
        int newUserId = 0;
        String preparedQuery = "INSERT INTO dbo.users values(?, ?, ?, ?, ?, ?)";

        try(Connection conn = SQLJDBCUtil.getConnection()){
            pstmt = conn.prepareStatement(preparedQuery, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getDateOfBirth());
            pstmt.setString(6, user.getPassword());
            pstmt.executeUpdate();

            ResultSet generatedUsers = pstmt.getGeneratedKeys();
            if(generatedUsers.next()){
                newUserId = generatedUsers.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            assert pstmt != null;
            pstmt.close();
        }

        return newUserId;
    }

    public static User getUserById(int id) throws SQLException {
        String preparedQuery = "SELECT * FROM users WHERE id=?";
        PreparedStatement pstmt = null;
        User exactUser = null;

        try(Connection conn = SQLJDBCUtil.getConnection()){
            pstmt = conn.prepareStatement(preparedQuery);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int studentId = rs.getInt(1);
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                String dateOfBirth = rs.getString("dateOfBirth");
                String password = rs.getString("password");
                exactUser = new User(studentId, firstName, lastName, phoneNumber, email, dateOfBirth, password);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            assert pstmt != null;
            pstmt.close();
        }
        return exactUser;
    }

    public static void deleteUserById(int id) throws SQLException {
        String query = "delete from users where id=?";
        PreparedStatement pstmt = null;

        try (Connection conn = SQLJDBCUtil.getConnection()) {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Student deleted successfully!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            assert pstmt != null;
            pstmt.close();
        }
    }
}
