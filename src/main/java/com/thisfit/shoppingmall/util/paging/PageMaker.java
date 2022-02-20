package com.thisfit.shoppingmall.util.paging;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class PageMaker {
	
	// 시작 페이지
	private int startPage;

	// 끝 페이지
	private int endPage;

	// 이전 페이지, 다음 페이지 존재유무
	private boolean prev, next;

	// 전체 게시물 수
	private int total;

	// 페이징 처리를 위한 Pageable 인터페이스
	private Pageable pageable;

	// 현재 페이지 (View 에서 쓰일 필드)
	private int page;

	// 한 페이지 당 보여질 게시물 갯수
	private int size;

	// 생성자
	public PageMaker(Pageable pageable, int total) {
	    
	    this.pageable = pageable;
	    this.total = total;

		// 현재 페이지 (View 에서 쓰일 필드)
		this.page = pageable.getPageNumber() + 1;

		// 한 페이지 당 보여질 게시물 갯수
		this.size = pageable.getPageSize();

	    // 마지막 페이지
	    this.endPage = (int)(Math.ceil(page / 10.0)) * 10;
	    
	    // 시작 페이지
	    this.startPage = this.endPage - 9;
	    
	    // 전체 마지막 페이지
	    int realEnd = (int)(Math.ceil(total * 1.0 / size));
	    
	    // 전체 마지막 페이지(realEnd)가 화면에 보이는 마지막페이지(endPage)보다 작은 경우, 보이는 페이지(endPage) 값 조정
	    if(realEnd < this.endPage) {
	        this.endPage = realEnd;
	    }
	    
	    // 시작 페이지(startPage) 값이 1 보다 큰 경우 true
	    this.prev = this.startPage > 1;
	    
	    // 마지막 페이지(endPage) 값이 realEnd 보다 작은 경우 true
	    this.next = this.endPage < realEnd;
	    
	}
	
}
