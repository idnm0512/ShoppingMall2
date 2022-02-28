package com.thisfit.shoppingmall.admin.commmgt.repository;

import com.thisfit.shoppingmall.admin.commmgt.domain.repository.CommMgtGateway;
import com.thisfit.shoppingmall.admin.commmgt.domain.vo.CommMgtModifyVO;
import com.thisfit.shoppingmall.admin.commmgt.domain.vo.CommMgtWriteVO;
import com.thisfit.shoppingmall.admin.commmgt.repository.datasource.CommMgtJpaRepository;
import com.thisfit.shoppingmall.user.community.repository.datasource.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommMgtJpa implements CommMgtGateway {

	private final CommMgtJpaRepository commMgtJpaRepository;
	
	// 커뮤관리 작성
	@Override
	@Modifying
	@Transactional
	public void writeCommMgt(CommMgtWriteVO commMgtWriteVO) {
		Community community = Community.builder()
									   .title(commMgtWriteVO.getTitle())
									   .content(commMgtWriteVO.getContent())
									   .writer(commMgtWriteVO.getWriter())
									   .category(commMgtWriteVO.getCategory())
									   .build();

		commMgtJpaRepository.save(community);
	}
	
	// 커뮤관리 수정
	@Override
	@Modifying
	@Transactional
	public void modifyCommMgt(CommMgtModifyVO commMgtModifyVO) {
		Community community = commMgtJpaRepository.findByNo(commMgtModifyVO.getNo());

		community.changeComm(commMgtModifyVO.getTitle(),
							 commMgtModifyVO.getContent());

		commMgtJpaRepository.save(community);
	}
	
	// 커뮤관리 삭제
	@Override
	@Modifying
	@Transactional
	public void deleteCommMgt(int no) {
		commMgtJpaRepository.deleteById(no);
	}
	
}
