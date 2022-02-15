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
	
	<style>
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
			border-top: solid 1px #efefef;
			border-bottom: solid 1px #efefef;
			height: 40px;
			font-size: 13px;
		}
		
		/* th 배경색 */
		th {
			background: #efefef;
			width: 10%;
		}
		
		/* td 왼쪽 정렬 */
		td {
			text-align: left;
		}
		
		/* 내용 div outer 가운데 정렬, 크기, 선 */
		#content_div_outer {
			margin-left: auto;
		    margin-right: auto;
			border-top: solid 1px #efefef;
			border-bottom: solid 1px #efefef;
			width: 1200px;
			text-align: center;
		}
		
		/* 내용 div inner */
		#content_div_inner {
			display: inline-block;
		}
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/userHeader.jsp" %>
	</header>
	
	<main>
		<h1>${commDetailInfo.category }</h1>
		
		<table>
			<tr>
				<th>제목</th>
				<td>${commDetailInfo.title }</td>
				<th>번호</th>
				<td>${commDetailInfo.no }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${commDetailInfo.writer }</td>
				<th>작성일</th>
				<td>
				    <fmt:parseDate value="${commDetailInfo.update_date }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
				    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
				</td>
			</tr>
		</table>
		
		<br>
		
		<div id="content_div_outer">
			<div id="content_div_inner">
				${commDetailInfo.content }
				
				임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>
				임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>
			</div>
		</div>
		
		<br>
		
		<form action="/community/list" method="get">
			<input type="hidden" name="category" value="${commDetailInfo.category }">
			<input type="hidden" name="page" value="${page }">
			<button style="margin-left: 10%;">목록</button>
		</form>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<!-- 푸터 -->
	<footer>
		<%@include file = "/resources/footer/footer.jsp" %>
	</footer>
</body>
</html>