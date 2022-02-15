package com.thisfit.shoppingmall.user.community.domain.repository;

import com.thisfit.shoppingmall.user.community.repository.datasource.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityGateway {

	// 커뮤니티 리스트
	public Page<Community> getCommList(String category, Pageable pageable);
	
	// 커뮤니티 상세보기
	public Community getCommDetail(int no);
	
	// 전체 게시물 수
	public int getTotalComm(String category);
	
}
