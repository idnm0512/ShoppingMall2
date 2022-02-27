package com.thisfit.shoppingmall.admin.qnamgt.controller;

import com.thisfit.shoppingmall.admin.qnamgt.domain.dto.QNAMgtAnswerRequest;
import com.thisfit.shoppingmall.admin.qnamgt.domain.usecase.QNAMgtUseCase;
import com.thisfit.shoppingmall.user.qna.domain.dto.QNARequest;
import com.thisfit.shoppingmall.user.qna.domain.usecase.QNAUseCase;
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
@RequestMapping("/qnaMgt")
public class QNAMgtController {

	public static final Logger log = LoggerFactory.getLogger(QNAMgtController.class);
	
	@Autowired
	private QNAMgtUseCase qnaMgtUseCase;
	
	@Autowired
	private QNAUseCase qnaUseCase;
	
	// 문의관리(미답변) or 문의관리(답변완료) + 답변 리스트
	@GetMapping("/list")
	public String getQnaMgtList(@PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
								boolean state, Model model) {
		log.info("문의관리(미답변) or 문의관리(답변완료) + 답변 리스트");

		model.addAttribute("state", state);

		if (!state) {
			model.addAttribute("qnaListInfo", qnaMgtUseCase.getQnaListNoAnswer(state, pageable));
		} else {
			model.addAttribute("qnaListInfo", qnaMgtUseCase.getQnaListWithAnswer(pageable));
		}

		model.addAttribute("pageMaker", qnaMgtUseCase.pagingQnaMgt(state, pageable));

		return "admin/qnaMgt/qnaMgtList";
	}
	
	// 문의 디테일
	@GetMapping("/detail")
	public String getQnaMgtDetail(int page, int no, Model model) {
		log.info("문의 디테일");

		model.addAttribute("qnaDetailInfo", qnaUseCase.getQnaDetail(no));
		model.addAttribute("page", page);
		
		return "admin/qnaMgt/qnaMgtDetail";
	}
	
	// 문의 삭제
	@PostMapping("/delete")
	public String deleteQnaMgt(int no, boolean state, RedirectAttributes rttr) {
		qnaUseCase.deleteQna(no);
		
		rttr.addAttribute("state", state);
		rttr.addAttribute("page", 1);

		rttr.addFlashAttribute("result", "delete success");
		
		return "redirect:/qnaMgt/list";
	}
	
	// 답변 디테일
	@GetMapping("/answerDetail")
	public String getQnaMgtAnswerDetail(int page, int re_no, Model model) {
		log.info("답변 디테일");
		
		model.addAttribute("qnaDetailInfo", qnaMgtUseCase.getQnaMgtAnswerDetail(re_no));
		model.addAttribute("page", page);
		
		return "admin/qnaMgt/qnaMgtAnswerDetail";
	}
	
	// 답변 작성 페이지 진입
	@GetMapping("/writeAnswer")
	public String writeQnaMgtAnswerGet(int page, int no, Model model) {
		log.info("답변 작성 페이지 진입");
		
		model.addAttribute("qnaDetailInfo", qnaUseCase.getQnaDetail(no));
		model.addAttribute("page", page);
		
		return "admin/qnaMgt/qnaMgtAnswerWrite";
	}
	
	// 답변 작성
	@PostMapping("/writeAnswer")
	public String writeQnaMgtAnswerPost(QNARequest qnaRequest, RedirectAttributes rttr) {
		qnaMgtUseCase.writeQnaMgtAnswer(qnaRequest);
		
		rttr.addAttribute("state", qnaRequest.isState());
		rttr.addAttribute("page", 1);

		rttr.addFlashAttribute("result", "write success");
		
		return "redirect:/qnaMgt/list";
	}
	
	// 답변 수정 페이지 진입
	@GetMapping("/answerModify")
	public String modifyQnaMgtAnswerGet(int page, int re_no, Model model) {
		log.info("답변 수정 페이지 진입");
		
		model.addAttribute("qnaMgtAnswerDetailInfo", qnaMgtUseCase.getQnaMgtAnswerDetail(re_no));
		model.addAttribute("page", page);
		
		return "admin/qnaMgt/qnaMgtAnswerModify";
	}
	
	// 답변 수정
	@PostMapping("/modifyAnswer")
	public String modifyQnaMgtAnswerPost(int page, QNAMgtAnswerRequest qnaMgtAnswerRequest, RedirectAttributes rttr) {
		qnaMgtUseCase.modifyQnaMgtAnswer(qnaMgtAnswerRequest);
		
		rttr.addAttribute("state", qnaMgtAnswerRequest.isState());
		rttr.addAttribute("page", page);
		rttr.addAttribute("re_no", qnaMgtAnswerRequest.getRe_no());

		rttr.addFlashAttribute("result", "modify success");
		
		return "redirect:/qnaMgt/answerDetail";
	}
	
}
