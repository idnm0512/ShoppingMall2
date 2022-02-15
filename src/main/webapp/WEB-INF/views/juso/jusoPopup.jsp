<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소 팝업창</title>
<% 
	String inputYn = request.getParameter("inputYn"); 
	String roadAddrPart1 = request.getParameter("roadAddrPart1"); 
	String addrDetail = request.getParameter("addrDetail"); 
%>
</head>
	<script language="javascript">
		/*
			모의 해킹 테스트 시 팝업API를 호출하시면 IP가 차단 될 수 있습니다. 
			주소팝업API를 제외하시고 테스트 하시기 바랍니다.
		*/
		function init() {
			var url = location.href;
			var confmKey = "U01TX0FVVEgyMDIxMTIwODAyNDU1NjExMjAwMjE=";
			var resultType = "4"; // 도로명주소 검색결과 화면 출력내용,
								  // 1 : 도로명,
								  // 2 : 도로명+지번+상세보기(관련지번, 관할주민센터),
								  // 3 : 도로명+상세보기(상세건물명),
								  // 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
			var inputYn= "<%=inputYn%>";
			
			if (inputYn != "Y") {
				document.form.confmKey.value = confmKey;
				document.form.returnUrl.value = url;
				document.form.resultType.value = resultType;
				document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; // 인터넷망
				document.form.submit();
			} else {
				opener.jusoCallBack("<%=roadAddrPart1%>", "<%=addrDetail%>");
				window.close();
			}
			
		}
	</script>
<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START -->
		<!-- 
			<input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/>
		 -->
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END -->
	</form>
</body>
</html>