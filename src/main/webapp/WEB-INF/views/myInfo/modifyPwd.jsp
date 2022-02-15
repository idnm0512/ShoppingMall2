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
		
		/* form 내부의 table 높이 */
		#form_table {
			height: 130px;
		}
		
		/* input 태그의 높이와 넓이 */
		#form_table input {
			width: 200px;
			height: 30px;
		}
		
		/* 예외처리 글씨 크기 */
		td > div {
			font-size: 13px;
		}
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/userHeader.jsp" %>
	</header>
	
	<main>
		<h1>비밀번호 변경</h1>
		
		<div id="form_div_outer">
			<div id="form_div_inner">
				<form id="modifyPwd_form" action="/myInfo/modifyPwd" method="post">
					<table id="form_table">
						<tr>
							<th>새 비밀번호</th>
							<td>
								<input type="password" id="pwd" name="pwd">
								<div id="pwd_check"></div>
							</td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td>
								<input type="password" id="pwd2" name="pwd2">
								<div id="pwd2_check"></div>
							</td>
						</tr>
					</table>
					<input type="hidden" name="id" value="${sessionScope.id }">
					<button class="modifyPwd_btn">비밀번호변경</button>
				</form>
			</div>
		</div>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<!-- 푸터 -->
	<footer>
		<%@include file = "/resources/footer/footer.jsp" %>
	</footer>
	
	<script>
		const modifyPwd_form = $('#modifyPwd_form');
		const pwd = $('#pwd');
		const pwd2 = $('#pwd2');
		
		// 비밀번호 변경 버튼 클릭 시 Start
		$('.modifyPwd_btn').on('click', function(e){
			e.preventDefault();
			
			// 빈칸 예외처리 Start
			if (pwd.val().trim() == '') {
				alert('변경하실 비밀번호를 입력해주세요.');
				pwd.val('');
				pwd.focus();
				return false;
			}
			
			if (pwd2.val().trim() == '') {
				alert('비밀번호를 확인해주세요.');
				pwd2.val('');
				pwd2.focus();
				return false;
			}
			// 빈칸 예외처리 End
			
			modifyPwd_form.submit();
		});
		// 비밀번호 변경 버튼 클릭 시 End
		
		// 패스워드 예외처리 Start
		$('#pwd').blur(function(){
			const pwd_check = $("#pwd_check");
			const pwd_pattern = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,20}$/;
			
			// 패스워드 패턴확인
			if (!pwd_pattern.test(pwd.val())) {
				pwd_check.text('알파벳, 숫자, 특수문자 포함 8~20자 입니다.');
				pwd_check.css('color', 'red');
				pwd.val('');
				return false;
			} else {
				pwd_check.text('안전한 비밀번호 입니다.');
				pwd_check.css('color', 'green');
			}
		});
		
		// 패스워드 재입력
		$('#pwd2').blur(function(){
			const pwd2_check = $('#pwd2_check');
			
			// 패스워드 재입력 체크
			if (pwd2.val() != '') {
				if (pwd.val() != pwd2.val()) {
					pwd2_check.text('비밀번호가 일치하지 않습니다.');
					pwd2_check.css('color', 'red');
					pwd2.val('');
					return false;
				} else {
					pwd2_check.text('비밀번호가 일치합니다.');
					pwd2_check.css('color', 'green');
				}
			}
		});
		// 패스워드 예외처리 End
	</script>
</body>
</html>