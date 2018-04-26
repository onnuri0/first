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

import com.kyw.study.first.entity.User;
import com.kyw.study.first.repository.UserRepository;
import com.kyw.study.first.util.ReflectPojoSetUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 교육 목적상 최소한 간단히 생성합니다.
 * @author restnfeel
 *
 */
@CrossOrigin	
@RestController
@RequestMapping(value="/api/user")
@Transactional
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository repo_user;
	
	@ApiOperation(value="사용자를 전체 조회합니다.")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> basicRUser() {
		
		List<User> list = repo_user.findAll();
		
		logger.info("[CHECK] {}", list);
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="사용자를 등록 합니다.")
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> basicCUser(@RequestBody User vo,  UriComponentsBuilder ucBuilder) {
	
		repo_user.save(vo);
		
		HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@ApiOperation(value="사용자를 수정 합니다.")
	@RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<Void> basicUUser(@RequestBody User vo,  UriComponentsBuilder ucBuilder) {
		
	    User po = repo_user.findOne(vo.getBuId());
	
		try {
			po = (User) ReflectPojoSetUtil.setPojo(po, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}			
			repo_user.save(po);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@ApiOperation(value="사용자를 삭제 합니다.")
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<User> basicDUser(@PathVariable("id") String id ) {
	     
		User vo = repo_user.findOne(id);
		
        if (vo == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
		repo_user.delete(id);
        
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	
}
