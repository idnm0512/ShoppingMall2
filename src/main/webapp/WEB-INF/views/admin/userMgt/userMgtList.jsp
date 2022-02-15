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
			margin-left: 5%;
			margin-right: 5%;
		}
		
		/* h1 글씨 가운데 정렬 */
		h1 {
			text-align: center;
			line-height: 300px;
		}
		
		/* 전체적인 테이블 크기와 정렬 */
		table {
			margin-left: auto;
		    margin-right: auto;
			border-collapse: collapse;
			width: 1300px;
			text-align: center;
		}
		
		/* 요소 높이와 글씨 크기 */
		td, th {
			border-bottom: solid 1px #efefef;
			height: 40px;
			font-size: 13px;
		}
		
		/* 회원 상태 부분에 마우스를 올렸을 때 */
		.user_list_td:hover {
			background: #efefef;
			transition: ease 0.2s;
		}
		
		/* 테이블 머리 배경색 */
		th {
			background: #efefef;
		}
		
		/* th 넓이 Start */
		.no_width {
			width: 5%;
		}
		
		.id_width {
			width: 7%;
		}
		
		.name_width {
			width: 5%;
		}
		
		.address_width {
			width: 27%;
		}
		
		.phone_width {
			width: 5%;
		}
		
		.phone2_width {
			width: 10%;
		}
		
		.email_width {
			width: 10%;
		}
		
		.birth_width {
			width: 5%;
		}
		
		.joindate_width {
			width: 10%;
		}

		.withdrawdate_width {
		    width: 5%;
		}
		
		.state_width {
			width: 5%;
		}
		/* th 넓이 End */
		
		/* 검색 부분 */
		.search_div_outer {
			text-align: center;
		}
		
		.search_div_inner {
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
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/adminHeader.jsp" %>
	</header>
	
	<main>
		<h1>회원관리</h1>
		
		<div class="search_div_outer">
		    <div class="search_div_inner">
		    	<!-- form으로 감싸야 엔터가 눌림 -->
		    	<form>
			        <input type="text" name="keyword" placeholder="아이디" value="${keyword }">
			        <button class="search_btn">검색</button>
		        </form>
		    </div>
		</div>
		
		<br>
		
		<table>
			<thead>
				<tr>
					<th class="no_width">번호</th>
					<th class="id_width">아이디</th>
					<th class="name_width">이름</th>
					<th class="address_width">주소</th>
					<th class="phone_width">휴대전화</th>
					<th class="phone2_width">일반전화</th>
					<th class="email_width">이메일</th>
					<th class="birth_width">생년월일</th>
					<th class="joindate_width">가입일</th>
					<th class="withdrawdate_width">탈퇴일</th>
					<th class="state_width">상태</th>
				</tr>
			</thead>
			<c:forEach items="${userListInfo }" var="list">
				<tr>
					<td>${list.no }</td>
					<td>${list.id }</td>
					<td>${list.name }</td>
					<td>${list.address } ${list.address2 }</td>
					<td>${list.phone }</td>
					<td>${list.phone2 }</td>
					<td>${list.email }</td>
					<td>${list.birth }</td>
					<td>
					    <fmt:parseDate value="${list.joindate }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
					    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
					</td>
					<td>
					    <fmt:parseDate value="${list.withdrawdate }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
					    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
					</td>
					<c:choose>
						<c:when test="${list.state == true}">
							<td class="user_list_td"><a style="color: green;" href="${list.state }" data-id="${list.id }">활성</a></td>
						</c:when>
						<c:otherwise>
							<td class="user_list_td"><a style="color: red;" href="${list.state }" data-id="${list.id }">비활성</a></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		
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
			<input type="hidden" name="keyword" value="${keyword }">
			<input type="hidden" name="page" value="${pageMaker.page }">
		</form>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<script>
		const pageInfo_form = $('#pageInfo_form');
		
		// 번호 클릭 -> 번호 페이지 이동
		$('.pageInfo_ul a').on('click', function(e){
		    e.preventDefault();
		    
		    pageInfo_form.find('input[name="page"]').val($(this).attr('href'));
		    // form의 action속성을 생략하면 해당 페이지를 요청할 때와 같은 방식으로 처리된다.
		    // f.attr("action", "/userMgt/list");
		    pageInfo_form.submit();
		});
		
		// 검색 버튼 클릭 시
		$('.search_btn').on('click', function(e){
		    e.preventDefault();
		    
		    let keyword = $('input[name="keyword"]').val();
		    
		    pageInfo_form.find('input[name="keyword"]').val(keyword);
		    pageInfo_form.find('input[name="page"]').val(1);
		    pageInfo_form.submit();
		});
		
		// 활성 / 비활성 클릭 시
		$('.user_list_td a').on('click', function(e){
			e.preventDefault();
			
			let id = $(this).data('id');
			let state = $(this).attr('href');
			
			$.ajax({
				type : 'post',
				url : '/userMgt/modifyUserState',
				data : { 'id' : id, 'state' : state },
				success : function(data) {
					console.log(data);
					
					window.location.reload();
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		});
	</script>
</body>
</html>