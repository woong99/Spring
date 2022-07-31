package mapper;

import domain.Criteria;
import domain.ReplyVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@Log4j
public class ReplyMapperTests {
    private int[] bnoArr = {1, 2, 3, 4, 5};
    @Setter(onMethod_ = {@Autowired})
    private ReplyMapper mapper;

    @Test
    public void testMapper() {
        log.info(mapper);
    }

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = new ReplyVO();

            vo.setBno(bnoArr[i % 5]);
            vo.setReply("댓글 테스트 " + i);
            vo.setReplyer("replyer" + i);

            mapper.insert(vo);
        });
    }

    @Test
    public void testRead() {
        int targetRno = 5;

        ReplyVO vo = mapper.read(5);

        log.info(vo);
    }

    @Test
    public void testDelete() {
        int targetRno = 2;

        mapper.delete(targetRno);
    }

    @Test
    public void testUpdate() {
        int targetRno = 1;

        ReplyVO vo = mapper.read(targetRno);

        vo.setReply("Update Reply");

        int count = mapper.update(vo);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testList() {
        Criteria cri = new Criteria();

        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);

        replies.forEach(reply -> log.info(reply));
    }

    @Test
    public void testList2() {
        Criteria cri = new Criteria(2, 10);
//        int pageNum = 2;
//        int amount = 10;
//        cri.setRowNum(pageNum * amount);
//        cri.setRn((pageNum - 1) * amount);
        log.info(cri);
        List<ReplyVO> replies = mapper.getListWithPaging(cri, 2);

        replies.forEach(replyVO -> log.info(replyVO));
    }
}
