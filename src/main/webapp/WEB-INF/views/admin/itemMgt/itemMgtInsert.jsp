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
			margin-left: 15%;
			margin-right: 15%;
		}

		/* h1 글씨 가운데 정렬 */
		h1 {
			text-align: center;
			line-height: 300px;
		}
		
		/* 실질적인 form 부분 outer */
		#form_div_outer {
			text-align: center;
		}
		
		/* 실질적인 form 부분 inner */
		#form_div_inner {
			display: inline-block;
		}
		
		/* form 내부의 table 높이 */
		#form_table {
			height: 600px;
			margin-left:auto; 
    		margin-right:auto;
		}
		
		#form_table2 {
			height: 100px;
			margin-left:auto; 
    		margin-right:auto;
		}
		
		#form_table3 {
			margin-left:auto; 
   			margin-right:auto;
		}
		
		/* input 태그의 높이와 넓이 */
		#form_table input {
			width: 600px;
			height: 30px;
		}
		
		#form_table2 input {
			width: 150px;
			height: 30px;
		}
		
		.grid {
		   text-align: center;
		}
		
		.label_scope {
		   width: 200px;
		   height: 200px;
		}
		
		.img_content {
		    outline: 2px dashed #4B89DC;
		    transition: all 0.2s ease-in-out;
		    width: 200px;
		    height: 200px;
		}
		
		.img_content:hover {
		   filter: brightness(80%);
		   outline-offset: -20px;
		   cursor: pointer;
		}
	</style>
</head>
<body>
	<!-- 헤더 -->
	<header>
		<%@include file = "/resources/header/adminHeader.jsp" %>
	</header>
	
	<main>
		<h1>상품등록</h1>
		
		<div id="form_div_outer">
			<div id="form_div_inner">
				<form id="item_insert_form" action="/itemMgt/insert" method="post" enctype="multipart/form-data">
					
					<br>
					
					<table id="form_table">
						<tr>
							<th>카테고리</th>
							<td>
								<select id="category" name="category" onchange="categoryChange(this)">
									<option value="">선택</option>
									<option value="top">TOP</option>
									<option value="bottom">BOTTOM</option>
									<option value="outer">OUTER</option>
									<option value="shoes">SHOES</option>
									<option value="acc">ACC</option>
								</select>
								<select id="sub_category_select" name="category2">
									<option>선택</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>상품명</th>
							<td>
								<input type="text" id="name" name="name">
							</td>
						</tr>
						<tr>
							<th>가격</th>
							<td>
								<input type="text" id="price" name="price">
							</td>
						</tr>
						<tr>
							<th>할인율(%)</th>
							<td>
								<input type="text" id="discount" name="discount" value="0">
							</td>
						</tr>
						<tr>
							<th>썸네일</th>
							<td>
								<div class="grid">
									<label class="label_scope" for="thumbnail">
										<img class="img_content" src="https://s3.ap-northeast-2.amazonaws.com/jaeho-bucket/img/white.png" id="thumbnail_img"/>
									</label>
									<input type="file" id="thumbnail" name="thumbnail_file" accept="image/*" style="display: none;" onchange="preview(this, 'thumbnail_img')">
								</div>
							</td>
						</tr>
					</table>
					
					<table id="form_table2">
						<tr>
							<th>색상</th>
							<td>
								<input class="opt_input" type="text" id="color" name="color">
							</td>
							<th>사이즈</th>
							<td>
								<input class="opt_input" type="text" id="size" name="size">
							</td>
							<th>수량</th>
							<td>
								<input class="opt_input" type="text" id="qty" name="qty">
							</td>
							<th><button class="opt_insert_btn">옵션등록</button></th>
						</tr>
					</table>
					
					<label>옵션목록</label>
					<table id="form_table3">
						<thead>
							<tr>
								<th><input type="checkbox" id="all_opt_check"></th>
								<th>색상</th>
								<th>사이즈</th>
								<th>수량</th>
							</tr>
						</thead>
						<tbody id="form_table3_tbody">
						</tbody>
					</table>
					
					<br>
					
					<div>
						<button class="opt_delete_btn">옵션삭제</button>
					</div>
					
					<br>
					
					<div>
						<%@include file = "/resources/util/summerNoteEditor.jsp" %>
					</div>
					
					<br><br>
					
					<input type="hidden" name="page" value="${page }">
					<input type="hidden" id="opt_array" name="opt_array">
					<button class="item_insert_btn">상품등록</button>
					<button class="cancel_btn">취소</button>
				</form>
				
				<form id="pageInfo_form" action="/itemMgt/list" method="get">
					<input type="hidden" name="category" value="${category}">
					<input type="hidden" name="category2" value="${category2}">
					<input type="hidden" name="page" value="${page }">
				</form>
			</div>
		</div>
	</main>
	
	<!-- 임시 -->
	<br><br><br><br><br><br><br><br><br><br>
	
	<script>
		// ------------------------------- function
		// 다중 select
		function categoryChange(this_select) {
			const select_choice = ['선택'];
			const select_top = ['선택', '반팔', '긴팔', '맨투맨'];
			const select_bottom = ['선택', '청바지', '슬랙스', '트레이닝'];
			const select_outer = ['선택', '후드', '코트', '패딩'];
			const select_shoes = ['선택', '운동화', '슬리퍼', '구두'];
			const select_acc = ['선택', '모자', '가방', '쥬얼리'];
			const target = document.getElementById('sub_category_select');
			
			if(this_select.value == '') var sub_category = select_choice;
			else if(this_select.value == 'top') sub_category = select_top;
			else if(this_select.value == 'bottom') sub_category = select_bottom;
			else if(this_select.value == 'outer') sub_category = select_outer;
			else if(this_select.value == 'shoes') sub_category = select_shoes;
			else if(this_select.value == 'acc') sub_category = select_acc;
			
			target.options.length = 0;
			
			for (x in sub_category) {
				var opt = document.createElement('option');
				opt.value = sub_category[x];
				opt.innerHTML = sub_category[x];
				target.appendChild(opt);
			}
		}
		// 다중 select
		
		// 이미지 미리보기
		function preview(file, id) {
		    let img = document.querySelector('#' + id);
		
		    let reader = new FileReader();
		    
		    reader.onload = function(e) {
		        img.setAttribute('src', e.target.result.toString());
		    }
		    
		    reader.readAsDataURL(file.files[0]);
		}
		// 이미지 미리보기
		
		// 옵션 view
		function optView() {
			let form_table3_tbody = $('#form_table3_tbody');
			
			const color_val = $('#color').val();
			const size_val = $('#size').val();
			const qty_val = $('#qty').val();
			
			let html = '';
			
			html += '<tr>';
			html += '<td><input type="checkbox" class="new_opt_check" name="new_opt_check" value="'+color_val+'/'+size_val+'/'+qty_val+'" checked="checked"></td>';
			html += '<td>'+color_val+'</td>';
			html += '<td>'+size_val+'</td>';
			html += '<td>'+qty_val+'</td>';
			html += '<td style="font-size: 12px; color: green">추가</td>';
			html += '</tr>';
			
			form_table3_tbody.append(html);
		}
		// 옵션 view
		// ------------------------------- function
		
		const item_insert_form = $('#item_insert_form');
		
		// ------------------------------- jquery
		// 옵션등록 버튼 클릭 시 Start
		$('.opt_insert_btn').on('click', function(e){
			e.preventDefault();
			
			const color = $('#color');
			const size = $('#size');
			const qty = $('#qty');
			
			// 빈칸 예외처리 Start
			if (color.val().trim() == '') {
				alert('색상을 입력해주세요.');
				color.val('');
				color.focus();
				return false;
			}
			
			if (size.val().trim() == '') {
				alert('사이즈를 입력해주세요.');
				size.val('');
				size.focus();
				return false;
			}
			
			if (qty.val().trim() == '') {
				alert('수량을 입력해주세요.');
				qty.val('');
				qty.focus();
				return false;
			}
			// 빈칸 예외처리 End
			
			optView();
		});
		// 옵션등록 버튼 클릭 시 End
		
		// 옵션삭제 버튼 클릭 시 Start
		$('.opt_delete_btn').on('click', function(e){
			e.preventDefault();
			
			if($('input[name=new_opt_check]:checked').length === 0) {
				alert('삭제할 옵션을 선택해주세요.');
				return false;
			}
			
			$('input[name=new_opt_check]:checked').each(function(){
				$('input[name=new_opt_check]:checked').parents('tr').remove();
			});
		});
		// 옵션삭제 버튼 클릭 시 End
		
		// 옵션 전체 선택 Start
		$('#all_opt_check').on('click', function(e){
			if ($('#all_opt_check').is(':checked')) {
				$('input[name=new_opt_check]').prop('checked', true);
			} else {
				$('input[name=new_opt_check]').prop('checked', false);
			}
		});
		// 옵션 전체 선택 End
		
		// 상품등록 버튼 클릭 시 Start
		$('.item_insert_btn').on('click', function(e){
			e.preventDefault();
			
			const category = $('#category');
			const sub_category_select = $('#sub_category_select');
			const name = $('#name');
			const price = $('#price');
			const discount = $('#discount');
			const thumbnail = $('#thumbnail');
			const summernote = $('#summernote');
			const new_opt = $('.new_opt_check');
			
			// 빈칸 예외처리 Start
			if (category.val() == '') {
				alert('카테고리를 선택해주세요.');
				category.val('');
				category.focus();
				return false;
			}
			
			if (sub_category_select.val() == '선택') {
				alert('서브 카테고리를 선택해주세요.');
				sub_category_select.val('선택');
				sub_category_select.focus();
				return false;
			}
			
			if (name.val().trim() == '') {
				alert('상품명을 입력해주세요.');
				name.val('');
				name.focus();
				return false;
			}
			
			if (price.val().trim() == '') {
				alert('가격을 입력해주세요.');
				price.val('');
				price.focus();
				return false;
			}
			
			if (discount.val().trim() == '') {
				alert('할인율을 입력해주세요.');
				discount.val(0);
				discount.focus();
				return false;
			}
			
			if (!thumbnail.val()) {
				alert('썸네일을 첨부해주세요.');
				return false;
			}
			
			if (!new_opt.val()) {
				alert('상품의 옵션을 1개 이상 추가해주세요.');
				return false;
			}
			
			if (summernote.val() == '') {
				alert('상세내용을 입력해주세요.');
				return false;
			}
			// 빈칸 예외처리 End
			
			var array = new Array();
			
			$('input[name=new_opt_check]:checked').each(function(){
				array.push(this.value);
			});
			
			$('#opt_array').val(array);
			
			item_insert_form.submit();
		});
		// 상품등록 버튼 클릭 시 End
		
		// 취소 버튼 클릭 시 Start
		$('.cancel_btn').on('click', function(e){
			e.preventDefault();
			
			let pageInfo_form = $('#pageInfo_form');
			
			pageInfo_form.submit();
		});
		// 취소 버튼 클릭 시 End
		// ------------------------------- jquery
	</script>
</body>
</html>