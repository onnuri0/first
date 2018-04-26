package com.kyw.study.first.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kyw.study.first.entity.Board;
import com.kyw.study.first.repository.BoardRepository;
import com.kyw.study.first.util.ReflectPojoSetUtil;

import io.swagger.annotations.ApiOperation;

@CrossOrigin	
@RestController
@RequestMapping(value="/api/board")
@Transactional
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardRepository repo_board;
	
	@ApiOperation(value="게시글을 전체 조회합니다.")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Board>> basicRBoard() {
		
		List<Board> list = repo_board.findAll();
		
		logger.info("[CHECK] {}", list);
        return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value="게시글을 등록 합니다.")
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> basicCBoard(@RequestBody Board vo,  UriComponentsBuilder ucBuilder) {
System.out.println(vo.toString());	
		repo_board.save(vo);
		HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@ApiOperation(value="게시글을 수정 합니다.")
	@RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<Void> basicUBoard(@RequestBody Board vo,  UriComponentsBuilder ucBuilder) {
		
		Board po = repo_board.findOne(vo.getBdId());
	
		try {
			po = (Board) ReflectPojoSetUtil.setPojo(po, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}			
			repo_board.save(po);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@ApiOperation(value="게시글을 삭제 합니다.")
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Board> basicDBoard(@PathVariable("id") String id ) {
	      
		Board vo = repo_board.findOne(id);
		
        if (vo == null) {
            return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
        }
 
		repo_board.delete(id);
        
        return new ResponseEntity<Board>(HttpStatus.NO_CONTENT);
    }
}
