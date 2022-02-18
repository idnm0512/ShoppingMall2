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
		main {
			margin-left: 5%;
			margin-right: 5%;
		}
		
		h1 {
			text-align: center;
			line-height: 300px;
		}
		
		.div_outer {
			text-align: center;
		}
		
		.div_inner {
			display: inline-block;
		}
		
		.item_list_div {
			float: left;
			margin: 60px 20px 0 20px;
		}
		
		.img_content:hover {
		   filter: brightness(80%);
		   transition: ease 0.2s;
		   cursor: pointer;
		}
		
		/* 페이징 관련 Start */
		.pageInfo_div {
			margin-top: 50px;
			text-align: center;
		}
		
		.pageInfo_ul {
		    list-style : none;
			display: inline-block; 
		}
		
		.pageInfo_ul li {
		    float: left;
		    font-size: 15px;
		    margin-left: 15px;
		    padding: 10px;
		}
		
		.pageInfo_btn:hover {
			background-color: #efefef;
		}
		
		.active {
			background-color: #efefef;
		}
		/* 페이징 관련 End */
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/userHeader.jsp" %>
	</header>
	
	<main>
		<h1>관심상품</h1>
		
		<div class="div_outer">
			<div class="div_inner">
				<c:forEach items="${wishListInfo }" var="list">
					<div class="item_list_div">
						<div><a href="/item/detail?no=${list.item_no }"><img class="img_content" style="width: 300px; height: 400px" src="${list.thumbnail }"></a></div>
						
						<br>
						
						<div style="font-size: 18px;"><a href="/item/detail?no=${list.item_no }">${list.name }</a></div>
						
						<br>
						
						<div style="text-decoration: line-through; font-size: 15px; color: gray;">${list.price }원</div>
						
						<br>
						
						<div style="font-size: 18px;">
							<fmt:formatNumber type="number" pattern="0"
								value="${list.discounted_price }"/>원
						</div>
						<div style="font-size: 15px;">(<span style="color: red">${list.discount }%</span> 할인)</div>
						<div>
							<input type="checkbox" class="item_check" name="item_check" value="${list.wishlist_no }">
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<br><br>
		
		<div class="div_outer">
			<div class="div_inner">
				<button class="item_delete_btn">선택상품 삭제</button>
				<button class="item_all_delete_btn">관심상품 비우기</button>
			</div>
		</div>
		
		<div class="div_outer">
			<div class="div_inner">
				<!-- 번호 페이지 -->
				<div class="pageInfo_div">
					<ul class="pageInfo_ul">
						<!-- 이전페이지 버튼 -->
						<c:if test="${pageMaker.prev }">
						    <li class="pageInfo_btn prev">
						    	<a href="${pageMaker.startPage-1 }">이전</a>
						    </li>
						</c:if>
						<!-- 각 번호 페이지 버튼 -->
						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
						    <li class="pageInfo_btn ${pageMaker.page == num ? "active":"" }">
						    	<a href="${num}">${num}</a>
						    </li>
						</c:forEach>
						<!-- 다음페이지 버튼 -->
						<c:if test="${pageMaker.next }">
						    <li class="pageInfo_btn next">
						    	<a href="${pageMaker.endPage+1 }">다음</a>
						    </li>
						</c:if>
					</ul>
				</div>
				
				<!-- 페이지 이동 시 필요한 정보 전송 -->
				<form id="pageInfo_form" method="get">
					<input type="hidden" name="user_id" value="${user_id}">
					<input type="hidden" name="page" value="${pageMaker.page }">
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
		const pageInfo_form = $('#pageInfo_form');
		
		// 번호 클릭 -> 번호 페이지 이동
		$('.pageInfo_ul a').on('click', function(e){
		    e.preventDefault();
		    
		    pageInfo_form.find('input[name="page"]').val($(this).attr('href'));
		 	// form의 action속성을 생략하면 해당 페이지를 요청할 때와 같은 방식으로 처리된다.
		    // f.attr("action", "/admin/itemMgtList");
		    pageInfo_form.submit();
		});
		
		// 상품 전체 삭제에 쓰일 id값
		const user_id = '<c:out value="${sessionScope.id}"/>';
		
		// confirm()에 쓰일 변수
		let confirm_result = '';
		
		// 상품 삭제 ajax에 쓰일 success 함수
		const onDefaultSuccess = function () {
			pageInfo_form.find('input[name="page"]').val(1);
			pageInfo_form.submit();
		}
		
		// 상품 삭제 ajax에 쓰일 error 함수
		const OnDefaultError = function(error) {
			alert('응답실패 : ' + error);
		}
		
		// 상품 삭제 ajax
		function deleteItemAjax(url, data, onSuccess, onError) {
			$.ajax({
				type : 'post',
				url : url,
				data : data,
				success : onSuccess || onDefaultSuccess,
				error : onError || OnDefaultError
			});
		}
		
		// 선택 상품 삭제
		$('.item_delete_btn').on('click', function(e){
			const checked_item = $('input[name=item_check]:checked');
			
			if (checked_item.length === 0) {
				alert('삭제할 상품을 선택해주세요.');
			} else if (checked_item.length > 0) {
				confirm_result = confirm('선택한 상품을 삭제하시겠습니까?');
				
				if (!confirm_result) return false;
				
				checked_item.each(function(){
					const wishlist_no = this.value;
					
					deleteItemAjax('/wishList/deleteItemInWishList', { 'wishlist_no' : wishlist_no });
				});
			}
		});
		// 선택 상품 삭제
		
		// 전체 상품 삭제 (장바구니 비우기)
		$('.item_all_delete_btn').on('click', function(e){
			confirm_result = confirm('관심상품을 비우시겠습니까?');
			
			if (!confirm_result) return false;
			
			deleteItemAjax('/wishList/deleteAllItemInWishList', { 'user_id' : user_id });
		});
		// 전체 상품 삭제 (장바구니 비우기)
	</script>
</body>
</html>