<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/mytaglib.tld" prefix="z" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试自定义标签</title>
</head>
<body>
	<z:Text/>
	<br>
	<z:ForEach>你好</z:ForEach>
	<br>
	<z:Text/>
</body>
</html>