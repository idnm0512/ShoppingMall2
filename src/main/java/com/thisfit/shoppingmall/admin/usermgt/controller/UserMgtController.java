package com.thisfit.shoppingmall.admin.usermgt.controller;

import com.thisfit.shoppingmall.admin.usermgt.domain.usecase.UserMgtUseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/userMgt")
public class UserMgtController {

	public static final Logger log = LoggerFactory.getLogger(UserMgtController.class);

	private final UserMgtUseCase userMgtUseCase;
	
	// 회원관리 페이지 진입
	@GetMapping("/list")
	public String getUserMgtList(@PageableDefault(size = 20, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
								 String keyword, Model model) {
		log.info("회원관리 페이지 진입");

		model.addAttribute("keyword", keyword);
		model.addAttribute("userListInfo", userMgtUseCase.getUserMgtList(keyword, pageable));
		model.addAttribute("pageMaker", userMgtUseCase.pagingUserMgt(keyword, pageable));

		return "admin/userMgt/userMgtList";
	}
	
	// 회원 활성화 / 비활성화
	@ResponseBody
	@PostMapping("/modifyUserState")
	public void modifyUserState(String id, boolean state) {
		userMgtUseCase.modifyUserState(id, state);
	}
	
}
