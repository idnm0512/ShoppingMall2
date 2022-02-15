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
	
	<!-- 카카오 로그인을 사용하기 위한 코드 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

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
			height: 100px;
		}
		
		/* input 태그의 높이와 넓이 */
		#form_table input {
			width: 200px;
			height: 30px;
		}
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/userHeader.jsp" %>
	</header>
	
	<!-- 로그인 부분 -->
	<main>
		<h1>로그인</h1>
		
		<div id="form_div_outer">
			<div id="form_div_inner">
				<!-- form으로 감싸야 엔터가 눌림 -->
				<form>
					<table id="form_table">
				        <tr>
				        	<th>아이디</th>
				        	<td>
					        	<input type="text" id="id" name="id">
				        	</td>
				        </tr>
			        	<tr>
			         		<th>비밀번호</th>
			         		<td>
			         			<input type="password" id="pwd" name="pwd">
			         		</td>
			      		</tr>
			      	</table>
			      	<input type="checkbox" id="save_check">
			      	<label>아이디/비밀번호 저장</label>
			      	<button class="login_btn">로그인</button>
				</form>
				
				<br>
				
				<a href="/login/findId">아이디 찾기</a> /
				<a href="/login/findPwd">비밀번호 찾기</a>
				
				<br><br>
				
				<!-- 카카오 로그인 -->
				<div>
					<a href="javascript:kakaoLogin()">
					    <img src="/resources/img/kakao_login.png" style="width: 250px; height: 60px;">
					</a>
				</div>
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
		const login_form = $('#login_form');
		const id = $('#id');
		const pwd = $('#pwd');
		const save_check = $('#save_check');
		
		// 아이디/비밀번호 저장 Start
		// 페이지 진입 시 바로 실행
		$(function() {
			// 페이지 진입 시 바로 실행되는 메서드
			fnInit();
			
			let alert_result = '<c:out value="${result}"/>';
			
			checkAlert(alert_result);
			
			function checkAlert(alert_result) {
				if (alert_result === 'join success') {
					alert('회원가입이 완료되었습니다.');
				}
			}
		});
		
		// 페이지 진입 시 바로 실행되는 메서드
		function fnInit() {
			const cookieId = getCookie('cookieId');
			const cookiePwd = getCookie('cookiePwd');
			
			// 쿠키값이 있는 경우
			if (cookieId != '') {
				id.val(cookieId);
				pwd.val(cookiePwd);
				save_check.attr('checked', true);
			}
		}
		
		// 쿠키값을 저장하는 메서드
		function setCookie(cookieName, value, exdays) {
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + exdays);    // 쿠키값을 저장할 기간을 정해줌
			
			var cookieValue = escape(value) + ((exdays==null) ? '' : '; expires=' + exdate.toGMTString());
			document.cookie = cookieName + '=' + cookieValue;
		}
		
		// 쿠키값을 조회하는 메서드
		function getCookie(cookieName) {
			cookieName = cookieName + '=';
			
			var cookieData = document.cookie;
			var start = cookieData.indexOf(cookieName);
			var cookieValue = '';
			
			if (start != -1)
				start += cookieName.length;
			
			var end = cookieData.indexOf(';', start);
			
			if (end == -1)
				end = cookieData.length;
			
			cookieValue = cookieData.substring(start, end);
			
			return unescape(cookieValue);
		}
		
		// 쿠키값을 삭제하는 메서드
		function deleteCookie(cookieName) {
			var expireDate = new Date();
			expireDate.setDate(expireDate.getDate() - 1); // 쿠키값을 저장할 기간을 삭제
			
			document.cookie = cookieName + '= ' + '; expires=' + expireDate.toGMTString();
		}
		// 아이디/비밀번호 저장 End
		
		// 로그인 버튼 클릭 시
		$('.login_btn').on('click', function(e){
			e.preventDefault();
			
			// 빈칸 예외처리 Start
			if (id.val().trim() == '') {
				alert('아이디를 입력해주세요.');
				id.val('');
				id.focus();
				return false;
			}
			
			if (pwd.val().trim() == '') {
				alert('비밀번호를 입력해주세요.');
				pwd.val('');
				pwd.focus();
				return false;
			}
			// 빈칸 예외처리 End
			
			// 로그인 (아이디, 패스워드 체크) Start
			$.ajax({
				type : 'post',
				url : '/login',
				data : { 'id' : id.val() , 'pwd' : pwd.val() },
				success : function(data) {
					console.log(data);
					
					if (data == '') {
						alert('아이디 혹은 비밀번호를 확인해주세요.');
						id.val('');
						pwd.val('');
						return false;
					} else {
						// 아이디/비밀번호 저장
						if (save_check.is(':checked')) {
							setCookie('cookieId', id.val(), 7);
							setCookie('cookiePwd', pwd.val(), 7);
						} else {
							deleteCookie('cookieId');
							deleteCookie('cookiePwd');
						}
						alert('로그인이 완료되었습니다.');
						window.location = '/main';
					}
				},
				error : function(error) {
					alert('응답실패 : ' + error);
					console.log(error);
				}
			});
			// 로그인 (아이디, 패스워드 체크) End
		});
		
		// 카카오 로그인 Start
		Kakao.init('87266bf7e892392c37bf9742aaffa4cb'); // 발급받은 키 중 javascript키를 사용해준다.
		console.log(Kakao.isInitialized()); // sdk초기화여부판단
		
		function kakaoLogin() {
		    Kakao.Auth.authorize({
		    	redirectUri: 'http://localhost:8090/login/kakaoLogin'
		    });
		}
		// 카카오 로그인 End
	</script>
</body>
</html>