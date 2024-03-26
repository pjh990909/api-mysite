package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//로그인
	public UserVo exeLogin(UserVo userVo) {
		System.out.println("UserService.exeLogin()");
		
		UserVo authUser = userDao.userSelscetByIdPw(userVo);
		
		return authUser;
	}
	
	//회원정보 수정폼(1명 데이터 가져오기)
	public UserVo exeModifyForm(int no) {
		System.out.println("UserService.exeModifyForm()");

		UserVo userVo = userDao.userSelectOneByNo(no);
		return userVo;
	}
	
	//회원정보수정
	public int exeModify(UserVo userVo) {
		System.out.println("UserService.exeModify()");

		int count = userDao.userUpdate(userVo);
		return count;
	}
}

