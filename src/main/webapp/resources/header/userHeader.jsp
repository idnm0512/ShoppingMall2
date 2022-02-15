<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
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
			color: orange;
			transition: ease 0.2s;
		}
		
		/* ul-li태그(리스트)의 스타일을 없애줌 */
		ul li {
			list-style: none;
		}
		
		/***************************** top 시작 */
		/* top (로그인, 회원가입 등) 부분 */
		#top {
			margin-left: 60px;
			margin-right: 60px;
			height: 35px;
			line-height: 35px;
			text-align: center;
			border-left: solid 1px #efefef;
			border-right: solid 1px #efefef;
		}
		
		/* top 안에 있는 링크 폰트사이즈 */
		#top a {
			font-size: 12px;
		}
		
		/* top에서 리스트를 우측정렬, 넓이를 80px로 */
		#top > ul > li {
			float: right;
			width: 80px;
		}
		
		/* top에서 dropdown할 리스트를 감춰줌 */
		#top > ul > li > ul {
			display: none;
		}
		
		/* top 해당 리스트에 마우스를 올리면 dropdown */
		#top > ul > li:hover > ul {
			display: block;
			position: absolute;
			min-width: 80px;
		}
		
		/* top dropdown 리스트에 마우스를 올렸을 때 반응 */
		#top > ul > li > ul > li:hover {
			background: #efefef;
			transition: ease 0.2s;
		}
		
		/* top dropdown 리스트의 배경을 white로 */
		#top > ul > li > ul > li {
			background: white;
		}
		/***************************** top 끝 */
		
		/**************************** logo 시작 */
		/* logo (로고) 부분 */
		#logo {
			height: 100px;
			border-top: solid 1px #efefef;
		}
		
		/* logo 이미지 크기 */
		#logo img {
			width: 250px;
			height: 55px;
		}
		
		/* logo를 감싼 링크 크기, 이미지 가운데 정렬  */
		#logo a {
			width: 250px;
			height: 55px;
			display: block; 
    		margin: 22.5px auto 22.5px auto;
		}
		/**************************** logo 끝 */
		
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
	<!-- 최상단 바 -->
	<c:choose>
		<c:when test="${sessionScope.id == null}">
			<div id="top">
				<ul>
					<li><a href="#">마이페이지</a>
						<ul>
							<li><a href="/login">주문/배송</a></li>
							<li><a href="/login">장바구니</a></li>
							<li><a href="/login">관심상품</a></li>
							<li><a href="/login">1:1문의</a></li>
							<li><a href="/login">내정보보기</a></li>
						</ul>
					</li>
					<li><a href="#">커뮤니티</a>
						<ul>
							<li><a href="/community/list?category=공지사항&page=1">공지사항</a></li>
							<li><a href="/community/list?category=이벤트&page=1">이벤트</a></li>
							<li><a href="/community/list?category=자주묻는질문&page=1">자주묻는질문</a></li>
						</ul>
					</li>
					<li><a href="/join">회원가입</a></li>
					<li><a href="/login">로그인</a></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div id="top">
				<ul>
					<li><a href="#">마이페이지</a>
						<ul>
							<li><a href="/order/list?user_id=${sessionScope.id }&order_state=배송준비중">주문/배송</a></li>
							<li><a href="/cart/list?user_id=${sessionScope.id }">장바구니</a></li>
							<li><a href="/wishList?user_id=${sessionScope.id }&page=1">관심상품</a></li>
							<li><a href="/qna/list?writer=${sessionScope.id }&state=false&page=1">1:1문의</a></li>
							<li><a href="/myInfo?id=${sessionScope.id }">내정보보기</a></li>
						</ul>
					</li>
					<li><a href="#">커뮤니티</a>
						<ul>
							<li><a href="/community/list?category=공지사항&page=1">공지사항</a></li>
							<li><a href="/community/list?category=이벤트&page=1">이벤트</a></li>
							<li><a href="/community/list?category=자주묻는질문&page=1">자주묻는질문</a></li>
						</ul>
					</li>
					<li><a href="/logout">로그아웃</a></li>
					<li style="font-size: 12px; width: 170px;">${sessionScope.id}님 환영합니다.</li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
	
	<!-- 로고 부분 -->
	<div id="logo">
		<a href="/main"><img src="/resources/img/logo.png"></a>
	</div>
	
	<!-- 메뉴 바 -->
	<nav id="menu">
		<ul>
			<li><a href="/item/list?category=&category2=&page=1">ALL</a></li>
			<li><a href="/item/list?category=top&category2=&page=1">TOP</a>
				<ul>
					<li><a href="/item/list?category=top&category2=반팔&page=1">반팔</a></li>
					<li><a href="/item/list?category=top&category2=긴팔&page=1">긴팔</a></li>
					<li><a href="/item/list?category=top&category2=맨투맨&page=1">맨투맨</a></li>
				</ul>
			</li>
			<li><a href="/item/list?category=bottom&category2=&page=1">BOTTOM</a>
				<ul>
					<li><a href="/item/list?category=bottom&category2=청바지&page=1">청바지</a></li>
					<li><a href="/item/list?category=bottom&category2=슬랙스&page=1">슬랙스</a></li>
					<li><a href="/item/list?category=bottom&category2=트레이닝&page=1">트레이닝</a></li>
				</ul>
			</li>
			<li><a href="/item/list?category=outer&category2=&page=1">OUTER</a>
				<ul>
					<li><a href="/item/list?category=outer&category2=후드&page=1">후드</a></li>
					<li><a href="/item/list?category=outer&category2=코트&page=1">코트</a></li>
					<li><a href="/item/list?category=outer&category2=패딩&page=1">패딩</a></li>
				</ul>
			</li>
			<li><a href="/item/list?category=shoes&category2=&page=1">SHOES</a>
				<ul>
					<li><a href="/item/list?category=shoes&category2=운동화&page=1">운동화</a></li>
					<li><a href="/item/list?category=shoes&category2=슬리퍼&page=1">슬리퍼</a></li>
					<li><a href="/item/list?category=shoes&category2=구두&page=1">구두</a></li>
				</ul>
			</li>
			<li><a href="/item/list?category=acc&category2=&page=1">ACC</a>
				<ul>
					<li><a href="/item/list?category=acc&category2=모자&page=1">모자</a></li>
					<li><a href="/item/list?category=acc&category2=가방&page=1">가방</a></li>
					<li><a href="/item/list?category=acc&category2=쥬얼리&page=1">쥬얼리</a></li>
				</ul>
			</li>
		</ul>
	</nav>
</body>
</html>