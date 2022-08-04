package service;

import domain.BoardAttachVO;
import domain.BoardVO;
import domain.Criteria;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mapper.BoardAttachMapper;
import mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BoarServiceImpl implements BoardService {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Setter(onMethod_ = @Autowired)
    private BoardAttachMapper attachMapper;

    @Override
    public List<BoardAttachVO> getAttachList(int bno) {
        log.info("get Attach list by bno" + bno);

        return attachMapper.findByBno(bno);
    }

    @Transactional
    @Override
    public void register(BoardVO board) {
        log.info("register......" + board);
        mapper.insertSelectKey(board);

        if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
            return;
        }

        board.getAttachList().forEach(attach -> {
            attach.setBno(board.getBno());
            attachMapper.insert(attach);
        });
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
    public List<BoardVO> getList(Criteria cri) {
        log.info("get List with criteria: " + cri);
        cri.setRowNum(cri.getPageNum() * cri.getAmount());
        cri.setRn((cri.getPageNum() - 1) * cri.getAmount());
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return mapper.getTotalCount(cri);
    }
}
