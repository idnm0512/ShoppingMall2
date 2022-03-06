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
	
	<style>
		main {
			margin-left: 5%;
			margin-right: 5%;
		}
		
		.div_outer {
			text-align: center;
		}
		
		.div_inner {
			display: inline-block;
			margin-top: 60px;
		}
		
		.thumbnail_div {
			float: left;
			margin-right: 150px;
		}
		
		.item_info_div {
			float: left;
		}
		
		input[type=number]::-webkit-inner-spin-button,
		input[type=number]::-webkit-outer-spin-button {
			-webkit-appearance: "Always Show Up/Down Arrows";
		}
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/userHeader.jsp" %>
	</header>
	
	<main>
		<div class="div_outer">
			<div class="div_inner">
				<div class="thumbnail_div"><img style="width: 500px; height: 600px;" src="${itemDetailInfo.thumbnail }"></div>
				<div class="item_info_div">
					<div style="font-size: 25px;">${itemDetailInfo.name }</div>
					
					<br><br>
					
					<div style="text-decoration: line-through; font-size: 15px; color: gray;">${itemDetailInfo.price }원</div>
					
					<br><br>
					
					<div style="font-size: 18px;">
						<fmt:formatNumber type="number" pattern="0" var="discountedPrice"
							value="${itemDetailInfo.discountedPrice }"/>${discountedPrice }원
					</div>
					<div style="font-size: 15px;">(<span style="color: red;">${itemDetailInfo.discount }%</span>할인)</div>
					
					<br><br>
					
					<div>색상&사이즈
						<select name="option" onchange="categoryChange(this)">
							<option value="">선택</option>
							<c:forEach items="${itemOptInfo }" var="optInfo">
								<option value="${optInfo.optNo}">${optInfo.color }/${optInfo.size }</option>
							</c:forEach>
						</select>
					</div>
					
					<br><br>
					
					<div id="item_result_div">(--HERE--)</div>
					
					<br>
					
					<table>
						<tbody id="item_result_tbody">
						</tbody>
					</table>
					
					<br>
					
					<div>총 구매금액 : <span data-name="final_price">0원</span></div>
					
					<br>
					
				</div>
				<div>
					<button class="add_item_in_cart_btn">장바구니 담기</button>
					<button class="add_item_in_wishlist_btn">관심상품 등록</button>
				</div>
				
				<br>
				
				<div>
					<button class="buy_item_btn">구매하기</button>
				</div>
				<div>
					<!-- 리뷰(후기)/문의 위치로 이동하는 버튼 -->
				</div>
				<div class="item_content_div">
					${itemDetailInfo.content }
				</div>
				
				<br><br>
				
				<div>
					<!-- 맨 아래에 리뷰(후기)/문의 리스트 -->
					<button onclick="insertReviewPopup()">상품 리뷰 작성하기</button>
					
					<br><br><br><br>
					
					<c:forEach items="${itemReviewInfo }" var="list">
						<div style="border-top: solid 1px #efefef;">
							<div>리뷰번호 : ${list.reviewNo }</div>
							<div>상품번호 : ${list.itemNo }</div>
							<div>유저아이디 : ${list.userId }</div>
							<div>
								<div>평점 : ${list.grade }</div>
								<div>내용 : ${list.content }</div>
								<c:if test="${list.reviewImg != null }">
									<div><img style="width: 150px; height: 150px;" src="${list.reviewImg }"></div>
								</c:if>
							</div>
							<div>작성일 :
							    <fmt:parseDate value="${list.updateDate }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
							    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
							</div>
							<c:if test="${sessionScope.id == list.userId }">
								<button onclick="modifyReviewPopup('${list.reviewNo }')">수정</button>
								<button onclick="deleteReview('${list.reviewNo }', '${list.reviewImg }')">삭제</button>
							</c:if>
						</div>
						
						<br>
						
					</c:forEach>
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
		// 참조할 수 있는 DB데이터
		const OPTIONS = [
			<c:forEach items='${itemOptInfo }' var='optInfo'>
				{
					id: ${optInfo.optNo},
					name: '${optInfo.color }/${optInfo.size }'
				},
			</c:forEach>
		];
		
		// 선택된 옵션을 가지고 있을 배열
		const selectedOptions = [];
		
		// 옵션 선택 시 생성할 옵션table 위치
		const itemResultTbody = $('#item_result_tbody');
		
		// 상품명
		const itemName = '<c:out value="${itemDetailInfo.name }"/>';
		
		// 할인된 상품가격
		const discountedPrice = '<c:out value="${discountedPrice }"/>';
		
		// 유저 아이디 값
		const userId = '<c:out value="${sessionScope.id }"/>';
		
		// 상품 번호
		const itemNo = '<c:out value="${itemDetailInfo.no }"/>';
		
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
		// 총 구매금액을 표시
		
		// 옵션 관련 table 생성
		function buildTableRow(optionId, optionName) {
			const html = `
				<tr class="opt_row_${'${optionId}'}">
					<td>
						<span>${'${itemName}'}</span>
						<br>
						<span>${'${optionName}'}</span>
					</td>
					<td>
						<input style="width: 40px; height: 30px;" type="number" name="qty" value="1" min="1" onchange="onChangeOptionQty(this, ${'${optionId}'})">
					</td>
					<td>
						<span class="opt_price">${'${discountedPrice}'}원</span>
					</td>
					<td onclick="optDelete(${'${optionId}'})">
						삭제
					</td>
				</tr>
			`;
			
			return html;
		}
		// 옵션 관련 table 생성
		
		// 선택된 옵션이 배열에 존재하는지 확인
		function isNotSelected(selectedId) {
			for (let i=0; i<selectedOptions.length; i++) {
				const _item = selectedOptions[i];
				
				// 옵션의 selectedId(번호)를 비교하여 존재한다면 false
				if (_item.id == selectedId) return false;
			}
			// 존재하지 않는다면 true
			return true;
		}
		// 선택된 옵션이 배열에 존재하는지 확인
		
		// 기존에 있던 DB데이터를 참조하여 옵션정보 가져오기
		function getOption(selectedId) {
			for (let i=0; i<OPTIONS.length; i++) {
				const _item = OPTIONS[i];
				
				// selectedId(번호) 비교하여 맞는 값이 있으면 return
				// 위의 isNotSelected()로 인해 맞는 값이 '없는 경우는 없음'
				if (_item.id == selectedId) return _item;
			}
		}
		// 기존에 있던 DB데이터를 참조하여 옵션정보 가져오기
		
		// 선택된 옵션의 selectedId(번호)를 받아 배열에 넣고 table 생성
		function addOption(selectedId) {
			// 기존에 있던 DB데이터를 참조하여 옵션정보 가져오기
			const _opt = getOption(selectedId);
			
			selectedOptions.push({
				id: _opt.id,
				name: _opt.name,
				price: parseInt(discountedPrice),
				qty: 1
			});
			
			// 옵션 관련 table 생성
			const _html = buildTableRow(_opt.id, _opt.name);
			
			itemResultTbody.append(_html);
		}
		// 선택된 옵션의 selectedId(번호)를 받아 배열에 넣고 table 생성
		
		// 색상&사이즈를 선택했을 때
		function categoryChange(selectElement) {
			const _selectedId = selectElement.value;
			
			// 선택된 옵션이 배열에 존재하지 않을 때,
			if (isNotSelected(_selectedId)) {
				// 해당 옵션값을 배열에 넣고 table 생성
				addOption(_selectedId);
				
				// 옵션 선택 후 select 값 초기화
				$('select[name="option"]').val('');
				
				// 총 구매금액에 +
				totalPrice += parseInt(discountedPrice);
				
				// 총 구매금액 표시
				refreshTotalPrice();
			} else {
				alert('이미 선택하신 옵션입니다.');
				$('select[name="option"]').val('');
			}
		}
		// 색상&사이즈를 선택했을 때
		
		// 해당 옵션값 가져오기 (수량 변경할 때)
		function getOptionFromSelectedList(optionId) {
			for (let i=0; i<selectedOptions.length; i++) {
				const _item = selectedOptions[i];
				
				// optionId(번호)를 받아 비교하여 맞는 옵션값 return
				if (_item.id == optionId) return _item;
			}
		}
		// 해당 옵션값 가져오기 (수량 변경할 때)
		
		// 수량 변경 + 그에 따른 price 값 변경
		function onChangeOptionQty(input, optionId) {
			const _opt = getOptionFromSelectedList(optionId); // 옵션값
			const _prevPrice = opt.price; // '수량 변경 전'의 가격
			
			// 입력한 수량이 0 일 때
			if (input.value == 0) {
				alert('수량을 최소 1개 이상 선택해주세요.');
				input.value = 1;
			}
			
			const _qty = input.value; // 입력한 수량
			const _qtyPrice = _qty * discountedPrice; // '수량 변경 후'의 가격
			
			// 옵션 배열에 있는 가격과 수량을 계산된 값으로 초기화
			_opt.price = _qtyPrice;
			_opt.qty = parseInt(_qty);
			
			// 수량에 따른 price 값이 표시될 위치
			const $price = $(`#item_result_tbody .opt_row_${'${optionId}'} .opt_price`);
			
			// 수량에 따른 price 값 표시
			$price.text(_qtyPrice + '원');
			
			// '수량 변경 전'의 가격을 먼저 빼고, '수량 변경 후'의 가격 더하기
			totalPrice -= _prevPrice;
			totalPrice += _qtyPrice;
			
			// 총 구매금액 표시
			refreshTotalPrice();
		}
		// 수량 변경 + 그에 따른 price 값 변경
		
		// 해당 옵션의 index값 가져오기 (삭제할 때)
		function getOptionIndexFromSelectedList(optionId) {
			for (let i=0; i<selectedOptions.length; i++) {
				const _item = selectedOptions[i];
				
				// option_id(번호)를 받아 비교하여 맞는 옵션값의 index를 return
				if (_item.id == optionId) return i;
			}
			// 없다면 -1을 return
			return -1;
		}
		// 해당 옵션의 index값 가져오기 (삭제할 때)
		
		// 옵션table 삭제
		function deleteRow(optionId) {
			// 삭제할 위치
			const $row = $(`.opt_row_${'${optionId}'}`);
			// 해당 위치부터 아래(자식)까지 전부 삭제
			$row.remove();
		}
		// 옵션table 삭제
		
		// 옵션 삭제
		function optDelete(optionId) {
			// 해당 옵션의 index값
			const _deleteIndex = getOptionIndexFromSelectedList(optionId);
			// 해당 옵션의 현재 가격
			const _price = selectedOptions[_deleteIndex].price;
			
			// getOptionIndexFromSelectedList()에서 맞는 옵션값이 있었다면,
			if (_deleteIndex !== -1) {
				// 선택된 옵션을 가지고 있는 배열(selectedOptions)에서 해당 옵션(값) 삭제
				selectedOptions.splice(_deleteIndex, 1);
				// 옵션table 삭제
				deleteRow(optionId);
				// 총 구매금액에서 해당 옵션의 현재 가격 빼기
				totalPrice -= _price;
				// 총 구매금액 표시
				refreshTotalPrice();
			}
		}
		// 옵션 삭제
		
		// 상품 리뷰 팝업창 띄우기
		function insertReviewPopup() {
			if (userId == '') {
				alert('로그인이 필요한 서비스입니다.');
				return false;
			}
			
			var _pop = window.open("/item/insertReview?no=${itemDetailInfo.no }",
								   "pop",
								   "width=600, height=700, scrollbars=yes, resizable=yes");
		}
		
		// 상품 리뷰 수정 팝업창 띄우기
		function modifyReviewPopup(reviewNo) {
			var _pop = window.open("/item/modifyReview?no=${itemDetailInfo.no }&reviewNo="+reviewNo,
								   "pop",
								   "width=600, height=700, scrollbars=yes, resizable=yes");
		}
		
		// 상품 리뷰 삭제하기
		function deleteReview(review_no, review_img) {
			confirmResult = confirm('해당 리뷰를 삭제하시겠습니까?');
			
			if (!confirmResult) return false;
			
			$.ajax({
				type : 'post',
				url : '/item/deleteReview',
				data : { 'reviewNo' : reviewNo, 'reviewImg' : reviewImg },
				success : function() {
					alert('리뷰 삭제가 완료되었습니다.');
					
					window.location.reload();
				},
				error : function(error) {
					alert('응답실패');
					console.log(error);
				}
			});
		}
		
		// 장바구니 담기
		$('.add_item_in_cart_btn').on('click', function(e){
			if (userId == '') {
				alert('로그인이 필요한 서비스입니다.');
				return false;
			}
			
			if (selectedOptions.length === 0) {
				alert('옵션을 최소 1개 이상 선택해주세요.');
				return false;
			}
			
			confirmResult = confirm('장바구니에 담으시겠습니까?');
			
			if (!confirmResult) return false;
			
			$.ajax({
				type : 'post',
				url : '/cart/addItemInCart',
				data : { 'itemNo' : itemNo,
						 'userId' : userId,
						 'selectedOptions' : JSON.stringify(selectedOptions)
				},
				dataType : 'json',
				traditional : true,
				success : function(data) {
					console.log(data);
					
					alert('장바구니에 담았습니다.');
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		});
		// 장바구니 담기
		
		// 관심상품 등록
		$('.add_item_in_wishlist_btn').on('click', function(e){
			if (user_id == '') {
				alert('로그인이 필요한 서비스입니다.');
				return false;
			}
			
			confirmResult = confirm('관심상품으로 등록하시겠습니까?');
			
			if (!confirmResult) return false;
			
			$.ajax({
				type : 'post',
				url : '/wishList/addItemInWishList',
				data : { 'userId' : userId,
						 'itemNo' : itemNo
				},
				success : function(data) {
					console.log(data);
					
					alert('관심상품으로 등록되었습니다.');
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		});
		// 관심상품 등록
		
		// 구매하기
		$('.buy_item_btn').on('click', function(e){
			if (userId == '') {
				alert('로그인이 필요한 서비스입니다.');
				return false;
			}
			
			if (selectedOptions.length === 0) {
				alert('옵션을 최소 1개 이상 선택해주세요.');
				return false;
			}
			
			confirmResult = confirm('총 구매금액 : ' + totalPrice + '원' +
									 '\n해당 상품을 구매하시겠습니까?');
			
			if (!confirmResult) return false;
			
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
					
					alert('상품을 구매하였습니다.');
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		});
	</script>
</body>
</html>