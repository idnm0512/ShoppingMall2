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
				<div class="thumbnail_div"><img style="width: 500px; height: 600px;" src="/resources/thumbnail/${itemDetailInfo.thumbnail }"></div>
				<div class="item_info_div">
					<div style="font-size: 25px;">${itemDetailInfo.name }</div>
					
					<br><br>
					
					<div style="text-decoration: line-through; font-size: 15px; color: gray;">${itemDetailInfo.price }원</div>
					
					<br><br>
					
					<div style="font-size: 18px;">
						<fmt:formatNumber type="number" pattern="0" var="discounted_price"
							value="${itemDetailInfo.discounted_price }"/>${discounted_price }원
					</div>
					<div style="font-size: 15px;">(<span style="color: red;">${itemDetailInfo.discount }%</span>할인)</div>
					
					<br><br>
					
					<div>색상&사이즈
						<select name="option" onchange="categoryChange(this)">
							<option value="">선택</option>
							<c:forEach items="${itemOptInfo }" var="optInfo">
								<option value="${optInfo.opt_no}">${optInfo.color }/${optInfo.size }</option>
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
							<div>리뷰번호 : ${list.review_no }</div>
							<div>상품번호 : ${list.item_no }</div>
							<div>유저아이디 : ${list.user_id }</div>
							<div>
								<div>평점 : ${list.grade }</div>
								<div>내용 : ${list.content }</div>
								<c:if test="${list.review_img != null }">
									<div><img style="width: 150px; height: 150px;" src="/resources/review/${list.review_img }"></div>
								</c:if>
							</div>
							<div>작성일 :
							    <fmt:parseDate value="${list.update_date }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
							    <fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
							</div>
							<c:if test="${sessionScope.id == list.user_id }">
								<button onclick="modifyReviewPopup('${list.review_no }')">수정</button>
								<button onclick="deleteReview('${list.review_no }', '${list.review_img }')">삭제</button>
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
					id: ${optInfo.opt_no},
					name: '${optInfo.color }/${optInfo.size }'
				},
			</c:forEach>
		];
		
		// 선택된 옵션을 가지고 있을 배열
		const selected_options = [];
		
		// 옵션 선택 시 생성할 옵션table 위치
		const item_result_tbody = $('#item_result_tbody');
		
		// 상품명
		const item_name = '<c:out value="${itemDetailInfo.name }"/>';
		
		// 할인된 상품가격
		const discounted_price = '<c:out value="${discounted_price }"/>';
		
		// 유저 아이디 값
		const user_id = '<c:out value="${sessionScope.id }"/>';
		
		// 상품 번호
		const item_no = '<c:out value="${itemDetailInfo.no }"/>';
		
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
		// 총 구매금액을 표시
		
		// 옵션 관련 table 생성
		function buildTableRow(option_id, option_name) {
			const html = `
				<tr class="opt_row_${'${option_id}'}">
					<td>
						<span>${'${item_name}'}</span>
						<br>
						<span>${'${option_name}'}</span>
					</td>
					<td>
						<input style="width: 40px; height: 30px;" type="number" name="qty" value="1" min="1" onchange="onChangeOptionQty(this, ${'${option_id}'})">
					</td>
					<td>
						<span class="opt_price">${'${discounted_price}'}원</span>
					</td>
					<td onclick="optDelete(${'${option_id}'})">
						삭제
					</td>
				</tr>
			`;
			
			return html;
		}
		// 옵션 관련 table 생성
		
		// 선택된 옵션이 배열에 존재하는지 확인
		function isNotSelected(selected_id) {
			for (let i=0; i<selected_options.length; i++) {
				const item = selected_options[i];
				
				// 옵션의 selected_id(번호)를 비교하여 존재한다면 false
				if (item.id == selected_id) return false;
			}
			// 존재하지 않는다면 true
			return true;
		}
		// 선택된 옵션이 배열에 존재하는지 확인
		
		// 기존에 있던 DB데이터를 참조하여 옵션정보 가져오기
		function getOption(selected_id) {
			for (let i=0; i<OPTIONS.length; i++) {
				const item = OPTIONS[i];
				
				// selected_id(번호) 비교하여 맞는 값이 있으면 return
				// 위의 isNotSelected()로 인해 맞는 값이 '없는 경우는 없음'
				if (item.id == selected_id) return item;
			}
		}
		// 기존에 있던 DB데이터를 참조하여 옵션정보 가져오기
		
		// 선택된 옵션의 selected_id(번호)를 받아 배열에 넣고 table 생성
		function addOption(selected_id) {
			// 기존에 있던 DB데이터를 참조하여 옵션정보 가져오기
			const opt = getOption(selected_id);
			
			selected_options.push({
				id: opt.id,
				name: opt.name,
				price: parseInt(discounted_price),
				qty: 1
			});
			
			// 옵션 관련 table 생성
			const html = buildTableRow(opt.id, opt.name);
			
			item_result_tbody.append(html);
		}
		// 선택된 옵션의 selected_id(번호)를 받아 배열에 넣고 table 생성
		
		// 색상&사이즈를 선택했을 때
		function categoryChange(select_element) {
			const selected_id = select_element.value;
			
			// 선택된 옵션이 배열에 존재하지 않을 때,
			if (isNotSelected(selected_id)) {
				// 해당 옵션값을 배열에 넣고 table 생성
				addOption(selected_id);
				
				// 옵션 선택 후 select 값 초기화
				$('select[name="option"]').val('');
				
				// 총 구매금액에 +
				total_price += parseInt(discounted_price);
				
				// 총 구매금액 표시
				refreshTotalPrice();
			} else {
				alert('이미 선택하신 옵션입니다.');
				$('select[name="option"]').val('');
			}
		}
		// 색상&사이즈를 선택했을 때
		
		// 해당 옵션값 가져오기 (수량 변경할 때)
		function getOptionFromSelectedList(option_id) {
			for (let i=0; i<selected_options.length; i++) {
				const item = selected_options[i];
				
				// option_id(번호)를 받아 비교하여 맞는 옵션값 return
				if (item.id == option_id) return item;
			}
		}
		// 해당 옵션값 가져오기 (수량 변경할 때)
		
		// 수량 변경 + 그에 따른 price 값 변경
		function onChangeOptionQty(input, option_id) {
			const opt = getOptionFromSelectedList(option_id); // 옵션값
			const prev_price = opt.price; // '수량 변경 전'의 가격
			
			// 입력한 수량이 0 일 때
			if (input.value == 0) {
				alert('수량을 최소 1개 이상 선택해주세요.');
				input.value = 1;
			}
			
			const qty = input.value; // 입력한 수량
			const qty_price = qty * discounted_price; // '수량 변경 후'의 가격
			
			// 옵션 배열에 있는 가격과 수량을 계산된 값으로 초기화
			opt.price = qty_price;
			opt.qty = parseInt(qty);
			
			// 수량에 따른 price 값이 표시될 위치
			const $price = $(`#item_result_tbody .opt_row_${'${option_id}'} .opt_price`);
			
			// 수량에 따른 price 값 표시
			$price.text(qty_price+'원');
			
			// '수량 변경 전'의 가격을 먼저 빼고, '수량 변경 후'의 가격 더하기
			total_price -= prev_price;
			total_price += qty_price;
			
			// 총 구매금액 표시
			refreshTotalPrice();
		}
		// 수량 변경 + 그에 따른 price 값 변경
		
		// 해당 옵션의 index값 가져오기 (삭제할 때)
		function getOptionIndexFromSelectedList(option_id) {
			for (let i=0; i<selected_options.length; i++) {
				const item = selected_options[i];
				
				// option_id(번호)를 받아 비교하여 맞는 옵션값의 index를 return
				if (item.id == option_id) return i;
			}
			// 없다면 -1을 return
			return -1;
		}
		// 해당 옵션의 index값 가져오기 (삭제할 때)
		
		// 옵션table 삭제
		function deleteRow(option_id) {
			// 삭제할 위치
			const $row = $(`.opt_row_${'${option_id}'}`);
			// 해당 위치부터 아래(자식)까지 전부 삭제
			$row.remove();
		}
		// 옵션table 삭제
		
		// 옵션 삭제
		function optDelete(option_id) {
			// 해당 옵션의 index값
			const delete_index = getOptionIndexFromSelectedList(option_id);
			// 해당 옵션의 현재 가격
			const price = selected_options[delete_index].price;
			
			// getOptionIndexFromSelectedList()에서 맞는 옵션값이 있었다면,
			if (delete_index !== -1) {
				// 선택된 옵션을 가지고 있는 배열(selected_options)에서 해당 옵션(값) 삭제
				selected_options.splice(delete_index, 1);
				// 옵션table 삭제
				deleteRow(option_id);
				// 총 구매금액에서 해당 옵션의 현재 가격 빼기
				total_price -= price;
				// 총 구매금액 표시
				refreshTotalPrice();
			}
		}
		// 옵션 삭제
		
		// 상품 리뷰 팝업창 띄우기
		function insertReviewPopup() {
			if (user_id == '') {
				alert('로그인이 필요한 서비스입니다.');
				return false;
			}
			
			var pop = window.open("/item/insertReview?no=${itemDetailInfo.no }",
								  "pop",
								  "width=600, height=700, scrollbars=yes, resizable=yes");
		}
		
		// 상품 리뷰 수정 팝업창 띄우기
		function modifyReviewPopup(review_no) {
			var pop = window.open("/item/modifyReview?no=${itemDetailInfo.no }&review_no="+review_no,
								  "pop",
								  "width=600, height=700, scrollbars=yes, resizable=yes");
		}
		
		// 상품 리뷰 삭제하기
		function deleteReview(review_no, review_img) {
			confirm_result = confirm('해당 리뷰를 삭제하시겠습니까?');
			
			if (!confirm_result) return false;
			
			$.ajax({
				type : 'post',
				url : '/item/deleteReview',
				data : { 'review_no' : review_no, 'review_img' : review_img },
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
			if (user_id == '') {
				alert('로그인이 필요한 서비스입니다.');
				return false;
			}
			
			if (selected_options.length === 0) {
				alert('옵션을 최소 1개 이상 선택해주세요.');
				return false;
			}
			
			confirm_result = confirm('장바구니에 담으시겠습니까?');
			
			if (!confirm_result) return false;
			
			$.ajax({
				type : 'post',
				url : '/cart/addItemInCart',
				data : { 'item_no' : item_no, 
						 'user_id' : user_id,
						 'selected_options' : JSON.stringify(selected_options)
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
			
			confirm_result = confirm('관심상품으로 등록하시겠습니까?');
			
			if (!confirm_result) return false;
			
			$.ajax({
				type : 'post',
				url : '/wishList/addItemInWishList',
				data : { 'user_id' : user_id,
						 'item_no' : item_no
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
			if (user_id == '') {
				alert('로그인이 필요한 서비스입니다.');
				return false;
			}
			
			if (selected_options.length === 0) {
				alert('옵션을 최소 1개 이상 선택해주세요.');
				return false;
			}
			
			confirm_result = confirm('총 구매금액 : ' + total_price + '원' +
									 '\n해당 상품을 구매하시겠습니까?');
			
			if (!confirm_result) return false;
			
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