package com.thisfit.shoppingmall.user.myinfo.controller;

import javax.servlet.http.HttpSession;

import com.thisfit.shoppingmall.user.myinfo.domian.dto.MyInfoRequest;
import com.thisfit.shoppingmall.user.myinfo.domian.usecase.MyInfoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/myInfo")
public class MyInfoController {

	private static final Logger log = LoggerFactory.getLogger(MyInfoController.class);
	
	@Autowired
	private MyInfoUseCase myInfoUseCase;
	
	// 내 정보 보기 페이지 진입
	@GetMapping
	public String myInfoGet(String id, Model model) {
		log.info("내 정보 보기 페이지 진입");
		
		model.addAttribute("myInfo", myInfoUseCase.getMyInfo(id));
		
		return "myInfo/myInfo";
	}
	
	// 내 정보 수정
	@PostMapping("/modifyMyInfo")
	public String modifyMyInfoPost(MyInfoRequest myInfoRequest, RedirectAttributes rttr) {
		myInfoUseCase.modifyMyInfo(myInfoRequest);
		
		rttr.addAttribute("id", myInfoRequest.getId());

		rttr.addFlashAttribute("result", "infoModify success");
		
		return "redirect:/myInfo";
	}
	
	// 비밀번호 변경 페이지 진입
	@GetMapping("/modifyPwd")
	public String modifyPwdGet() {
		log.info("비밀번호 변경 페이지 진입");
		
		return "myInfo/modifyPwd";
	}
	
	// 비밀번호 변경
	@PostMapping("/modifyPwd")
	public String modifyPwdPost(String pwd, String id, RedirectAttributes rttr) {
		myInfoUseCase.modifyPwd(pwd, id);
		
		rttr.addAttribute("id", id);

		rttr.addFlashAttribute("result", "pwdModify success");
		
		return "redirect:/myInfo";
	}
	
	// 회원탈퇴 (상태변경)
	@PostMapping("/deleteUser")
	public String deleteUserPost(String id, HttpSession session) {
		session.invalidate();
		
		myInfoUseCase.deleteUser(id);
		
		return "redirect:/main";
	}
	
}
