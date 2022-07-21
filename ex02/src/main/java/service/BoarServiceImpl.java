package service;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class BoarServiceImpl implements BoardService {

    private BoardMapper mapper;

    @Override
    public void register(BoardVO board) {

    }

    @Override
    public BoardVO get(int bno) {
        return null;
    }

    @Override
    public boolean modify(BoardVO board) {
        return false;
    }

    @Override
    public boolean remove(int bno) {
        return false;
    }

    @Override
    public List<BoardVO> getList() {
        return null;
    }
}
