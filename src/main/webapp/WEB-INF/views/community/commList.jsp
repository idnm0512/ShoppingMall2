<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아 옷사야되는데.. 디스핏!</title>

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
			width: 1200px;
			text-align: center;
		}
		
		/* 요소 높이와 글씨 크기 */
		td, th {
			border-bottom: solid 1px #efefef;
			height: 40px;
			font-size: 13px;
		}
		
		/* 테이블 머리 배경색 */
		th {
			background: #efefef;
		}
		
		/* th 넓이 Start */
		.no_width {
			width: 5%;
		}
		
		.category_width {
			width: 10%;
		}
		
		.title_width {
			width: 50%;
		}
		
		.writer_width {
			width: 10%;
		}
		
		.regdate_width {
			width: 10%;
		}
		/* th 넓이 End */
		
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
		<%@include file = "/resources/header/userHeader.jsp" %>
	</header>
	
	<main>
		<h1>${category }</h1>
		
		<table>
			<thead>
				<tr>
					<th class="no_width">번호</th>
					<th class="category_width">카테고리</th>
					<th class="title_width">제목</th>
					<th class="writer_width">작성자</th>
					<th class="regdate_width">작성일</th>
				</tr>
			</thead>
			<c:forEach items="${commListInfo }" var="list">
				<tr>
					<td>${list.no }</td>
					<td>${list.category }</td>
					<td style="text-align: left;"><a class="moveDetail" href="${list.no }">${list.title }</a></td>
					<td>${list.writer }</td>
					<td>
                        <fmt:parseDate value="${list.update_date }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
					    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- 번호 페이지 -->
		<div class="pageInfo_div">
			<ul class="pageInfo_ul">
				<!-- 이전페이지 버튼 -->
				<c:if test="${pageMaker.prev }">
				    <li class="pageInfo_btn prev">
				    	<a href="${pageMaker.startPage - 1 }">이전</a>
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
				    	<a href="${pageMaker.endPage + 1 }">다음</a>
				    </li>
				</c:if>
			</ul>
		</div>
		
		<!-- 페이지 이동 시 필요한 정보 전송 -->
		<form id="pageInfo_form" method="get">
			<input type="hidden" name="category" value="${category }">
			<input type="hidden" name="page" value="${pageMaker.page }">
		</form>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<!-- 푸터 -->
	<footer>
		<%@include file = "/resources/footer/footer.jsp" %>
	</footer>
	
	<script>
		const pageInfo_form = $('#pageInfo_form');
		
		// 제목 클릭 -> 해당 글 상세보기 페이지 이동
		$('.moveDetail').on('click', function(e){
			e.preventDefault();
			
			pageInfo_form.append('<input type="hidden" name="no" value="'+ $(this).attr("href") +'">');
			pageInfo_form.attr('action', '/community/detail');
			pageInfo_form.submit();
		});
		
		// 번호 클릭 -> 번호 페이지 이동
		$('.pageInfo_ul a').on('click', function(e){
		    e.preventDefault();
		    
		    pageInfo_form.find('input[name="page"]').val($(this).attr('href'));
		 	// form의 action속성을 생략하면 해당 페이지를 요청할 때와 같은 방식으로 처리된다.
		    // f.attr("action", "/community/list");
		    pageInfo_form.submit();
		});
	</script>
</body>
</html>