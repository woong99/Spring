package service;

import domain.BoardVO;
import domain.Criteria;

import java.util.List;

public interface BoardService {
    public void register(BoardVO board);

    public BoardVO get(int bno);

    public boolean modify(BoardVO board);

    public boolean remove(int bno);

//    public List<BoardVO> getList();

    public List<BoardVO> getList(Criteria cri);

}
