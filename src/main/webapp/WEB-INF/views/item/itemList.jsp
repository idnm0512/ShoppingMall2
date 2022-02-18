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
		<div class="div_outer">
			<div class="div_inner">
				<c:forEach items="${itemListInfo }" var="list">
					<div class="item_list_div">
						<div><a href="/item/detail?no=${list.no }"><img class="img_content" style="width: 300px; height: 400px" src="${list.thumbnail }"></a></div>
						
						<br>
						
						<div style="font-size: 18px;"><a href="/item/detail?no=${list.no }">${list.name }</a></div>
						
						<br>
						
						<div style="text-decoration: line-through; font-size: 15px; color: gray;">${list.price }원</div>
						
						<br>
						
						<div style="font-size: 18px;">
							<fmt:formatNumber type="number" pattern="0"
								value="${list.discounted_price }"/>원
						</div>
						<div style="font-size: 15px;">(<span style="color: red">${list.discount }%</span> 할인)</div>
					</div>
				</c:forEach>
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
						    	<a href="${pageMaker.startPage - 1 }">이전</a>
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
						    	<a href="${pageMaker.endPage + 1 }">다음</a>
						    </li>
						</c:if>
					</ul>
				</div>
				
				<!-- 페이지 이동 시 필요한 정보 전송 -->
				<form id="pageInfo_form" method="get">
					<input type="hidden" name="category" value="${category}">
					<input type="hidden" name="category2" value="${category2}">
					<input type="hidden" name="page" value="${pageMaker.page}">
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
		    // f.attr("action", "/item/list");
		    pageInfo_form.submit();
		});
	</script>
</body>
</html>