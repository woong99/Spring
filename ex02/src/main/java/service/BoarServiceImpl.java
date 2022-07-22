package service;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class BoarServiceImpl implements BoardService {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Override
    public void register(BoardVO board) {
        log.info("register......" + board);
        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(int bno) {
        log.info("get......" + bno);

        return mapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("modify......" + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(int bno) {
        log.info("remove...." + bno);

        return mapper.delete(bno) == 1;
    }

    @Override
    public List<BoardVO> getList() {
        log.info("getList.........");

        return mapper.getList();
    }
}
