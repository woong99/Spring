package service;

import domain.Criteria;
import domain.ReplyPageDTO;
import domain.ReplyVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mapper.BoardMapper;
import mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    @Setter(onMethod_ = {@Autowired})
    private ReplyMapper mapper;

    @Setter(onMethod_ = {@Autowired})
    private BoardMapper boardMapper;

    @Override
    public ReplyPageDTO getListPage(Criteria cri, int bno) {
        return new ReplyPageDTO(
                mapper.getCountByBno(bno),
                mapper.getListWithPaging(cri, bno)
        );
    }

    @Transactional
    @Override
    public int register(ReplyVO vo) {
        log.info("register......" + vo);
        boardMapper.updateReplyCnt(vo.getBno(), 1);
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

    @Transactional
    @Override
    public int remove(int rno) {
        log.info("remove......" + rno);
        ReplyVO vo = mapper.read(rno);
        boardMapper.updateReplyCnt(vo.getBno(), -1);
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, int bno) {
        log.info("get Reply List of a Board " + bno);
        return mapper.getListWithPaging(cri, bno);
    }
}
