package service;

import domain.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Log4j2
public class BoardServiceTests {
    @Setter(onMethod_ = {@Autowired})
    private BoardService service;

    @Test
    public void testExist() {
        log.info("service : " + service);
        assertNotNull(service);
    }

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();
        board.setTitle("새로새로 작성하는 글");
        board.setContent("새로새로 작성하는 내용");
        board.setWriter("newbie");

        service.register(board);

        log.info("생성된 게시물의 번호: " + board.getBno());
    }

    @Test
    public void testGetList() {
        service.getList().forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testGet() {
        log.info(service.get(11));
    }

    @Test
    public void testDelete() {
        log.info("REMOVE RESULT: " + service.remove(2));
    }

    @Test
    public void testUpdate() {
        BoardVO board = service.get(1);
        if (board == null) {
            return;
        }
        board.setTitle("제목 수정합니다.");
        log.info("MODIFY RESULT: " + service.modify(board));
    }
}
