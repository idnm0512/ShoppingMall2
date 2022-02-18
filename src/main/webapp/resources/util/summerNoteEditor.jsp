<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>summerNoteEditor</title>
	
    <script
		src="https://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous"></script>
	
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    
	<script>
		$(function() {
			$('#summernote').summernote({
				height : 600,
				minHeight : 550,
				maxHeight : 650,
				lang : "ko-KR",
				placeholder : '상품의 상세내용을 입력해주세요.',
				callbacks : {
					onImageUpload : function(files, editor, welEditable) {
						// 파일 업로드 (다중업로드를 위해 반복문 사용)
						for (var i = files.length - 1; i >= 0; i--) {
							sendImg(files[i], this);
						}
					}
				}
			});
		});
		
		function sendImg(file, summernote_this) {
			data = new FormData();
			
			data.append("file", file);
			
			$.ajax({
				data : data,
				type : "post",
				url : "/itemMgt/summerNoteEditorImgUpload",
				contentType : false,
				processData : false,
				success : function(data) {
					alert("업로드 중입니다. 잠시만 기다려주세요.");
					setTimeout(function() {
						$(summernote_this).summernote('editor.insertImage', data.url);
					}, 3000);
				},
				error : function(error) {
					alert("응답실패 : " + error);
				}
			});
		}
	</script>
	
</head>
<body>
	
	<textarea id="summernote" name="content">${itemDetailInfo.content }</textarea>
	
</body>
</html>