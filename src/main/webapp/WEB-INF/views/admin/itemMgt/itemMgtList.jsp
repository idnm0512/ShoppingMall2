<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>

	<script
		src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous"></script>
		
	<style type="text/css">
		/* 전체적인 마진 */
		main {
			margin-left: 15%;
			margin-right: 15%;
		}

		/* h1 글씨 가운데 정렬 */
		h1 {
			text-align: center;
			line-height: 300px;
		}
		
		.div_outer {
			text-align: center;
		}
		
		.div_inner {
			display: inline-block;
		}
		
		/* 페이징 관련 Start */
		.pageInfo_div {
			margin-top: 50px;
			text-align: center;
		}
		
		.pageInfo_ul {
		    list-style : none;
			display: inline-block; 
		}
		
		.pageInfo_ul li {
		    float: left;
		    font-size: 15px;
		    margin-left: 15px;
		    padding: 10px;
		}
		
		.pageInfo_btn:hover {
			background-color: #efefef;
		}
		
		.active {
			background-color: #efefef;
		}
		/* 페이징 관련 End */
		
		#category_menu_div {
			height: 35px;
			line-height: 35px;
			text-align: center;
			border-bottom: solid 1px #efefef;
			margin-bottom: 50px;
		}
		
		#category_menu_div a {
			font-size: 14px;
		}
		
		#category_menu_div > ul > li {
			float: left;
			width: 80px;
		}
		
		#category_menu_div > ul > li > ul {
			display: none;
		}
		
		#category_menu_div > ul > li:hover > ul {
			display: block;
			position: absolute;
			min-width: 80px;
		}
		
		#category_menu_div > ul > li > ul > li:hover {
			background: #efefef;
			transition: ease 0.2s;
		}
		
		#category_menu_div > ul > li > ul > li {
			background: white;
		}
		
		.item_list_div {
			float: left;
			margin: 40px 20px 0 20px;
		}
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/adminHeader.jsp" %>
	</header>
	
	<main>
		<h1>상품관리</h1>
		
		<div class="div_outer">
			<div class="div_inner">
				<div id="category_menu_div">
					<ul>
						<li><a href="/itemMgt/list?category=&category2=&page=1">전체</a></li>
						<li><a href="/itemMgt/list?category=top&category2=&page=1">TOP</a>
							<ul>
								<li><a href="/itemMgt/list?category=top&category2=반팔&page=1">반팔</a></li>
								<li><a href="/itemMgt/list?category=top&category2=긴팔&page=1">긴팔</a></li>
								<li><a href="/itemMgt/list?category=top&category2=맨투맨&page=1">맨투맨</a></li>
							</ul>
						</li>
						<li><a href="/itemMgt/list?category=bottom&category2=&page=1">BOTTOM</a>
							<ul>
								<li><a href="/itemMgt/list?category=bottom&category2=청바지&page=1">청바지</a></li>
								<li><a href="/itemMgt/list?category=bottom&category2=슬랙스&page=1">슬랙스</a></li>
								<li><a href="/itemMgt/list?category=bottom&category2=트레이닝&page=1">트레이닝</a></li>
							</ul>
						</li>
						<li><a href="/itemMgt/list?category=outer&category2=&page=1">OUTER</a>
							<ul>
								<li><a href="/itemMgt/list?category=outer&category2=후드&page=1">후드</a></li>
								<li><a href="/itemMgt/list?category=outer&category2=코트&page=1">코트</a></li>
								<li><a href="/itemMgt/list?category=outer&category2=패딩&page=1">패딩</a></li>
							</ul>
						</li>
						<li><a href="/itemMgt/list?category=shoes&category2=&page=1">SHOES</a>
							<ul>
								<li><a href="/itemMgt/list?category=shoes&category2=운동화&page=1">운동화</a></li>
								<li><a href="/itemMgt/list?category=shoes&category2=슬리퍼&page=1">슬리퍼</a></li>
								<li><a href="/itemMgt/list?category=shoes&category2=구두&page=1">구두</a></li>
							</ul>
						</li>
						<li><a href="/itemMgt/list?category=acc&category2=&page=1">ACC</a>
							<ul>
								<li><a href="/itemMgt/list?category=acc&category2=모자&page=1">모자</a></li>
								<li><a href="/itemMgt/list?category=acc&category2=가방&page=1">가방</a></li>
								<li><a href="/itemMgt/list?category=acc&category2=쥬얼리&page=1">쥬얼리</a></li>
							</ul>
						</li>
						<li><button class="item_insert_btn">상품등록</button></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="div_outer">
			<div class="div_inner">
				<c:forEach items="${itemListInfo }" var="list">
					<div class="item_list_div">
						<div><img style="width: 200px; height: 200px" src="/resources/thumbnail/${list.thumbnail }"></div>
						<div>상품번호 : ${list.no }</div>
						<div>상품명 : ${list.name }</div>
						<div>가격 : ${list.price }</div>
						<div>할인율 : ${list.discount }</div>
						<div>카테고리 : ${list.category }</div>
						<div>카테고리2 : ${list.category2 }</div>
						<div>
							<a class="item_modify_a" style="color: blue;" href="${list.no }">수정</a> /
							<a style="color: red;" href="javascript:itemMgtDelete('${list.no }', '${list.thumbnail }');">삭제</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="div_outer">
			<div class="div_inner">
				<!-- 번호 페이지 -->
				<div class="pageInfo_div">
					<ul class="pageInfo_ul">
						<!-- 이전페이지 버튼 -->
						<c:if test="${pageMaker.prev }">
						    <li class="pageInfo_btn prev">
						    	<a href="${pageMaker.startPage-1 }">이전</a>
						    </li>
						</c:if>
						<!-- 각 번호 페이지 버튼 -->
						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
						    <li class="pageInfo_btn ${pageMaker.page == num ? "active":"" }">
						    	<a href="${num }">${num }</a>
						    </li>
						</c:forEach>
						<!-- 다음페이지 버튼 -->
						<c:if test="${pageMaker.next }">
						    <li class="pageInfo_btn next">
						    	<a href="${pageMaker.endPage+1 }">다음</a>
						    </li>
						</c:if>
					</ul>
				</div>
				
				<!-- 페이지 이동 시 필요한 정보 전송 -->
				<form id="pageInfo_form" method="get">
					<input type="hidden" name="category" value="${category}">
					<input type="hidden" name="category2" value="${category2}">
					<input type="hidden" name="page" value="${pageMaker.page }">
				</form>
			</div>
		</div>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<script>
		$(function(){
			let alert_result = '<c:out value="${result}"/>';
			
			checkAlert(alert_result);
			
			function checkAlert(alert_result) {
				if (alert_result === 'write success') {
					alert('등록이 완료되었습니다.');
				}
				
				if (alert_result === 'modify success') {
					alert('수정이 완료되었습니다.');
				}
			}
		});
		
		const pageInfo_form = $('#pageInfo_form');
		
		// 상품등록 클릭
		$('.item_insert_btn').on('click', function(e){
			e.preventDefault();
			
			pageInfo_form.attr('action', '/itemMgt/insert');
			pageInfo_form.submit();
		});
		// 상품등록 클릭
		
		// 상품수정 클릭
		$('.item_modify_a').on('click', function(e){
			e.preventDefault();
			
			pageInfo_form.append('<input type="hidden" name="no" value="'+ $(this).attr('href') +'">');
			pageInfo_form.attr('action', '/itemMgt/modify');
			pageInfo_form.submit();
		});
		// 상품수정 클릭
		
		// 상품삭제
		function itemMgtDelete(item_no, thumbnail) {
			const confirm_result = confirm('상품번호 : ' + item_no +
										   '\n해당 상품을 삭제하시겠습니까?');
			
			if (!confirm_result) return;
			
			$.ajax({
				type : 'post',
				url : '/itemMgt/delete',
				data : { 'no' : item_no, 'thumbnail' : thumbnail },
				success : function(data) {
					alert('삭제가 완료되었습니다.');
					pageInfo_form.find('input[name="page"]').val(1);
					pageInfo_form.submit();
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		}
		// 상품삭제
		
		// 번호 클릭 -> 번호 페이지 이동
		$('.pageInfo_ul a').on('click', function(e){
		    e.preventDefault();
		    
		    pageInfo_form.find('input[name="page"]').val($(this).attr('href'));
		 	// form의 action속성을 생략하면 해당 페이지를 요청할 때와 같은 방식으로 처리된다.
		    // f.attr("action", "/itemMgt/list");
		    pageInfo_form.submit();
		});
	</script>
</body>
</html>