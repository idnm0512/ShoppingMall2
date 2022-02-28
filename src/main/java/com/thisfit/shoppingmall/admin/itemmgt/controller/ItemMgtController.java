package com.thisfit.shoppingmall.admin.itemmgt.controller;

import java.io.IOException;
import java.util.List;

import com.thisfit.shoppingmall.admin.itemmgt.domain.dto.ItemMgtRequest;
import com.thisfit.shoppingmall.admin.itemmgt.domain.usecase.ItemMgtUseCase;
import com.thisfit.shoppingmall.user.item.domain.usecase.ItemUseCase;
import com.thisfit.shoppingmall.admin.itemmgt.domain.vo.ItemMgtOptInfoVO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/itemMgt")
public class ItemMgtController {

	public static final Logger log = LoggerFactory.getLogger(ItemMgtController.class);

	private final ItemMgtUseCase itemMgtUseCase;
	private final ItemUseCase itemUseCase;
	
	// 상품관리 페이지 진입
	@GetMapping("/list")
	public String getItemMgtList(@PageableDefault(size = 30, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
								 String category, String category2, Model model) {
		log.info("상품관리 페이지 진입");

		model.addAttribute("category", category);
		model.addAttribute("category2", category2);
		model.addAttribute("itemListInfo", itemUseCase.getItemList(category, category2, pageable));
		model.addAttribute("pageMaker", itemUseCase.pagingItem(category, category2, pageable));

		return "admin/itemMgt/itemMgtList";
	}
	
	// 상품등록 페이지 진입
	@GetMapping("/insert")
	public String insertItemMgtGet(int page, String category, String category2, Model model) {
		log.info("상품등록 페이지 진입");

		model.addAttribute("category", category);
		model.addAttribute("category2", category2);
		model.addAttribute("page", page);

		return "admin/itemMgt/itemMgtInsert";
	}
	
	// 상품등록
	@PostMapping("/insert")
	public String insertItemMgtPost(@RequestParam(value="opt_array")String[] opt_array,
									ItemMgtRequest itemMgtRequest,
									RedirectAttributes rttr) {
		
		try {
			itemMgtUseCase.insertItemMgt(itemMgtRequest, opt_array);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IllegalStateException 발생 (상품등록)");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IOException 발생 (상품등록)");
		}
		
		rttr.addAttribute("category", "");
		rttr.addAttribute("category2", "");
		rttr.addAttribute("page", 1);

		rttr.addFlashAttribute("result", "write success");
		
		return "redirect:/itemMgt/list";
	}
	
	// 상품수정 페이지 진입
	@GetMapping("/modify")
	public String modifyItemMgtGet(int page, int no, String category, String category2, Model model) {
		log.info("상품수정 페이지 진입");
		
		model.addAttribute("itemDetailInfo", itemUseCase.getItemDetail(no));
		model.addAttribute("category", category);
		model.addAttribute("category2", category2);
		model.addAttribute("page", page);
		
		return "admin/itemMgt/itemMgtModify";
	}
	
	// 상품수정
	@PostMapping("/modify")
	public String modifyItemMgtPost(@RequestParam(value="opt_array")String[] opt_array,
									ItemMgtRequest itemMgtRequest,
									String paging_category,
									String paging_category2,
									int page,
									RedirectAttributes rttr) {
		
		try {
			itemMgtUseCase.modifyItemMgt(itemMgtRequest, opt_array);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IllegalStateException 발생 (상품수정)");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR]: IOException 발생 (상품수정)");
		}
		
		rttr.addAttribute("category", paging_category);
		rttr.addAttribute("category2", paging_category2);
		rttr.addAttribute("page", page);

		rttr.addFlashAttribute("result", "modify success");
		
		return "redirect:/itemMgt/list";
	}
	
	// 상품삭제
	@ResponseBody
	@PostMapping("/delete")
	public void deleteItemMgt(int no, String thumbnail) {
		itemMgtUseCase.deleteItemMgt(no, thumbnail);
	}
	
	// 옵션정보 ajax
	@ResponseBody
	@GetMapping("/optList")
	public List<ItemMgtOptInfoVO> getItemMgtOptList(int item_no) {
		return itemMgtUseCase.getItemMgtOptList(item_no);
	}
	
	// 옵션삭제 ajax
	@ResponseBody
	@PostMapping("/optDeleteAjax")
	public void deleteItemMgtOptAjax(int opt_no) {
		itemMgtUseCase.deleteItemMgtOptAjax(opt_no);
	}
	
	// 썸머노트 이미지 업로드 ajax
	@ResponseBody
	@PostMapping(path = "summerNoteEditorImgUpload", produces = "application/json; charset=utf8")
	public String uploadSummerNoteEditorImg(@RequestParam("file") MultipartFile multipartFile) {
		return itemMgtUseCase.uploadSummerNoteEditorImg(multipartFile);
	}
	
}
