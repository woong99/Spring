package mapper;

import domain.BoardAttachVO;

import java.util.List;

public interface BoardAttachMapper {

    public void insert(BoardAttachVO vo);

    public void delete(String uuid);

    public List<BoardAttachVO> findByBno(int bno);
}
