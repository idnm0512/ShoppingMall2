package com.thisfit.shoppingmall.admin.commmgt.controller;

import com.thisfit.shoppingmall.admin.commmgt.domain.dto.CommMgtRequest;
import com.thisfit.shoppingmall.admin.commmgt.domain.usecase.CommMgtUseCase;
import com.thisfit.shoppingmall.user.community.domain.usecase.CommunityUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/commMgt")
public class CommMgtController {

	public static final Logger log = LoggerFactory.getLogger(CommMgtController.class);
	
	@Autowired
	private CommMgtUseCase commMgtUseCase;
	
	@Autowired
	private CommunityUseCase communityUseCase;
	
	// 커뮤관리 리스트
	@GetMapping("/list")
	public String getCommMgtList(@PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
								 String category, Model model) {
		log.info("커뮤관리 리스트");

		model.addAttribute("category", category);
		model.addAttribute("commListInfo", communityUseCase.getCommList(category, pageable));
		model.addAttribute("pageMaker", communityUseCase.pagingComm(category, pageable));

		return "/admin/commMgt/commMgtList";
	}
	
	// 커뮤관리 상세보기
	@GetMapping("/detail")
	public String getCommMgtDetail(int page, int no, Model model) {
		log.info("커뮤관리 상세보기");
		
		model.addAttribute("commDetailInfo", communityUseCase.getCommDetail(no));
		model.addAttribute("page", page);
		
		return "/admin/commMgt/commMgtDetail";
	}
	
	// 커뮤관리 작성 페이지 진입
	@GetMapping("/write")
	public String writeCommMgtGet(int page, String category, Model model) {
		log.info("커뮤관리 작성 페이지 진입");
		
		model.addAttribute("category", category);
		model.addAttribute("page", page);
		
		return "/admin/commMgt/commMgtWrite";
	}
	
	// 커뮤관리 작성
	@PostMapping("/write")
	public String writeCommMgtPost(CommMgtRequest commMgtRequest, RedirectAttributes rttr) {
		commMgtUseCase.writeCommMgt(commMgtRequest);
		
		rttr.addAttribute("category", commMgtRequest.getCategory());
		rttr.addAttribute("page", 1);

		rttr.addFlashAttribute("result", "write success");
		
		return "redirect:/commMgt/list";
	}
	
	// 커뮤관리 수정 페이지 진입
	@GetMapping("/modify")
	public String modifyCommMgtGet(int page, int no, Model model) {
		log.info("커뮤관리 수정 페이지 진입");
		
		model.addAttribute("commDetailInfo", communityUseCase.getCommDetail(no));
		model.addAttribute("page", page);
		
		return "/admin/commMgt/commMgtModify";
	}
	
	// 커뮤관리 수정
	@PostMapping("/modify")
	public String modifyCommMgtPost(int page, CommMgtRequest commMgtRequest, RedirectAttributes rttr) {
		commMgtUseCase.modifyCommMgt(commMgtRequest);
		
		rttr.addAttribute("category", commMgtRequest.getCategory());
		rttr.addAttribute("page", page);
		rttr.addAttribute("no", commMgtRequest.getNo());

		rttr.addFlashAttribute("result", "modify success");
		
		return "redirect:/commMgt/detail";
	}
	
	// 커뮤관리 삭제
	@PostMapping("/delete")
	public String deleteCommMgtPost(CommMgtRequest commMgtRequest, RedirectAttributes rttr) {
		commMgtUseCase.deleteCommMgt(commMgtRequest.getNo());
		
		rttr.addAttribute("category", commMgtRequest.getCategory());
		rttr.addAttribute("page", 1);

		rttr.addFlashAttribute("result", "delete success");
		
		return "redirect:/commMgt/list";
	}
	
}
