package controller;

import domain.Criteria;
import domain.ReplyVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReplyService;

import java.util.List;

@RestController
@RequestMapping("/replies")
@Slf4j
@AllArgsConstructor
public class ReplyController {
    private ReplyService service;

    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
        log.info("ReplyVO: " + vo);

        int insertCount = service.register(vo);

        log.info("Reply INSERT COUNT: " + insertCount);

        return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ReplyVO>> getList(
            @PathVariable("page") int page,
            @PathVariable("bno") int bno) {
        log.info("getList..................");
        Criteria cri = new Criteria(page, 10);
        log.info(String.valueOf(cri));

        return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno) {
        log.info("get: " + rno);

        return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") int rno) {
        log.info("remove: " + rno);

        return service.remove(rno) == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
