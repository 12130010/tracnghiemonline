<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Update de thi</title>
<!--  jquery and jquery ui -->
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>

<!-- Datatable and editor -->
<link rel="stylesheet" type="text/css" href="css/dataTables.css">


<script type="text/javascript" charset="utf8" src="js/dataTables.js"></script>

<!--  function for tab panel run -->
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Quản lí Khoa</a></li>
			<li><a href="#tabs-2">Quản lí Ngành</a></li>
			<li><a href="#tabs-3">Quản lí Môn học</a></li>
			<li><a href="#tabs-4">Quản lí Câu hỏi</a></li>
		</ul>
		<div id="tabs-1">
			<%@ include file="qlkhoa.jsp"%>
		</div>
		<div id="tabs-2">
			<%@ include file="qlnganh.jsp"%>
		</div>
		<div id="tabs-3">
			<%@ include file="qlmonhoc.jsp"%>
		</div>
		<div id="tabs-4">
			<%@ include file="qlcauhoi.jsp"%>
		</div>
	</div>

</body>
</html>
