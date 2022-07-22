package persistence;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Log4j2
public class MySqlConnectionTest {

    @Inject
    private DataSource ds;

    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testConnection1() {
        try (Connection con = ds.getConnection()) {
            log.info("\n >>>>>>>>>> Connection 출력 : " + con + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMybatis() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            log.info(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
