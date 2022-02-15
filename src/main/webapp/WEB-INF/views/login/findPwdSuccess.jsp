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
			margin-left: 25%;
			margin-right: 25%;
		}

		/* h1 글씨 가운데 정렬 */
		h1 {
			text-align: center;
			line-height: 300px;
		}
		
		/* 실질적인 form 부분 outer */
		#form_div_outer {
			border: solid 1px black;
			height: 500px;
			text-align: center;
		}
		
		/* 실질적인 form 부분 inner */
		#form_div_inner {
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
		<h1>비밀번호 찾기</h1>
		
		<div id="form_div_outer">
			<div id="form_div_inner">
				<div>
					회원님의 임시 비밀번호는 ${tempPwd } 입니다.
				</div>
				<br>
				<a href="/login">로그인</a>
			</div>
		</div>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<!-- 푸터 -->
	<footer>
		<%@include file = "/resources/footer/footer.jsp" %>
	</footer>
</body>
</html>