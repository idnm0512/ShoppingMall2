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
		/* div 가운데 정렬 */
		.admin_login_div_outer {
			padding-top: 200px;
			text-align: center;
		}
		
		/* div 가운데 정렬 */
		.admin_login_div_inner {
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
	<div class="admin_login_div_outer">
		<div class="admin_login_div_inner">
			<h1>관리자 로그인</h1>
			
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
				<button class="login_btn">로그인</button>
			</form>
		</div>
	</div>
	
	<script>
		const id = $('#id');
		const pwd = $('#pwd');
	
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
				url : '/adminLogin',
				data : { 'id' : id.val(), 'pwd' : pwd.val() },
				success : function(data) {
					console.log(data);
					
					if (data == '') {
						alert('아이디 혹은 비밀번호를 확인해주세요.');
						id.val('');
						pwd.val('');
						return false;
					} else {
						alert('로그인이 완료되었습니다.');
						window.location = '/userMgt/list?keyword=&page=1';
					}
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
			// 로그인 (아이디, 패스워드 체크) End
		});
	</script>
</body>
</html>