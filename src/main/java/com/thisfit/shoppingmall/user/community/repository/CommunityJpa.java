package com.thisfit.shoppingmall.user.community.repository;

import com.thisfit.shoppingmall.user.community.domain.repository.CommunityGateway;
import com.thisfit.shoppingmall.user.community.repository.datasource.Community;
import com.thisfit.shoppingmall.user.community.repository.datasource.CommunityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommunityJpa implements CommunityGateway {

	private final CommunityJpaRepository communityJpaRepository;
	
	// 커뮤니티 리스트
	@Override
	public Page<Community> getCommList(String category, Pageable pageable) {
		return communityJpaRepository.findByCategory(category, pageable);
	}
	
	// 커뮤니티 상세보기
	@Override
	public Community getCommDetail(int no) {
		return communityJpaRepository.findByNo(no);
	}
	
	// 전체 게시물 수
	@Override
	public int getTotalComm(String category) {
		return communityJpaRepository.countByCategory(category);
	}
	
}
