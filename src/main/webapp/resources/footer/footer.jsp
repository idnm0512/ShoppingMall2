<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
	<style>
		.footer_div {
			height: 250px;
			background: #efefef;
			color: gray;
			font-size: 14px;
		}
	</style>
</head>
<body>
	<div class="footer_div">
		<br><br>
		&emsp;&emsp;&emsp;&emsp;상호명 : 디스핏  | 대표자 : 이재호  | 주소 : 인천 서구 신현동 XX빌 Y동 Z호
		<br>
		&emsp;&emsp;&emsp;&emsp;사업자등록번호 : 123-45-67890 | 통신판매업신고 : 제 1234-인천서구-5678 호  | 개인정보보호책임자 : 이재호 (idnm0512@gmail.com)
		<br>
		&emsp;&emsp;&emsp;&emsp;전화번호 : 032-1234-5678  |  팩스 : 032-1234-5678
		<br><br><br>
		&emsp;&emsp;&emsp;&emsp;ⓒ JaeHo ALL RIGHTS RESERVED. DESIGN BY JaeHo
		<br><br><br>
		&emsp;&emsp;&emsp;&emsp;
		<c:choose>
			<c:when test="${sessionScope.id == 'admin'}">
				<a href="/userMgt/list?keyword=&page=1">[관리자]</a>
			</c:when>
			<c:otherwise>
				<a href="/adminLogin">[관리자]</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>