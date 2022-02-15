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
			margin-left: 20%;
			margin-right: 20%;
		}

		/* h1 글씨 가운데 정렬 */
		h1 {
			text-align: center;
			line-height: 300px;
		}
		
		/* 실질적인 form 부분 outer */
		#form_div_outer {
			border: solid 1px black;
			height: 800px;
			text-align: center;
		}
		
		/* 실질적인 form 부분 inner */
		#form_div_inner {
			display: inline-block;
		}
		
		/* form 내부의 table 높이 */
		#form_table {
			height: 600px;
		}
		
		/* input 태그의 높이와 넓이 */
		#form_table input {
			width: 300px;
			height: 30px;
		}
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/adminHeader.jsp" %>
	</header>
	
	<main>
		<h1>문의관리 답변작성</h1>
		
		<div id="form_div_outer">
			<div id="form_div_inner">
				<form id="qnaMgtAnswer_form" action="/qnaMgt/writeAnswer" method="post">
					<table id="form_table">
						<tr>
							<th>제목</th>
							<td>
								<input type="text" id="title" name="title" value="Re: ${qnaDetailInfo.title }" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>
								<input type="text" id="writer" name="writer" value="관리자" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea style="resize: none;" rows="30" cols="50" id="content" name="content">>> ${qnaDetailInfo.content }</textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" name="state" value="${qnaDetailInfo.state }">
					<input type="hidden" name="page" value="${page }">
					<input type="hidden" name="no" value="${qnaDetailInfo.no }">
					<button class="qnaMgtAnswer_btn">답변작성</button>
					<button class="cancel_btn">취소</button>
				</form>
			</div>
		</div>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<script>
		const qnaMgtAnswer_form = $('#qnaMgtAnswer_form');
		const title = $('#title');
		const writer = $('#writer');
		const content = $('#content');
		
		// 답변작성 클릭 시 Start
		$('.qnaMgtAnswer_btn').on('click', function(e){
			e.preventDefault();
			
			// 빈칸 예외처리 Start
			if (content.val().trim() == "") {
				alert("내용을 입력해주세요.");
				content.val('');
				content.focus();
				return false;
			}
			// 빈칸 예외처리 End
			
			qnaMgtAnswer_form.submit();
		});
		// 답변작성 버튼 클릭 시 End
		
		// 취소 버튼 클릭 시
		$('.cancel_btn').on('click', function(e){
			e.preventDefault();
			
			qnaMgtAnswer_form.find(title).remove();
			qnaMgtAnswer_form.find(writer).remove();
			qnaMgtAnswer_form.find(content).remove();
			qnaMgtAnswer_form.attr("action", "/qnaMgt/detail");
			qnaMgtAnswer_form.attr("method", "get");
			qnaMgtAnswer_form.submit();
		});
	</script>
</body>
</html>