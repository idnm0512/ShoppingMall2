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
		/* 전체적인 마진 */
		main {
			margin-left: 5%;
			margin-right: 5%;
		}
		
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
			width: 1300px;
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
		
		.user_id_width {
			width: 5%;
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
		<%@include file = "/resources/header/adminHeader.jsp" %>
	</header>
	
	<main>
		<h1>주문관리</h1>
		
		<table>
			<tr>
				<th class="checkbox_width"><input type="checkbox" id="all_item_check"></th>
				<th class="user_id_width">유저아이디</th>
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
						${list.user_id }
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
		
		<c:choose>
			<c:when test="${order_state == '배송준비중' }">
				<div class="div_outer">
					<div class="div_inner">
						<div>
							<button class="success_order_btn">선택상품 배송완료</button>
							<button class="success_all_order_btn">전체상품 배송완료</button>
						</div>
					</div>
				</div>
			</c:when>
			<c:when test="${order_state == '주문취소' }">
				<div class="div_outer">
					<div class="div_inner">
						<div>
							<button class="cancel_success_order_btn">선택상품 취소완료</button>
							<button class="cancel_success_all_order_btn">전체상품 취소완료</button>
						</div>
					</div>
				</div>
			</c:when>
		</c:choose>
		
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
				    	<a href="${num }">${num }</a>
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
			<input type="hidden" name="order_state" value="${order_state }">
			<input type="hidden" name="page" value="${pageMaker.page }">
		</form>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<script>
		const pageInfo_form = $('#pageInfo_form');
		
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
		
		// 선택 상품 배송완료
		$('.success_order_btn').on('click', function(e){
			const checked_item = $('input[name=item_check]:checked');
			
			if (checked_item.length === 0) {
				alert('배송완료 처리할 상품을 선택해주세요.');
			} else if (checked_item.length > 0) {
				confirm_result = confirm('선택한 상품을 배송완료 처리 하시겠습니까?');
				
				if (!confirm_result) return false;
				
				checked_item.each(function(){
					const order_no = this.value;
					
					$.ajax({
						type : 'post',
						url : '/orderMgt/successOrder',
						data : { 'order_no' : order_no },
						success : function() {
							window.location.href = '/orderMgt/list?order_state=배송준비중&page=1';
						},
						error : function(error) {
							alert('응답실패 : ' + error);
						}
					});
				});
			}
		});
		// 선택 상품 배송완료
		
		// 전체 상품 배송완료
		$('.success_all_order_btn').on('click', function(e){
			confirm_result = confirm('전체 상품을 배송완료 처리 하시겠습니까?');
			
			if (!confirm_result) return false;
			
			$.ajax({
				type : 'post',
				url : '/orderMgt/successAllOrder',
				success :function() {
					window.location.href = '/orderMgt/list?order_state=배송준비중&page=1';
				},
				error : function(error) {
					alert('응답실패 : ' + error);
					console.log(error);
				}
			});
		});
		// 전체 상품 배송완료
		
		// 선택 상품 취소완료
		$('.cancel_success_order_btn').on('click', function(e){
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
						url : '/orderMgt/cancelSuccessOrder',
						data : { 'order_no' : order_no },
						success : function() {
							window.location.href = '/orderMgt/list?order_state=주문취소&page=1';
						},
						error : function(error) {
							alert('응답실패 : ' + error);
						}
					});
				});
			}
		});
		// 선택 상품 취소완료
		
		// 전체 상품 취소완료
		$('.cancel_success_all_order_btn').on('click', function(e){
			confirm_result = confirm('전체 상품을 취소하시겠습니까?');
			
			if (!confirm_result) return false;
			
			$.ajax({
				type : 'post',
				url : '/orderMgt/cancelSuccessAllOrder',
				success :function() {
					window.location.href = '/orderMgt/list?order_state=주문취소&page=1';
				},
				error : function(error) {
					alert('응답실패 : ' + error);
					console.log(error);
				}
			});
		});
		// 전체 상품 취소완료
		
		// 번호 클릭 -> 번호 페이지 이동
		$('.pageInfo_ul a').on('click', function(e){
		    e.preventDefault();
		    
		    pageInfo_form.find('input[name="page"]').val($(this).attr('href'));
		 	// form의 action속성을 생략하면 해당 페이지를 요청할 때와 같은 방식으로 처리된다.
		    // f.attr("action", "/orderMgt/list");
		    pageInfo_form.submit();
		});
	</script>
</body>
</html>