package com.thisfit.shoppingmall.user.community.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class CommunityController {

	private static final Logger log = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityUseCase communityUseCase;
	
	// 커뮤니티 페이지 진입
	@GetMapping("/list")
	public String getCommList(@PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
							  String category, Model model) {
		log.info("커뮤니티 페이지 진입");

		System.out.println(pageable.getPageNumber());

		model.addAttribute("category", category);
		model.addAttribute("commListInfo", communityUseCase.getCommList(category, pageable));
		model.addAttribute("pageMaker", communityUseCase.pagingComm(category, pageable));
		
		return "/community/commList";
	}
	
	// 커뮤니티 상세보기
	@GetMapping("/detail")
	public String getCommDetail(int page, int no, Model model) {
		log.info("커뮤니티 상세보기");
		
		model.addAttribute("commDetailInfo", communityUseCase.getCommDetail(no));
		model.addAttribute("page", page);
		
		return "/community/commDetail";
	}
	
}
