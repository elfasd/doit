<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<html>
<head>
    <title>主界面</title>
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/bs/otherjs/AjaxList.js"></script>
    <script src="${pageContext.request.contextPath}/bs/otherjs/jquery.media.js"></script>
    <script type="text/javascript">
    $(function() {
    	
    	//alert($(window).height()); //浏览器当前窗口可视区域高度   
    	//alert($(document).height()); //浏览器当前窗口文档的高度   
    	
    	var location;
    	//需要获取路径
    	
    	
    	 $('#media').media({
    		 width:$("#media").width(),
    		 height:$(window).height()-200,
    		 autoplay: true, 
    		 src: '${pageContext.request.contextPath}/'+location
    	});
    });
</script>
</head>
<body>		
		<div id="media">
		</div>

</body>
</html>
