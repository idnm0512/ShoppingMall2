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
				<tr class="item_row_${list.cartNo }">
					<td>
						<input type="checkbox" class="item_check" name="itemCheck" value="${list.cartNo }">
					</td>
					<td>
						<a href="/item/detail?no=${list.itemNo }">
							<img src="${list.thumbnail }" width="120" height="120">
						</a>
					</td>
					<td style="text-align: left;">
						<a href="/item/detail?no=${list.itemNo }">
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
						<fmt:formatNumber type="number" pattern="0" var="discountedPrice"
							value="${list.discountedPrice }"/>${discountedPrice }원
					</td>
					<td>
						<input style="width: 40px; height: 30px;" type="number" name="qty" value="${list.qty }" min="1" onchange="onChangeItemQty(this, ${list.cartNo })">
					</td>
					<td>
						<span class="qty_price">${list.qty * discountedPrice }원</span>
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
					itemNo: ${list.itemNo}
				},
			</c:forEach>
		];
		
		// 상품 전체 삭제에 쓰일 id값
		const userId = '<c:out value="${sessionScope.id}"/>';
		
		// 선택된 상품의 옵션을 가지고 있을 배열
		let selectedOptions = [];
		
		// confirm()에 쓰일 변수
		let confirmResult = '';
		
		// 총 구매금액
		let totalPrice = 0;
		
		// 총 구매금액이 표시될 위치
		const totalElement = $('span[data-name="final_price"]');
		
		// 총 구매금액을 표시
		function refreshTotalPrice() {
			totalElement.text(`${'${totalPrice}'}원`);
		}
		
		// 총 구매금액 구하기 (장바구니 페이지 시작 시)
		function finalPrice() {
			for (let i=0; i<CARTITEM.length; i++) {
				const _item = CARTITEM[i];
				
				totalPrice += _item.qty * _item.price;
			}
			
			refreshTotalPrice();
		}
		// 총 구매금액 구하기 (장바구니 페이지 시작 시)
		
		// 해당 상품데이터 가져오기 (수량 변경할 때, 선택 상품 구매할 때)
		function getItemFromCartItem(cartNo) {
			for (let i = 0; i < CARTITEM.length; i++) {
				const _cartItem = CARTITEM[i];
				
				// cartNo(번호)를 받아 비교하여 맞는 옵션값 return
				if (_cartItem.id == cartNo) return _cartItem;
			}
		}
		// 해당 상품데이터 가져오기 (수량 변경할 때, 선택 상품 구매할 때)
		
		// 수량 변경 + 그에 따른 price 값 변경
		function onChangeItemQty(input, cartNo) {
			const _cartItem = getItemFromCartItem(cartNo); // 상품데이터
			const _prevPrice = _cartItem.qty * _cartItem.price; // '수량 변경 전'의 가격
			
			// 입력한 수량이 0 일 때
			if (input.value == 0) {
				alert('수량을 최소 1개 이상 선택해주세요.');
				input.value = 1;
			}
			
			const _qty = input.value; // 입력한 수량
			const _qtyPrice = qty * _cartItem.price; // '수량 변경 후'의 가격
			
			// CARTITEM 배열에 있는 수량을 계산된 값으로 초기화
			_cartItem.qty = parseInt(_qty);
			
			// 수량에 따른 price 값이 표시될 위치
			const $price = $(`.item_row_${'${cartNo }'} .qty_price`);
			
			// 수량에 따른 price 값 표시
			$price.text(qtyPrice+'원');
			
			// CART_TB 수량 변경
			$.ajax({
				type : 'post',
				url : '/cart/modifyItemQty',
				data : { 'qty' : qty, 'cartNo' : cartNo },
				success : function(data) {
					console.log('상품정보 : ' + _cartItem.name + ', ' + _cartItem.opt +
								'\n변경된 수량 : ' + data);
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
			
			totalPrice -= _prevPrice;
			totalPrice += _qtyPrice;
			
			refreshTotalPrice();
		}
		// 수량 변경 + 그에 따른 price 값 변경
		
		// 선택 상품 삭제 function
		function checkedItemDelete(cartNo) {
			$.ajax({
				type : 'post',
				url : '/cart/deleteItemInCart',
				data : { 'cartNo' : cartNo },
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
			const _checkedItem = $('input[name=item_check]:checked');
			
			if (_checkedItem.length === 0) {
				alert('구매할 상품을 선택해주세요.');
			} else if (_checkedItem.length > 0) {
				confirmResult = confirm('선택한 상품을 구매하시겠습니까?');
				
				if (!confirmResult) return false;
				
				checked_item.each(function(){
					const _cartNo = this.value;
					const _cartItem = getItemFromCartItem(cartNo);
					const _item_no = _cartItem.itemNo;
					
					selectedOptions.push({
						name: cartItem.opt,
						qty: cartItem.qty
					});
					
					$.ajax({
						type : 'post',
						url : '/order/buyItem',
						data : { 'itemNo' : itemNo,
								 'userId' : userId,
								 'selectedOptions' : JSON.stringify(selectedOptions)
						},
						dataType : 'json',
						traditional : true,
						success : function(data) {
							console.log(data);
							
							checkedItemDelete(cartNo);
						},
						error : function(error) {
							alert('응답실패 : ' + error);
						}
					});
					
					selectedOptions = [];
				});
			}
		});
		// 선택 상품 구매
		
		// 전체 상품 구매
		$('.buy_all_item_btn').on('click', function(e){
			confirmResult = confirm('전체 상품을 구매하시겠습니까?');
			
			if (!confirmResult) return false;
			
			for (let i = 0; i < CARTITEM.length; i++) {
				const _cartItem = CARTITEM[i];
				const cartNo = _cartItem.id;
				const itemNo = _cartItem.itemNo;
				
				selectedOptions.push({
					name: cartItem.opt,
					qty: cartItem.qty
				});
				
				$.ajax({
					type : 'post',
					url : '/order/buyItem',
					data : { 'itemNo' : itemNo,
							 'userId' : userId,
							 'selectedOptions' : JSON.stringify(selectedOptions)
					},
					dataType : 'json',
					traditional : true,
					success : function(data) {
						console.log(data);
						
						checkedItemDelete(cartNo);
					},
					error : function(error) {
						alert('응답실패 : ' + error);
					}
				});
				
				selectedOptions = [];
			}
		});
		// 전체 상품 구매
		
		// 선택 상품 삭제
		$('.item_delete_btn').on('click', function(e){
			const _checkedItem = $('input[name=item_check]:checked');
			
			if (_checkedItem.length === 0) {
				alert('삭제할 상품을 선택해주세요.');
			} else if (_checkedItem.length > 0) {
				confirmResult = confirm('선택한 상품을 삭제하시겠습니까?');
				
				if (!confirmResult) return false;
				
				_checkedItem.each(function(){
					const _cartNo = this.value;
					
					checkedItemDelete(_cartNo);
				});
			}
		});
		// 선택 상품 삭제
		
		// 전체 상품 삭제 (장바구니 비우기)
		$('.item_all_delete_btn').on('click', function(e){
			confirmResult = confirm('장바구니를 비우시겠습니까?');
			
			if (!confirmResult) return false;
			
			$.ajax({
				type : 'post',
				url : '/cart/deleteAllItemInCart',
				data : { 'userId' : userId },
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