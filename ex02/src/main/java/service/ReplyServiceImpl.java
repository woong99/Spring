package service;

import domain.Criteria;
import domain.ReplyVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private ReplyMapper mapper;

    @Override
    public int register(ReplyVO vo) {
        log.info("register......" + vo);
        return mapper.insert(vo);
    }

    @Override
    public ReplyVO get(int rno) {
        log.info("get......" + rno);
        return mapper.read(rno);
    }

    @Override
    public int modify(ReplyVO vo) {
        log.info("modify......" + vo);
        return mapper.update(vo);
    }

    @Override
    public int remove(int rno) {
        log.info("remove......" + rno);
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, int bno) {
        log.info("get Reply List of a Board " + bno);
        return mapper.getListWithPaging(cri, bno);
    }
}
