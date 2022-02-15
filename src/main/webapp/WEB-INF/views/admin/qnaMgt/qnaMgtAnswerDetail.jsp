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
		<%@include file = "/resources/header/adminHeader.jsp" %>
	</header>
	
	<main>
		<h1>문의관리 답변</h1>
		
		<table>
			<tr>
				<th>제목</th>
				<td>${qnaDetailInfo.re_title }</td>
				<th>번호</th>
				<td>${qnaDetailInfo.re_no }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${qnaDetailInfo.re_writer }</td>
				<th>작성일</th>
				<td>
				    <fmt:parseDate value="${qnaDetailInfo.re_update_date }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
				    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
				</td>
			</tr>
		</table>
		
		<br>
		
		<div id="content_div_outer">
			<div id="content_div_inner">
				${qnaDetailInfo.re_content }
				
				<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시
				<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시
			</div>
		</div>
		
		<br>
		
		<form id="qnaMgtAnswer_form" method="get">
			<input type="hidden" name="state" value="true">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" id="re_no" name="re_no" value="${qnaDetailInfo.re_no }">
			<button class="qnaMgtList_btn" style="margin-left: 10%;">목록</button>
			<button class="qnaMgtAnswerModify_btn">수정</button>
		</form>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<script>
		$(function(){
			let alert_result = '<c:out value="${result}"/>';
			
			checkAlert(alert_result);
			
			function checkAlert(alert_result) {
				if (alert_result === 'modify success') {
					alert('수정이 완료되었습니다.');
				}
			}
		});
		
		const qnaMgtAnswer_form = $('#qnaMgtAnswer_form');
		
		// 목록 버튼 클릭 -> 문의관리 페이지 진입
		$('.qnaMgtList_btn').on('click', function(e){
			e.preventDefault();
			
			qnaMgtAnswer_form.find('#re_no').remove();
			qnaMgtAnswer_form.attr('action', '/qnaMgt/list');
			qnaMgtAnswer_form.submit();
		});
		
		// 수정 버튼 클릭 -> 문의관리 답변수정 페이지 진입
		$('.qnaMgtAnswerModify_btn').on('click', function(e){
			e.preventDefault();
			
			qnaMgtAnswer_form.attr('action', '/qnaMgt/answerModify');
			qnaMgtAnswer_form.submit();
		});
	</script>
</body>
</html>