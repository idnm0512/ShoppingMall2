package com.thisfit.shoppingmall.admin.commmgt.domain.repository;

import com.thisfit.shoppingmall.admin.commmgt.domain.vo.CommMgtModifyVO;
import com.thisfit.shoppingmall.admin.commmgt.domain.vo.CommMgtWriteVO;

public interface CommMgtGateway {

	// 커뮤관리 작성
	public void writeCommMgt(CommMgtWriteVO commMgtWriteVO);
	
	// 커뮤관리 수정
	public void modifyCommMgt(CommMgtModifyVO commMgtModifyVO);
	
	// 커뮤관리 삭제
	public void deleteCommMgt(int no);
	
}
