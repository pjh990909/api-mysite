package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인
	@PostMapping("/api/users/login")
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {
		System.out.println("UserController.login()");
		
		UserVo authUser = userService.exeLogin(userVo);
		
		if(authUser != null) {
			//토큰발급 해더에 실어 보낸다
			JwtUtil.createTokenAndSetHeader(response,""+authUser.getNo());
			return JsonResult.success(authUser);
		}else {
			
			return JsonResult.fail("로그인실패");
		}
		
	}
	
	//회원정보 수정폼(1명 데이터 가져오기)
	@GetMapping("/api/users/modify")
	public JsonResult modifyform(HttpServletRequest request) {
		System.out.println("UserController.modifyform()");
		
		/*
		//토큰
		String token = JwtUtil.getTokenByHeader(request);
		System.out.println("token="+token);
		
		//검증
		boolean check = JwtUtil.checkToken(token);
		System.out.println(check);
		
		//이상없음             ---->이상있음
		if(check==true) {
			System.out.println("정상");
			int no = Integer.parseInt(JwtUtil.getSubjectFromToken(token));
			System.out.println(no);
		}
		*/
		
		int no = JwtUtil.getNoFromHeader(request);
		if(no != -1) {
			UserVo userVo = userService.exeModifyForm(no);
			
			return JsonResult.success(userVo);
		}else {
			//토큰이 없거나(로그인상태아님), 변조된 경우
			
			return JsonResult.fail("로그인상태아님");
		}
		
	}
	
	//회원정보수정
	@PutMapping("/api/users/modify")
	public JsonResult modify(@RequestBody UserVo userVo, HttpServletRequest request) {
		System.out.println("UserController.modify()");
		
		System.out.println(userVo);
		
		int no = JwtUtil.getNoFromHeader(request);
		if(no != -1) {
			//db에 수정시킨다
			userService.exeModify(userVo);
			
			return JsonResult.success(userVo.getName());
		}else {
			
			return JsonResult.fail("로그인하지않음");
		}
	}
	
	//회원가입
	@PostMapping("/api/users/join")
	public JsonResult join(@RequestBody UserVo userVo) {
		System.out.println("UserController.join()");
		
		int no = userService.exejoin(userVo);
		if(no>0) {
			return JsonResult.success(no);
		}else {
			return JsonResult.fail("회원가입 실패");
		}		
	}	
}
