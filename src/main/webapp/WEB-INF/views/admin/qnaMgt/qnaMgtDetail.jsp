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
		<h1>문의관리</h1>
		
		<table>
			<tr>
				<th>제목</th>
				<td>${qnaDetailInfo.title }</td>
				<th>답변상태</th>
				<c:set var="state" value="${qnaDetailInfo.state }"/>
				<c:choose>
					<c:when test="${state == false }">
						<td style="color: red;">미답변</td>
					</c:when>
					<c:otherwise>
						<td style="color: green;">답변완료</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${qnaDetailInfo.writer }</td>
				<th>작성일</th>
				<td>
				    <fmt:parseDate value="${qnaDetailInfo.update_date }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
				    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
				</td>
			</tr>
		</table>
		
		<br>
		
		<div id="content_div_outer">
			<div id="content_div_inner">
				${qnaDetailInfo.content }
				
				<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시
				<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시<br>임시
			</div>
		</div>
		
		<br>
		
		<form id="qnaMgt_form" method="get">
			<input type="hidden" name="state" value="${qnaDetailInfo.state }">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" id="no" name="no" value="${qnaDetailInfo.no }">
			<button class="qnaMgtList_btn" style="margin-left: 10%;">목록</button>
			<button class="qnaMgtAnswer_btn">답변</button>
			<c:if test="${state == false }">
				<button class="qnaMgtDelete_btn">삭제</button>
			</c:if>
		</form>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<script>
		const qnaMgt_form = $('#qnaMgt_form');
		
		// 목록 버튼 클릭 -> 문의관리 페이지 진입
		$('.qnaMgtList_btn').on('click', function(e){
			e.preventDefault();
			
			qnaMgt_form.find('#no').remove();
			qnaMgt_form.attr('action', '/qnaMgt/list');
			qnaMgt_form.submit();
		});
		
		// 답변 버튼 클릭 -> 문의관리 답변 페이지 진입
		$('.qnaMgtAnswer_btn').on('click', function(e){
			e.preventDefault();
			
			const state = '${qnaDetailInfo.state}';
			
			// 답변상태 확인
			if (state == 'true') {
				alert('답변이 완료된 문의입니다.');
				return false;
			} else {
				qnaMgt_form.attr('action', '/qnaMgt/writeAnswer');
				qnaMgt_form.submit();
			}
		});
		
		// 삭제 버튼 클릭
		$('.qnaMgtDelete_btn').on('click', function(e){
			e.preventDefault();

			qnaMgt_form.attr('action', '/qnaMgt/delete');
			qnaMgt_form.attr('method', 'post');
			qnaMgt_form.submit();
		});
	</script>
</body>
</html>