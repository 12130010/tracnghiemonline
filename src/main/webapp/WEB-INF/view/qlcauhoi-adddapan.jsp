<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<fieldset>
		<legend>Đán án thứ ${numDA}</legend>
		<label for="noiDungDA">Nội dung đáp án:</label>
		<textarea rows="10" cols="50" name="noiDungDA" id="noiDungDA"></textarea>
		<br/>
		<label for="hinh">Hình cho đán án:</label>
		<input type="file" multiple name="hinh" id="hinh"></textarea>
		<br/>
		<label>Là đáp án đúng:</label> <input type="radio" name="">
	</fieldset>
</body>
</html>