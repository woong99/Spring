package persistence;

import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Log4j
public class TimeMapperTests {

    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testFactory() {
        log.info("Mybatis Connection ------> " + sqlSessionFactory);
    }

    @Test
    public void testSession() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            log.info(sqlSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
