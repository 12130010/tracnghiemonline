<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Add Môn Học</title>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#selectkhoaforMH').change(
						function() {
							var index = $("#selectkhoaforMH option:selected")
									.attr('value');
							selectNganhForMonHoc(index);
						});

				$('#selectnganhforMH').change(
						function() {
							var indexkhoa = $(
									"#selectkhoaforMH option:selected").attr(
									'value');
							var indexnganh = $(
									"#selectnganhforMH option:selected").attr(
									'value');
							selectMonHoc(indexkhoa, indexnganh);
						});
			});
	function selectNganhForMonHoc(index) {
		if (index === '-1') {
			$('#selectnganhforMH').html(
					"<option value='-1'>Chọn ngành</option>");
		} else {
			$('#selectnganhforMH').load(
					"qlmonhocselectnganh?indexkhoa=" + index);
		}
		$('#content-table-monhoc').html("");
	}
	function selectMonHoc(indexkhoa, indexnganh) {
		if (indexkhoa === '-1' || indexnganh == '-1') {
			$('#content-table-monhoc').html("");
		} else {
			$('#content-table-monhoc').load(
					"qlmonhoctablemonhoc?indexkhoa=" + indexkhoa
							+ "&indexnganh=" + indexnganh);
			datatable.draw();
		}
	}
</script>
</head>
<body>
	<h1>Xin chào Quản lí Môn học</h1>
	<label>Khoa: </label>
	<select id="selectkhoaforMH">
		<option value="-1">Chọn khoa</option>
		<c:forEach var="khoa" items="${listKhoa}" varStatus="status">
			<option value="${status.index}">${khoa.tenKhoa }</option>
		</c:forEach>
	</select>
	<hr />
	<label>Ngành: </label>
	<select id="selectnganhforMH">
		<option value="-1">Chọn ngành</option>
	</select>
	<hr />
	<button onclick="">Thêm môn học</button>
	<hr/>
	<div id="content-table-monhoc"></div>
</body>
</html>
