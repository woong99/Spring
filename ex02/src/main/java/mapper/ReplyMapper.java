package mapper;

import domain.Criteria;
import domain.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    public int insert(ReplyVO vo);

    public ReplyVO read(int rno);

    public int delete(int rno);

    public int update(ReplyVO reply);

    public List<ReplyVO> getListWithPaging(
            @Param("cri") Criteria cri,
            @Param("bno") int bno
    );
}
