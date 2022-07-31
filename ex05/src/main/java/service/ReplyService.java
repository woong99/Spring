package service;

import domain.Criteria;
import domain.ReplyPageDTO;
import domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    public int register(ReplyVO vo);

    public ReplyVO get(int rno);

    public int modify(ReplyVO vo);

    public int remove(int rno);

    public List<ReplyVO> getList(Criteria cri, int bno);

    public ReplyPageDTO getListPage(Criteria cri, int bno);
}
