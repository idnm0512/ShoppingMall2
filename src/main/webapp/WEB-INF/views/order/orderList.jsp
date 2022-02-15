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
		
		.state_div_outer {
			text-align: center;
		}
		
		.state_div_inner {
			display: inline-block;
		}
		
		/* th 넓이 Start */
		.checkbox_width {
			width: 3%;
		}
		
		.thumbnail_width {
			width: 7%;
		}
		
		.itemInfo_width {
			width: 30%;
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
		
		.order_state_width {
			width: 5%;
		}
		
		.order_date_width {
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
		<h1>주문/배송</h1>
		
		<div class="state_div_outer">
		    <div class="state_div_inner">
				<a href="/order/list?user_id=${sessionScope.id }&order_state=배송준비중">배송준비중</a> /
				<a href="/order/list?user_id=${sessionScope.id }&order_state=배송완료">배송완료</a> /
				<a href="/order/list?user_id=${sessionScope.id }&order_state=주문취소">주문취소</a> /
				<a href="/order/list?user_id=${sessionScope.id }&order_state=취소완료">취소완료</a>
			</div>
		</div>
		
		<br>
		
		<table>
			<tr>
				<th class="checkbox_width"><input type="checkbox" id="all_item_check"></th>
				<th class="thumbnail_width">상품이미지</th>
				<th class="itemInfo_width">상품정보</th>
				<th class="discounted_price_width">판매가격</th>
				<th class="qty_width">수량</th>
				<th class="qty_price_width">구매금액</th>
				<th class="order_state_width">상태</th>
				<c:choose>
					<c:when test="${order_state == '취소완료' }">
						<th class="order_date_width">취소날짜</th>
					</c:when>
					<c:otherwise>
						<th class="order_date_width">주문날짜</th>
					</c:otherwise>
				</c:choose>
			</tr>
			<c:forEach items="${orderListInfo }" var="list">
				<tr class="item_row_${list.order_no }">
					<td>
						<input type="checkbox" class="item_check" name="item_check" value="${list.order_no }">
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
					<td>
						<fmt:formatNumber type="number" pattern="0" var="discounted_price"
							value="${list.discounted_price }"/>${discounted_price }원
					</td>
					<td>
						${list.qty }
					</td>
					<td>
						<span class="qty_price">${list.qty * discounted_price }원</span>
					</td>
					<c:choose>
						<c:when test="${order_state == '배송준비중' }">
							<td style="color: blue;">
								${list.order_state }
							</td>
						</c:when>
						<c:when test="${order_state == '배송완료' }">
							<td style="color: green;">
								${list.order_state }
							</td>
						</c:when>
						<c:when test="${order_state == '주문취소' }">
							<td style="color: red;">
								${list.order_state }
							</td>
						</c:when>
						<c:otherwise>
							<td style="color: gray;">
								${list.order_state }
							</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${order_state == '취소완료' }">
							<td>
							    <fmt:parseDate value="${list.order_cancel_date }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
								<fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
							</td>
						</c:when>
						<c:otherwise>
							<td>
							    <fmt:parseDate value="${list.order_date }" pattern="yyyy-MM-dd" var="parseDateTime" type="both" />
								<fmt:formatDate pattern="yyyy-MM-dd" value="${parseDateTime }"/>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		
		<br><br>

		<c:if test="${order_state == '배송준비중' }">
			<div class="div_outer">
				<div class="div_inner">
					<div>
						<button class="cancel_order_btn">선택상품 주문취소</button>
						<button class="cancel_all_order_btn">전체상품 주문취소</button>
					</div>
				</div>
			</div>
		</c:if>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<!-- 푸터 -->
	<footer>
		<%@include file = "/resources/footer/footer.jsp" %>
	</footer>
	
	<script>
		// 상품 전체 삭제에 쓰일 id값
		const user_id = '<c:out value="${sessionScope.id}"/>';
		
		// confirm()에 쓰일 변수
		let confirm_result = '';
		
		// 상품 전체 선택
		$('#all_item_check').on('click', function(e){
			if ($('#all_item_check').is(':checked')) {
				$('input[name=item_check]').prop('checked', true);
			} else {
				$('input[name=item_check]').prop('checked', false);
			}
		});
		// 상품 전체 선택
		
		// 선택 상품 취소
		$('.cancel_order_btn').on('click', function(e){
			const checked_item = $('input[name=item_check]:checked');
			
			if (checked_item.length === 0) {
				alert('취소할 상품을 선택해주세요.');
			} else if (checked_item.length > 0) {
				confirm_result = confirm('선택한 상품을 취소하시겠습니까?');
				
				if (!confirm_result) return false;
				
				checked_item.each(function(){
					const order_no = this.value;
					
					$.ajax({
						type : 'post',
						url : '/order/cancelOrder',
						data : { 'order_no' : order_no },
						success : function(data) {
							window.location.reload();
						},
						error : function(error) {
							alert('응답실패 : ' + error);
						}
					});
				});
			}
		});
		// 선택 상품 취소
		
		// 전체 상품 취소
		$('.cancel_all_order_btn').on('click', function(e){
			confirm_result = confirm('전체 상품을 취소하시겠습니까?');
			
			if (!confirm_result) return false;
			
			$.ajax({
				type : 'post',
				url : '/order/cancelAllOrder',
				data : { 'user_id' : user_id },
				success :function(data) {
					window.location.reload();
				},
				error : function(error) {
					alert('응답실패 : ' + error);
				}
			});
		});
		// 전체 상품 취소
		
	</script>
</body>
</html>