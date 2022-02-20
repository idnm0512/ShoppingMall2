package com.thisfit.shoppingmall.user.community.domain.usecase;

import com.thisfit.shoppingmall.user.community.domain.repository.CommunityGateway;
import com.thisfit.shoppingmall.user.community.domain.vo.CommunityDetailVO;
import com.thisfit.shoppingmall.user.community.domain.vo.CommunityVO;
import com.thisfit.shoppingmall.user.community.repository.datasource.Community;
import com.thisfit.shoppingmall.util.paging.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CommunityUseCase {

	private final CommunityGateway communityGateway;

	// 커뮤니티 리스트
	public List<CommunityVO> getCommList(String category, Pageable pageable) {
		Page<Community> communityList = communityGateway.getCommList(category, pageable);
		
		List<CommunityVO> commVOList = new ArrayList<>();

		for (int i = 0; i < communityList.getContent().size(); i++) {

			Community community = communityList.getContent().get(i);

			CommunityVO commVO = new CommunityVO(community.getNo(),
												 community.getCategory(),
												 community.getTitle(),
												 community.getWriter(),
												 community.getUpdateDate());
			commVOList.add(commVO);
		}
		
		return commVOList;
	}
	
	// 커뮤니티 디테일
	public CommunityDetailVO getCommDetail(int no) {
		Community community = communityGateway.getCommDetail(no);

		CommunityDetailVO communityDetailVO = new CommunityDetailVO(community.getNo(),
																    community.getCategory(),
																    community.getTitle(),
																    community.getContent(),
																    community.getWriter(),
																    community.getUpdateDate());
		
		return communityDetailVO;
	}
	
	// 커뮤니티 페이징 처리
	public PageMaker pagingComm(String category, Pageable pageable) {
		int total = communityGateway.getTotalComm(category);
		
		PageMaker pageMaker = new PageMaker(pageable, total);
		
		return pageMaker;
	}
	
}
