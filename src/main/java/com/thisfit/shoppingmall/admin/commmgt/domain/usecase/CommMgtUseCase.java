package com.thisfit.shoppingmall.admin.commmgt.domain.usecase;

import com.thisfit.shoppingmall.admin.commmgt.domain.dto.CommMgtRequest;
import com.thisfit.shoppingmall.admin.commmgt.domain.repository.CommMgtGateway;
import com.thisfit.shoppingmall.admin.commmgt.domain.vo.CommMgtModifyVO;
import com.thisfit.shoppingmall.admin.commmgt.domain.vo.CommMgtWriteVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommMgtUseCase {

	private final CommMgtGateway commMgtGateway;

	// 커뮤관리 작성
	public void writeCommMgt(CommMgtRequest commMgtRequest) {
		CommMgtWriteVO commMgtWriteVO = new CommMgtWriteVO(commMgtRequest.getTitle(),
														   commMgtRequest.getContent(),
														   commMgtRequest.getWriter(),
														   commMgtRequest.getCategory());

		commMgtGateway.writeCommMgt(commMgtWriteVO);
	}
	
	// 커뮤관리 수정
	public void modifyCommMgt(CommMgtRequest commMgtRequest) {
		CommMgtModifyVO commMgtModifyVO = new CommMgtModifyVO(commMgtRequest.getNo(),
															  commMgtRequest.getTitle(),
															  commMgtRequest.getContent());

		commMgtGateway.modifyCommMgt(commMgtModifyVO);
	}
	
	// 커뮤관리 삭제
	public void deleteCommMgt(int no) {
		commMgtGateway.deleteCommMgt(no);
	}
	
}
