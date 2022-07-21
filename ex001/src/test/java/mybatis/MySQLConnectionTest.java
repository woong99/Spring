package mybatis;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Log4j
public class MySQLConnectionTest {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Inject
    private DataSource ds;

    @Test
    public void testConnection1() {
        try (Connection con = ds.getConnection()) {
            log.info("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection2() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String userName = "woong";
        String password = "Jhan00254!";

        Connection con = DriverManager.getConnection(url, userName, password);
        log.info(con);
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");

        resultSet.next();
        String name = resultSet.getString("name");
        log.info(name);

        resultSet.close();
        statement.close();
        con.close();
    }

}
