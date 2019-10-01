import com.test.jdbc.entity.UserEntity;
import com.test.jdbc.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.when;


public class UserRepositoryTest {
    @Test
    public void testFindByLogin() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Statement statement = Mockito.mock(Statement.class);
        Connection connection = Mockito.mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery("SELECT * FROM users WHERE login = \"Dandy\"")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        UserRepository userRepository = new UserRepository();
        when(resultSet.getString("login")).thenReturn("Dandy");
        when(resultSet.getString("first_name")).thenReturn("Danil");
        when(resultSet.getString("second_name")).thenReturn("Ishutin");
        when(resultSet.getString("middle_name")).thenReturn("Alexandrovich");
        when(resultSet.getInt("age")).thenReturn(29);
        when(resultSet.getString("email")).thenReturn("dandy@gmail.com");
        when(resultSet.getString("phone_number")).thenReturn("+79166242162");

        UserEntity userEntity = userRepository.findByLogin("Dandy", statement);
        Assert.assertEquals(createTestEntity().toString(), userEntity.toString());//if Exist
    }

    @Test
    public void testUpdateSecondName() throws SQLException, ClassNotFoundException {
        Statement statement = Mockito.mock(Statement.class);
        Connection connection = Mockito.mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);

        String userName1 = "Puppey";
        String change = "Ivanov";
        String sqlPuppey = "UPDATE users SET second_name=\"" + change + "\" WHERE login=\"" + userName1 + "\"";
        UserRepository userRepository = new UserRepository();

        when(statement.executeUpdate(sqlPuppey)).thenReturn(1);
        Assert.assertEquals(true, userRepository.updateSecondName(userName1, change, statement));//if Exist

        String userName2 = "Miracle";
        String sql2 = "UPDATE users SET second_name=\"" + change + "\" WHERE login=\"" + userName2 + "\"";
        when(statement.executeUpdate(sql2)).thenReturn(0);
        Assert.assertEquals(false, userRepository.updateSecondName(userName2, change, statement));//if not Exist
    }

    private UserEntity createTestEntity() {
        UserEntity userEntity = UserEntity.builder()
                .login("Dandy")
                .firstName("Danil")
                .secondName("Ishutin")
                .middleName("Alexandrovich")
                .age(29)
                .email("dandy@gmail.com")
                .phoneNumber("+79166242162")
                .build();
        return userEntity;
    }
}