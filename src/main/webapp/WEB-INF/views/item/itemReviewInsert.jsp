<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리뷰 작성하기</title>

	<script
		src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous"></script>
	
	<style>
		.label_scope {
		   width: 150px;
		   height: 150px;
		}
		
		.img_content {
		    outline: 2px dashed #4B89DC;
		    transition: all 0.2s ease-in-out;
		    width: 150px;
		    height: 150px;
		}
		
		.img_content:hover {
		   filter: brightness(80%);
		   outline-offset: -20px;
		   cursor: pointer;
		}
	</style>
</head>
<body>
	<form id="item_review_form" action="/item/insertReview" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품명</th>
				<td>${itemDetailInfo.name }</td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td>
					<span style="text-decoration: line-through; font-size: 14px; color: gray;">${itemDetailInfo.price }원</span>
					<span style="font-size: 17px;">
						<fmt:formatNumber type="number" pattern="0" var="discountedPrice"
							value="${itemDetailInfo.discountedPrice }"/>${discountedPrice }원
					</span>
					<span style="font-size: 14px;">(<span style="color: red;">${itemDetailInfo.discount }%</span>할인)</span>
				</td>
			</tr>
			<tr>
				<th>상품이미지</th>
				<td><img style="width: 200px; height: 200px;" src="${itemDetailInfo.thumbnail }"></td>
			</tr>
			<tr>
				<th>평점</th>
				<td>
					<select name="grade" style="width: 40px; height: 30px;">
						<option value="5">5</option>
						<option value="4">4</option>
						<option value="3">3</option>
						<option value="2">2</option>
						<option value="1">1</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>리뷰</th>
				<td>
					<textarea style="resize: none;" rows="10" cols="50" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
					<label class="label_scope" for="review_img">
						<img class="img_content" src="https://s3.ap-northeast-2.amazonaws.com/jaeho-bucket/img/white.png" id="review_img_view"/>
					</label>
					<input type="file" id="review_img" name="reviewImgFile" accept="image/*" style="display: none;" onchange="preview(this, 'review_img_view')">
				</td>
			</tr>
		</table>
		
		<br><br>
		
		<input type="hidden" name="itemNo" value="${itemDetailInfo.no }">
		<input type="hidden" name="userId" value="${sessionScope.id }">
		<button class="item_review_btn">상품 리뷰 등록하기</button>
	</form>
	
	<script>
		// 이미지 미리보기
		function preview(file, id) {
		    let img = document.querySelector('#' + id);
		
		    let reader = new FileReader();
		    
		    reader.onload = function(e) {
		        img.setAttribute('src', e.target.result.toString());
		    }
		    
		    reader.readAsDataURL(file.files[0]);
		}
	
		$('.item_review_btn').on('click', function(e){
			e.preventDefault();
			
			const _itemReviewForm = $('#item_review_form')[0];
			const _formData = new FormData(_itemReviewForm);
			
			$.ajax({
				type : 'post',
				url : '/item/insertReview',
				enctype : 'multipart/form-data',
				data : _formData,
		        contentType : false,
				processData : false,
				success : function() {
					alert('리뷰 등록이 완료되었습니다.');
					opener.parent.location.reload();
					window.close();
				},
				error : function(error) {
					alert('응답실패 : ' + error);
					console.log(error);
				}
			});
		});
	</script>
</body>
</html>