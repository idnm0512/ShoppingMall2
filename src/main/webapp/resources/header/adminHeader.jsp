<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminHeader</title>
	<style type="text/css">
		/* 전체 영역의 여백을 없애줌 */
		* {
			margin: 0;
			padding: 0;
		}
		
		/* a태그(링크)의 기본 색을 black으로, 선을 없애줌 */
		a:link, a:visited {
			color: black;
			text-decoration: none;
		}
		
		/* a태그(링크)에 마우스를 올렸을 때 색 변경 */
		a:hover {
			color: blue;
			transition: ease 0.2s;
		}
		
		/* ul-li태그(리스트)의 스타일을 없애줌 */
		ul li {
			list-style: none;
		}
		
		/* logo 이미지 크기 */
		#logo img {
			width: 100px;
			height: 25px;
			margin-top: 10px;
		}
		
		/* logo를 감싼 링크 크기  */
		#logo a {
			width: 100px;
			height: 25px;
		}
		
		/***************************** menu 시작 */
		/* menu (상의, 하의 등) 부분 */
		#menu {
			height: 45px;
			line-height: 45px;
			text-align: center;
			border-top: solid 1px #efefef;
			border-bottom: solid 1px #efefef;
		}
		
		/* menu 안에 있는 링크 폰트사이즈 */
		#menu a {
			font-size: 15px;
		}
		
		/* menu에서 리스트를 우측정렬, 넓이를 100px로 */
		#menu > ul > li {
			float: left;
			width: 100px;
		}
		
		/* menu에서 dropdown할 리스트를 감춰줌 */
		#menu > ul > li > ul {
			display: none;
		}
		
		/* menu 해당 리스트에 마우스를 올리면 dropdown */
		#menu > ul > li:hover > ul {
			display: block;
			position: absolute;
			min-width: 100px;
		}
		
		/* menu dropdown된 리스트에 마우스를 올렸을 때 반응 */
		#menu > ul > li > ul > li:hover {
			background: #efefef;
			transition: ease 0.2s;
		}
		
		/* menu dropdown 리스트의 배경을 white로 */
		#menu > ul > li > ul > li {
			background: white;
		}
		/**************************** menu 끝 */
	</style>
</head>
<body>
	<nav id="menu">
		<ul>
			<li>
				<div id="logo">
					<a href="/main"><img src="/resources/img/logo.png"></a>
				</div>
			</li>
			<li><a class="admin_header_a" href="/userMgt/list?keyword=&page=1">회원관리</a></li>
			<li><a class="admin_header_a" href="/commMgt/list?category=공지사항&page=1">커뮤관리</a>
				<ul>
					<li><a href="/commMgt/list?category=공지사항&page=1">공지사항</a></li>
					<li><a href="/commMgt/list?category=이벤트&page=1">이벤트</a></li>
					<li><a href="/commMgt/list?category=자주묻는질문&page=1">자주묻는질문</a></li>
				</ul>
			</li>
			<li><a class="admin_header_a" href="/qnaMgt/list?state=false&page=1">문의관리</a>
				<ul>
					<li><a href="/qnaMgt/list?state=false&page=1">미답변</a></li>
					<li><a href="/qnaMgt/list?state=true&page=1">답변완료</a></li>
				</ul>
			</li>
			<li><a class="admin_header_a" href="/itemMgt/list?category=&category2=&page=1">상품관리</a></li>
			<li><a class="admin_header_a" href="/orderMgt/list?order_state=배송준비중&page=1">주문관리</a>
				<ul>
					<li><a href="/orderMgt/list?order_state=배송준비중&page=1">배송준비중</a></li>
					<li><a href="/orderMgt/list?order_state=배송완료&page=1">배송완료</a></li>
					<li><a href="/orderMgt/list?order_state=주문취소&page=1">주문취소</a></li>
					<li><a href="/orderMgt/list?order_state=취소완료&page=1">취소완료</a></li>
				</ul>
			</li>
		</ul>
	</nav>
</body>
</html>