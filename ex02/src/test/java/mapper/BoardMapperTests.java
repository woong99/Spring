package mapper;


import domain.BoardVO;
import domain.Criteria;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;


    @Test
    public void testGetList() {
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("auto_implement Test");
        board.setContent("새로 작성하는 내용");
        board.setWriter("newbie");

        mapper.insert(board);
        log.info(board);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성하는 글 select key");
        board.setContent("새로 작성하는 내용 select key");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);

        log.info(board);
    }

    @Test
    public void testRead() {
        BoardVO board = mapper.read(6);

        log.info(board);
    }

    @Test
    public void testDelete() {
        log.info("DELETE COUNT: " + mapper.delete(11));
    }

    @Test
    public void testUpdate() {
        BoardVO board = new BoardVO();
        board.setBno(3);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = mapper.update(board);
        log.info("UPDATE COUNT : " + count);
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();

        int pageNum = 3;
        int amount = 20;
        cri.setPageNum(pageNum);
        cri.setAmount(amount);
        cri.setRowNum(pageNum * amount);
        cri.setRn((pageNum - 1) * amount);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }

}
