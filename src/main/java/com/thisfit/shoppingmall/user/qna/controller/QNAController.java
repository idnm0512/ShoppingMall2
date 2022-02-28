package com.thisfit.shoppingmall.user.qna.controller;

import com.thisfit.shoppingmall.admin.qnamgt.domain.usecase.QNAMgtUseCase;
import com.thisfit.shoppingmall.user.qna.domain.dto.QNARequest;
import com.thisfit.shoppingmall.user.qna.domain.usecase.QNAUseCase;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("/qna")
public class QNAController {

	private static final Logger log = LoggerFactory.getLogger(QNAController.class);

	private final QNAUseCase qnaUseCase;
	private final QNAMgtUseCase qnaMgtUseCase;
	
	// 1:1문의(미답변) or 1:1문의(답변완료) + 답변 리스트
	@GetMapping("/list")
	public String getQnaList(@PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
							 QNARequest qnaRequest, Model model) {
		log.info("1:1문의(미답변) or 1:1문의(답변완료) + 답변 리스트");

		model.addAttribute("writer", qnaRequest.getWriter());
		model.addAttribute("state", qnaRequest.isState());

		if (qnaRequest.isState() == false) {
			model.addAttribute("qnaListInfo", qnaUseCase.getQnaListNoAnswer(qnaRequest, pageable));
		} else {
			model.addAttribute("qnaListInfo", qnaUseCase.getQnaListWithAnswer(qnaRequest, pageable));
		}

		model.addAttribute("pageMaker", qnaUseCase.pagingQna(qnaRequest, pageable));

		return "qna/qnaList";
	}
	
	// 1:1문의 디테일
	@GetMapping("/detail")
	public String getQnaDetail(int page, int no, Model model) {
		log.info("1:1문의 디테일");
		
		model.addAttribute("qnaDetailInfo", qnaUseCase.getQnaDetail(no));
		model.addAttribute("page", page);
		
		return "qna/qnaDetail";
	}
	
	// 1:1문의 작성 페이지 진입
	@GetMapping("/write")
	public String writeQnaGet(int page, QNARequest qnaRequest, Model model) {
		log.info("1:1문의 작성 페이지 진입");
		
		model.addAttribute("writer", qnaRequest.getWriter());
		model.addAttribute("state", qnaRequest.isState());
		model.addAttribute("page", page);
		
		return "qna/qnaWrite";
	}
	
	// 1:1문의 작성
	@PostMapping("/write")
	public String writeQnaPost(QNARequest qnaRequest, RedirectAttributes rttr) {
		qnaUseCase.writeQna(qnaRequest);
		
		rttr.addAttribute("writer", qnaRequest.getWriter());
		rttr.addAttribute("state", false);
		rttr.addAttribute("page", 1);

		rttr.addFlashAttribute("result", "write success");
		
		return "redirect:/qna/list";
	}
	
	// 1:1문의 수정 페이지 진입
	@GetMapping("/modify")
	public String modifyQnaGet(int page, int no, Model model) {
		log.info("1:1문의 수정 페이지 진입");
		
		model.addAttribute("qnaDetailInfo", qnaUseCase.getQnaDetail(no));
		model.addAttribute("page", page);
		
		return "qna/qnaModify";
	}
	
	// 1:1문의 수정
	@PostMapping("/modify")
	public String modifyQnaPost(int page, QNARequest qnaRequest, RedirectAttributes rttr) {
		qnaUseCase.modifyQna(qnaRequest);
		
		rttr.addAttribute("writer", qnaRequest.getWriter());
		rttr.addAttribute("state", qnaRequest.isState());
		rttr.addAttribute("page", page);
		rttr.addAttribute("no", qnaRequest.getNo());

		rttr.addFlashAttribute("result", "modify success");
		
		return "redirect:/qna/detail";
	}
	
	// 1:1문의 삭제
	@PostMapping("/delete")
	public String deleteQnaPost(QNARequest qnaRequest, RedirectAttributes rttr) {
		qnaUseCase.deleteQna(qnaRequest.getNo());
		
		rttr.addAttribute("writer", qnaRequest.getWriter());
		rttr.addAttribute("state", qnaRequest.isState());
		rttr.addAttribute("page", 1);

		rttr.addFlashAttribute("result", "delete success");
		
		return "redirect:/qna/list";
	}
	
	// 답변 디테일
	@GetMapping("/answerDetail")
	public String qnaMgtAnswerDetailGet(int page, int re_no, Model model) {
		log.info("답변 디테일");

		model.addAttribute("qnaDetailInfo", qnaMgtUseCase.getQnaMgtAnswerDetail(re_no));
		model.addAttribute("page", page);

		return "qna/qnaAnswerDetail";
	}
	
}
