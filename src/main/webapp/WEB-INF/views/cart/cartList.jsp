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
			width: 1400px;
			text-align: center;
		}
		
		/* 요소 높이와 글씨 크기 */
		td, th {
			border-bottom: solid 1px #efefef;
			height: 40px;
			font-size: 13px;
		}
		
		/* 테이블 머리 배경색 */
		th {
			background: #efefef;
		}
		
		/* th 넓이 Start */
		.checkbox_width {
			width: 3%;
		}
		
		.thumbnail_width {
			width: 7%;
		}
		
		.itemInfo_width {
			width: 40%;
		}
		
		.price_width {
			width: 5%;
		}
		
		.discount_width {
			width: 5%;
		}
		
		.discounted_price_width {
			width: 5%;
		}
		
		.qty_width {
			width: 5%;
		}
		
		.qty_price_width {
			width: 5%;
		}
		
		.div_outer {
			text-align: center;
		}
		
		.div_inner {
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
		<h1>장바구니</h1>
		
		<table>
			<tr>
				<th class="checkbox_width"><input type="checkbox" id="all_item_check"></th>
				<th class="thumbnail_width">상품이미지</th>
				<th class="itemInfo_width">상품정보</th>
				<th class="price_width">원가</th>
				<th class="discount_width">할인율</th>
				<th class="discounted_price_width">판매가격</th>
				<th class="qty_width">수량</th>
				<th class="qty_price_width">합계</th>
			</tr>
			<c:forEach items="${cartListInfo }" var="list">
				<tr class="item_row_${list.cart_no }">
					<td>
						<input type="checkbox" class="item_check" name="item_check" value="${list.cart_no }">
					</td>
					<td>
						<a href="/item/detail?no=${list.item_no }">
							<img src="/resources/thumbnail/${list.thumbnail }" width="120" height="120">
						</a>
					</td>
					<td style="text-align: left;">
						<a href="/item/detail?no=${list.item_no }">
							<span style="font-size: 15px">${list.name }</span>
						</a>
						<br>
						<span style="color: gray">[옵션: ${list.opt }]</span>
					</td>
					<td style="text-decoration: line-through; color: gray;">
						${list.price }원
					</td >
					<td style="color: red">
						${list.discount }%
					</td>
					<td>
						<fmt:formatNumber type="number" pattern="0" var="discounted_price"
							value="${list.discounted_price }"/>${discounted_price }원
					</td>
					<td>
						<input style="width: 40px; height: 30px;" type="number" name="qty" value="${list.qty }" min="1" onchange="onChangeItemQty(this, ${list.cart_no })">
					</td>
					<td>
						<span class="qty_price">${list.qty * discounted_price }원</span>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<br><br>
		
		<div class="div_outer">
			<div class="div_inner">
				<div>
					<button class="item_delete_btn">선택상품 삭제</button>
					<button class="item_all_delete_btn">장바구니 비우기</button>
				</div>
				
				<br><br>
				
				<div>전체상품 금액 : <span data-name="final_price">0원</span></div>
				
				<br><br>
				
				<div>
					<button class="buy_item_btn">선택상품 구매</button>
					<button class="buy_all_item_btn">전체상품 구매</button>
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
		$(function(){
			// 총 구매금액 구하기 (장바구니 페이지 시작 시)
			finalPrice();
		});
		
		// 장바구니에 들어있는 상품
		const CARTITEM = [
			<c:forEach items='${cartListInfo }' var='list'>
				{
					id: ${list.cart_no },
					name: '${list.name }',
					opt: '${list.opt }',
					price: ${list.price - (list.price*list.discount/100) },
					qty: ${list.qty },
					item_no: ${list.item_no}
				},
			</c:forEach>
		];
		
		// 상품 전체 삭제에 쓰일 id값
		const user_id = '<c:out value="${sessionScope.id}"/>';
		
		// 선택된 상품의 옵션을 가지고 있을 배열
		let selected_options = [];
		
		// confirm()에 쓰일 변수
		let confirm_result = '';
		
		// 총 구매금액
		let total_price = 0;
		
		// 총 구매금액이 표시될 위치
		const total_element = $('span[data-name="final_price"]');
		
		// 총 구매금액을 표시
		function refreshTotalPrice() {
			total_element.text(`${'${total_price}'}원`);
		}
		
		// 총 구매금액 구하기 (장바구니 페이지 시작 시)
		function finalPrice() {
			for (let i=0; i<CARTITEM.length; i++) {
				const item = CARTITEM[i];
				
				total_price += item.qty * item.price;
			}
			
			refreshTotalPrice();
		}
		// 총 구매금액 구하기 (장바구니 페이지 시작 시)
		
		// 해당 상품데이터 가져오기 (수량 변경할 때, 선택 상품 구매할 때)
		function getItemFromCartItem(cart_no) {
			for (let i = 0; i < CARTITEM.length; i++) {
				const cartItem = CARTITEM[i];
				
				// cart_no(번호)를 받아 비교하여 맞는 옵션값 return
				if (cartItem.id == cart_no) return cartItem;
			}
		}
		// 해당 상품데이터 가져오기 (수량 변경할 때, 선택 상품 구매할 때)
		
		// 수량 변경 + 그에 따른 price 값 변경
		function onChangeItemQty(input, cart_no) {
			const cartItem = getItemFromCartItem(cart_no); // 상품데이터
			const prev_price = cartItem.qty * cartItem.price; // '수량 변경 전'의 가격
			
			// 입력한 수량이 0 일 때
			if (input.value == 0) {
				alert('수량을 최소 1개 이상 선택해주세요.');
				input.value = 1;
			}
			
			const qty = input.value; // 입력한 수량
			const qty_price = qty * cartItem.price; // '수량 변경 후'의 가격
			
			// CARTITEM 배열에 있는 수량을 계산된 값으로 초기화
			cartItem.qty = parseInt(qty);
			
			// 수량에 따른 price 값이 표시될 위치
			const $price = $(`.item_row_${'${cart_no }'} .qty_price`);
			
			// 수량에 따른 price 값 표시
			$price.text(qty_price+'원');
			
			// CART_TB 수량 변경
			$.ajax({
				type : 'post',
				url : '/cart/modifyItemQty',
				data : { 'qty' : qty, 'cart_no' : cart_no },
				success : function(data) {
					console.log('상품정보 : ' + cartItem.name + ', ' + cartItem.opt +
								'\n변경된 수량 : ' + data);
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
			
			total_price -= prev_price;
			total_price += qty_price;
			
			refreshTotalPrice();
		}
		// 수량 변경 + 그에 따른 price 값 변경
		
		// 선택 상품 삭제 function
		function checkedItemDelete(cart_no) {
			$.ajax({
				type : 'post',
				url : '/cart/deleteItemInCart',
				data : { 'cart_no' : cart_no },
				success : function(data) {
					window.location.reload();
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		}
		
		// 상품 전체 선택
		$('#all_item_check').on('click', function(e){
			if ($('#all_item_check').is(':checked')) {
				$('input[name=item_check]').prop('checked', true);
			} else {
				$('input[name=item_check]').prop('checked', false);
			}
		});
		// 상품 전체 선택
		
		// 선택 상품 구매
		$('.buy_item_btn').on('click', function(e){
			const checked_item = $('input[name=item_check]:checked');
			
			if (checked_item.length === 0) {
				alert('구매할 상품을 선택해주세요.');
			} else if (checked_item.length > 0) {
				confirm_result = confirm('선택한 상품을 구매하시겠습니까?');
				
				if (!confirm_result) return false;
				
				checked_item.each(function(){
					const cart_no = this.value;
					const cartItem = getItemFromCartItem(cart_no);
					const item_no = cartItem.item_no;
					
					selected_options.push({
						name: cartItem.opt,
						qty: cartItem.qty
					});
					
					$.ajax({
						type : 'post',
						url : '/order/buyItem',
						data : { 'item_no' : item_no, 
								 'user_id' : user_id,
								 'selected_options' : JSON.stringify(selected_options)
						},
						dataType : 'json',
						traditional : true,
						success : function(data) {
							console.log(data);
							
							checkedItemDelete(cart_no);
						},
						error : function(error) {
							alert('응답실패 : ' + error);
						}
					});
					
					selected_options = [];
				});
			}
		});
		// 선택 상품 구매
		
		// 전체 상품 구매
		$('.buy_all_item_btn').on('click', function(e){
			confirm_result = confirm('전체 상품을 구매하시겠습니까?');
			
			if (!confirm_result) return false;
			
			for (let i = 0; i < CARTITEM.length; i++) {
				const cartItem = CARTITEM[i];
				const cart_no = cartItem.id;
				const item_no = cartItem.item_no;
				
				selected_options.push({
					name: cartItem.opt,
					qty: cartItem.qty
				});
				
				$.ajax({
					type : 'post',
					url : '/order/buyItem',
					data : { 'item_no' : item_no, 
							 'user_id' : user_id,
							 'selected_options' : JSON.stringify(selected_options)
					},
					dataType : 'json',
					traditional : true,
					success : function(data) {
						console.log(data);
						
						checkedItemDelete(cart_no);
					},
					error : function(error) {
						alert('응답실패 : ' + error);
					}
				});
				
				selected_options = [];
			}
		});
		// 전체 상품 구매
		
		// 선택 상품 삭제
		$('.item_delete_btn').on('click', function(e){
			const checked_item = $('input[name=item_check]:checked');
			
			if (checked_item.length === 0) {
				alert('삭제할 상품을 선택해주세요.');
			} else if (checked_item.length > 0) {
				confirm_result = confirm('선택한 상품을 삭제하시겠습니까?');
				
				if (!confirm_result) return false;
				
				checked_item.each(function(){
					const cart_no = this.value;
					
					checkedItemDelete(cart_no);
				});
			}
		});
		// 선택 상품 삭제
		
		// 전체 상품 삭제 (장바구니 비우기)
		$('.item_all_delete_btn').on('click', function(e){
			confirm_result = confirm('장바구니를 비우시겠습니까?');
			
			if (!confirm_result) return false;
			
			$.ajax({
				type : 'post',
				url : '/cart/deleteAllItemInCart',
				data : { 'user_id' : user_id },
				success :function(data) {
					window.location.reload();
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		});
		// 전체 상품 삭제 (장바구니 비우기)
		
	</script>
</body>
</html>