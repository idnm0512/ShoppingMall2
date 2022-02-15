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
		/* 전체적으로 마진 25% 주기 */
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
			height: 200px;
		}
		
		/* input 태그의 높이와 넓이 */
		.type_text {
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
		<h1>비밀번호 찾기</h1>
		
		<div id="form_div_outer">
			<div id="form_div_inner">
				<form id="find_pwd_form" action="/login/findPwd" method="post">
					<table id="form_table">
				        <tr>
				        	<th>아이디</th>
				        	<td>
					        	<input type="text" id="id" class="type_text" name="id">
				        	</td>
				        </tr>
			        	<tr>
							<th>이메일</th>
							<td>
								<input type="text" id="email" class="type_text" name="email">
								<div id="email_check"></div>
							</td>
							<td>
								<input type="button" onclick="sendMail();" value="인증번호받기">
							</td>
						</tr>
						<tr>
							<th>인증번호</th>
							<td>
								<input type="text" id="email_check_num" class="type_text" name="email_check_num">
							</td>
							<td>
								<input type="button" onclick="emailNumCheck();" value="인증번호확인">
							</td>
						</tr>
			      	</table>
			      	<button class="find_pwd_btn">비밀번호 찾기</button>
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
		// 페이지 진입 시 바로 실행
		$(function() {
		    let result = '${result}';
		    
		 	// 페이지 진입 시 바로 실행되는 메서드
		    findCheck(result);
		    
		    function findCheck(result) {
		    	if (result === 'fail') {
		            alert('아이디 혹은 이메일을 확인해주세요.');
		        }
		    }
		});
		
		const find_pwd_form = $('#find_pwd_form');
		const email = $('#email');
		const email_check_num = $('#email_check_num');
		
		// 메일로 보낸 인증번호(난수)를 넣을 변수 선언
		let check_num = '';
		
		// 이메일 인증을 했는지 확인하기 위한 변수 선언
		let email_check_flag = false;
		
		// 이메일 전송 Start (인증번호받기 클릭 시)
		function sendMail() {
			// 이메일 빈칸 예외처리 Start
			if (email.val().trim() == '') {
				alert('이메일을 입력해주세요.');
				email.val('');
				email.focus();
				return false;
			}
			// 이메일 빈칸 예외처리 End
			
			// 이메일 패턴 예외처리 Start
			let email_check = $('#email_check');
			let email_pattern = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
			
			if (!email_pattern.test(email.val())) {
				email_check.text('양식을 지켜주세요. ex) abc123@gmail.com');
				email_check.css('color', 'red');
				email.val('');
				return false;
			} else {
				email_check.text('');
			}
			// 이메일 패턴 예외처리 End
			
			alert('확인을 누른 뒤, 잠시만 기다려주세요.');
			
			$.ajax({
				type : 'post',
				url : '/sendMail',
				data : { 'email' : email.val() },
				success : function(data) {
					console.log(data);
					
					// 생성하고 메일로 보낸 인증번호(난수)를 선언한 변수에 넣기
					check_num = data;
					
					alert('이메일 인증번호가 발송되었습니다.');
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		}
		// 이메일 전송 End
		
		// 인증번호 확인 Start (인증번호확인 클릭 시)
		function emailNumCheck() {
			// 인증번호 빈칸 예외처리 Start
			if (email_check_num.val().trim() == '') {
				alert('이메일 인증번호를 입력해주세요.');
				email_check_num.val('');
				email_check_num.focus();
				return false;
			}
			// 인증번호 빈칸 예외처리 End
			
			// 인증번호 일치
			const input_num = email_check_num.val();
			
			if (input_num == check_num) {
				alert('인증번호가 일치합니다.');
				email_check_flag = true;
			} else {
				alert('인증번호가 일치하지 않습니다.');
				email_check_flag = false;
				email_check_num.val('');
				return false;
			}
		}
		// 인증번호 확인 End
		
		// 비밀번호 찾기 버튼 클릭시
		$('.find_pwd_btn').on('click', function(e){
			e.preventDefault();

			const id = $('#id');
			
			// 빈칸 예외처리 Start
			if (id.val().trim() == '') {
				alert('아이디를 입력해주세요.');
				id.val('');
				id.focus();
				return false;
			}
			
			if (email.val().trim() == '') {
				alert('이메일을 입력해주세요.');
				email.val('');
				email.focus();
				return false;
			}
			// 빈칸 예외처리 End
			
			// 이메일 인증을 했는지 확인
			if (!email_check_flag) {
				alert("이메일 인증을 해주세요.");
				email_check_num.val('');
				email_check_num.focus();
				return false;
			}
			
			find_pwd_form.submit();
		});
	</script>
</body>
</html>