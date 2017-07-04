<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>吉他</title>
</head>
<body>
<form action="guitar_search">
<label>builder:</label>
	<input type="text" name="builder">
	<label>model:</label>
	<input type="text" name="model">
	<label>numberString:</label>
	<input type="text" name="numberStrings">
	<label>type:</label>
	<input type="text" name="type">
	<label>backWood:</label>
	<input type="text" name="backWood">
	<label>topWood:</label>
	<input type="text" name="topWood">
	<input type="submit" value="查询">
	
</form>
<!--  <form action="guitar_add">
<input type="submit" value="添加">
</form>   -->
</body>
</html>