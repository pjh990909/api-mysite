package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GuestbookVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//삭제
	@DeleteMapping(value="/api/guests/{no}")
	public JsonResult remove(@RequestBody GuestbookVo guestbookVo,
						 @PathVariable(value="no") int no) {
		System.out.println("GuestbookController.remove()");
		
		guestbookVo.setNo(no);
		System.out.println(guestbookVo);
		
		int count = guestbookService.exeRemove(guestbookVo);
		
		return JsonResult.success(count);
	}
	
	
	//저장
	@PostMapping(value="/api/guests")
	public JsonResult add(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.add()");
		System.out.println(guestbookVo);
		
		GuestbookVo guestVo = guestbookService.exeAddandGuest(guestbookVo);
		System.out.println(guestVo);
		
		return JsonResult.success(guestVo);
	}
	
	
	//리스트
	@GetMapping(value="/api/guests")
	public JsonResult list() {
		System.out.println("GuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeGuestList();
		
		System.out.println(guestbookList);
		
		return JsonResult.success(guestbookList);
	}
}
