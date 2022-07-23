package mapper;

import domain.BoardVO;
import domain.Criteria;

import java.util.List;

public interface BoardMapper {

    //    @Select("select * from tbl_board where bno > 0")
    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    public void insert(BoardVO board);

    public void insertSelectKey(BoardVO board);

    public BoardVO read(int bno);

    public int delete(int bno);

    public int update(BoardVO board);


}
