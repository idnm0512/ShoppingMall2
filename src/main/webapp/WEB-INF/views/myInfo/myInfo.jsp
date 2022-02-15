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
			height: 1000px;
			text-align: center;
		}
		
		/* 실질적인 form 부분 inner */
		#form_div_inner {
			display: inline-block;
		}
		
		/* form 내부의 table 높이 */
		#form_table {
			height: 750px;
		}
		
		/* input type_text 태그의 높이와 넓이 */
		.type_text {
			width: 250px;
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
		<h1>내 정보 보기</h1>
		
		<div id="form_div_outer">
			<div id="form_div_inner">
				<form id="myInfo_form" action="/myInfo/modifyMyInfo" method="post">
					<table id="form_table">
				        <tr>
				        	<th>아이디</th>
				        	<td>
					        	<input type="text" class="type_text" name="id" value="${myInfo.id }" readonly="readonly">
				        	</td>
				        </tr>
			        	<tr>
			         		<th>비밀번호</th>
			         		<td>
			         			<input type="password" id="pwd" class="type_text" name="pwd">
			         			<div id="pwd_check"></div>
			         		</td>
			         		<td>
			         			<input type="button" onclick="modifyPwd();" value="비밀번호변경">
			         		</td>
			      		</tr>
						<tr>
					    	<th>비밀번호 확인</th>
					    	<td>
					    		<input type="password" id="pwd2" class="type_text">
					    		<div id="pwd2_check"></div>
					    	</td>
						</tr>
						<tr>
					    	<th>이름</th>
					    	<td>
					    		<input type="text" class="type_text" name="name" value="${myInfo.name }" readonly="readonly">
					    	</td>
						</tr>
						<tr>
					    	<th>주소</th>
					    	<td>
					    		<input type="text" id="address" class="type_text" name="address" value="${myInfo.address }" readonly="readonly">
					    		<br><br>
					    		<input type="text" id="address2" class="type_text" name="address2" value="${myInfo.address2 }">
					    	</td>
					    	<td>
					    		<input type="button" onclick="jusoPopup();" value="주소찾기">
					    	</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>
								<input type="text" id="email" class="type_text" name="email" value="${myInfo.email }">
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
						<tr>
					    	<th>휴대전화</th>
					    	<td>
					    		<input type="text" id="phone" class="type_text" name="phone" value="${myInfo.phone }">
					    		<div id="phone_check"></div>
					    	</td>
						</tr>
						<tr>
					    	<th>일반전화</th>
					    	<td>
					    		<input type="text" id="phone2" class="type_text" name="phone2" value="${myInfo.phone2 }">
					    		<div id="phone2_check"></div>
					    	</td>
						</tr>
						<tr>
					    	<th>생년월일</th>
					    	<td>
					    		<input type="text" id="birth" class="type_text" name="birth" value="${myInfo.birth }">
					    		<div id="birth_check"></div>
					    	</td>
						</tr>
			   		</table>
			   		<button class="modify_btn">정보수정</button>
			   		<button class="delete_btn">회원탈퇴</button>
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
		$(function(){
			let alert_result = '<c:out value="${result}"/>';
			
			checkAlert(alert_result);
			
			function checkAlert(alert_result) {
				if (alert_result === 'infoModify success') {
					alert('정보수정이 완료되었습니다.');
				}
				
				if (alert_result === 'pwdModify success') {
					alert('비밀번호 변경이 완료되었습니다.');
				}
			}
		});
		
		const myInfo_form = $('#myInfo_form');
		const pwd = $('#pwd');
		const pwd2 = $('#pwd2');
		const address = $('#address');
		const address2 = $('#address2');
		const email = $('#email');
		const email_check_num = $('#email_check_num');
		const phone = $('#phone');
		
		// 메일로 보낸 인증번호(난수)를 넣을 변수 선언
		let check_num = '';
		
		// 이메일 인증을 했는지 확인하기 위한 변수 선언
		let email_check_flag = false;
		
		// 주소 팝업창 띄우기 Start (주소찾기 클릭 시)
		function jusoPopup() {
			// 주소검색을 수행할 팝업창을 호출합니다.
			// 호출된 페이지(jusopopup.jsp)에서
			// 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
			var pop = window.open("/jusoPopup",
								  "pop",
								  "width=570, height=420, scrollbars=yes, resizable=yes"); 
		}
		// 주소 팝업창 띄우기 End
		
		// 현재 페이지에 주소 입력 Start
		function jusoCallBack(roadAddrPart1, addrDetail) {
			// 주소 팝업창에서 입력한 주소 정보를 받아, 현재 페이지에 입력합니다.
			address.val(roadAddrPart1);
			address2.val(addrDetail);
		}
		// 현재 페이지에 주소 입력 End
		
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
			const email_check = $('#email_check');
			const email_pattern = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
			
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
		
		// 비밀번호 변경 Start
		function modifyPwd() {
			
			// 빈칸 예외처리 Start
			if (pwd.val().trim() == '') {
				alert('비밀번호를 입력해주세요.');
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
			
			window.location = '/myInfo/modifyPwd';
		}
		// 비밀번호 변경 End
		
		// 정보수정 버튼 클릭시 Start
		$(".modify_btn").on("click", function(e){
			e.preventDefault();
			
			// 회원의 현재 이메일 값 (now_email), 수정할 이메일 값 (modify_email)
			const now_email = '${myInfo.email}';
			const modify_email = email.val();
			
			// 빈칸 예외처리 Start
			if (pwd.val().trim() == '') {
				alert('비밀번호를 입력해주세요.');
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
			
			if (address.val().trim() == '') {
				alert('주소를 입력해주세요.');
				address.val('');
				address.focus();
				return false;
			}
			
			if (address2.val().trim() == '') {
				alert('나머지 주소를 입력해주세요.');
				address2.val('');
				address2.focus();
				return false;
			}
			
			if (email.val().trim() == '') {
				alert('이메일을 입력해주세요.');
				email.val('');
				email.focus();
				return false;
			}
			
			if (phone.val().trim() == '') {
				alert('휴대폰 번호를 입력해주세요.');
				phone.val('');
				phone.focus();
				return false;
			}
			// 빈칸 예외처리 End
			
			// '이메일을 수정했을 때' 이메일 인증을 했는지 확인
			// 회원의 현재 이메일 (now_email), 수정할 이메일 (modify_email)
			if (now_email == modify_email) { // 이메일 수정을 안할 시 true;
				email_check_flag = true;
			} else {
				if (!email_check_flag) {
					alert('이메일 인증을 해주세요.');
					email_check_num.val('');
					email_check_num.focus();
					return false;
				}
			}
			
			myInfo_form.submit();
		});
		// 정보수정 버튼 클릭시 End
		
		// 회원탈퇴 버튼 클릭 시 Start
		$(".delete_btn").on("click", function(e){
			e.preventDefault();
			
			// 빈칸 예외처리 Start
			if (pwd.val().trim() == '') {
				alert('비밀번호를 입력해주세요.');
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
			
			alert('회원탈퇴가 완료되었습니다.');
			myInfo_form.attr('action', '/myInfo/deleteUser');
			myInfo_form.submit();
		});
		// 회원탈퇴 버튼 클릭 시 End
		
		// 패스워드 예외처리 Start
		$('#pwd').blur(function(){
			const pwd_check = $('#pwd_check');
			const login_pwd = '${myInfo.pwd}';
			
			// 패스워드 패턴확인
			if (pwd.val() != login_pwd) {
				pwd_check.text('회원님의 비밀번호를 정확히 입력해주세요.');
				pwd_check.css('color', 'red');
				pwd.val('');
				return false;
			} else {
				pwd_check.text('');
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
					pwd2_check.css('color', 'green');
				}
			}
		});
		// 패스워드 예외처리 End
		
		// 휴대전화 예외처리 Start
		$('#phone').blur(function(){
			const phone_check = $('#phone_check');
			const phone_pattern = /^[0-9]{10,11}$/;
			
			if (!phone_pattern.test(phone.val())) {
				phone_check.text('-제외 숫자만 입력해주세요. ex) 01012345678');
				phone_check.css('color', 'red');
				phone.val('');
				return false;
			} else {
				phone_check.text('');
			}
		});
		// 휴대전화 예외처리 End
		
		// 일반전화 예외처리 Start
		$('#phone2').blur(function(){
			const phone2 = $('#phone2');
			const phone2_check = $('#phone2_check');
			const phone2_pattern = /^[0-9]{10,11}$/;
			
			if (!phone2_pattern.test(phone2.val())) {
				phone2_check.text('-제외 숫자만 입력해주세요. ex) 0212345678');
				phone2_check.css('color', 'red');
				phone2.val('');
				return false;
			} else {
				phone2_check.text('');
			}
		});
		// 일반전화 예외처리 End
		
		// 생년월일 예외처리 Start
		$('#birth').blur(function(){
			const birth = $('#birth');
			const birth_check = $('#birth_check');
			const birth_pattern = /^[0-9]{6}$/;
			
			if (!birth_pattern.test(birth.val())) {
				birth_check.text('숫자 6자리 입니다. ex) 940902');
				birth_check.css('color', 'red');
				birth.val('');
				return false;
			} else {
				birth_check.text('');
			}
		});
		// 생년월일 예외처리 End
	</script>
</body>
</html>