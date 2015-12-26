<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(
			function() {
				$('#selectkhoaforCH').change(
						function() {
							var indexKhoa = $(
									"#selectkhoaforCH option:selected").attr(
									'value');
							selectNganhForCauHoi(indexKhoa);
							tableCauHoiForCauHoi('-1', 0, 0);
						});
				$('#selectnganhforCH').change(
						function() {
							var indexKhoa = $(
									"#selectkhoaforCH option:selected").attr(
									'value');
							var indexNganh = $(
									"#selectnganhforCH option:selected").attr(
									'value');
							selectMonHocForCauHoi(indexKhoa, indexNganh);
							tableCauHoiForCauHoi('-1', 0, 0);
						});
				$('#selectmonhocforCH').change(
						function() {
							var indexKhoa = $(
									"#selectkhoaforCH option:selected").attr(
									'value');
							var indexNganh = $(
									"#selectnganhforCH option:selected").attr(
									'value');
							var indexMonHoc = $(
									"#selectmonhocforCH option:selected").attr(
									'value');

							tableCauHoiForCauHoi(indexKhoa, indexNganh,
									indexMonHoc);
						});

			});
	function selectNganhForCauHoi(indexKhoa) {
		if (indexKhoa === '-1') {
			$('#selectnganhforCH').html(
					"<option value='-1'>Chọn ngành</option>");
		} else {
			$('#selectnganhforCH').load(
					"qlmonhocselectnganh?indexkhoa=" + indexKhoa);
		}
		selectMonHocForCauHoi('-1', '-1');
	}
	function selectMonHocForCauHoi(indexkhoa, indexnganh) {
		if (indexkhoa === '-1' || indexnganh == '-1') {
			$('#selectmonhocforCH').html(
					"<option value='-1'>Chọn môn học</option>");
		} else {
			$('#selectmonhocforCH').load(
					"qlcauhoiselectmonhoc?indexkhoa=" + indexkhoa
							+ "&indexnganh=" + indexnganh);
		}
	}
	function tableCauHoiForCauHoi(indexkhoa, indexnganh, indexmonhoc) {
		if (indexkhoa === '-1' || indexnganh === '-1' || indexmonhoc === '-1') {
			$('#content-table-cauhoi').html("");
		} else {
			$('#content-table-cauhoi').load(
					'qlcauhoitablecauhoi?indexkhoa=' + indexkhoa
							+ '&indexnganh=' + indexnganh + '&indexmonhoc='
							+ indexmonhoc);
		}
	}
	function addCauHoi() {
		var indexkhoa = $("#selectkhoaforCH option:selected").attr('value');
		var indexnganh = $("#selectnganhforCH option:selected").attr('value');
		var indexmonhoc = $("#selectmonhocforCH option:selected").attr('value');
		if (indexkhoa === '-1') {
			alert("Bạn chưa chọn khoa!");
			return;
		}
		if (indexnganh === '-1') {
			alert("Bạn chưa chọn ngành!");
			return;
		}
		if (indexmonhoc === '-1') {
			alert("Bạn chưa chọn môn học!");
			return;
		}

		window.open('qlcauhoiaddcauhoi?indexkhoa=' + indexkhoa + '&indexnganh='
				+ indexnganh + '&indexmonhoc=' + indexmonhoc, '_blank');
	}
	function editCauHoi(idCauHoi) {
		var indexkhoa = $("#selectkhoaforCH option:selected").attr('value');
		var indexnganh = $("#selectnganhforCH option:selected").attr('value');
		var indexmonhoc = $("#selectmonhocforCH option:selected").attr('value');
		if (indexkhoa === '-1') {
			alert("Bạn chưa chọn khoa!");
			return;
		}
		if (indexnganh === '-1') {
			alert("Bạn chưa chọn ngành!");
			return;
		}
		if (indexmonhoc === '-1') {
			alert("Bạn chưa chọn môn học!");
			return;
		}
		window.open('qlcauhoiaddcauhoi?indexkhoa=' + indexkhoa + '&indexnganh='
				+ indexnganh + '&indexmonhoc=' + indexmonhoc + '&idCauHoi='
				+ idCauHoi, '_blank');
	}
	function deleteCauHoi(indexcauhoi) {
		if (confirm('Bạn có chắc muốn xóa câu hỏi này không?'))
			$.post("deletecauhoi", {
				indexcauhoi : indexcauhoi
			}).done(
					function(data) {
						alert(data);
						if (data === "success") {
							var indexKhoa = $(
									"#selectkhoaforCH option:selected").attr(
									'value');
							var indexNganh = $(
									"#selectnganhforCH option:selected").attr(
									'value');
							var indexMonHoc = $(
									"#selectmonhocforCH option:selected").attr(
									'value');

							tableCauHoiForCauHoi(indexKhoa, indexNganh,
									indexMonHoc);
						}
					});
	}
</script>
</head>
<body>
	<h1>Xin chào Quản lí Câu hỏi</h1>

	<label>Khoa: </label>
	<select id="selectkhoaforCH">
		<option value="-1">Chọn khoa</option>
		<c:forEach var="khoa" items="${listKhoa}" varStatus="status">
			<option value="${status.index}">${khoa.tenKhoa }</option>
		</c:forEach>
	</select>
	<hr />
	<label>Ngành: </label>
	<select id="selectnganhforCH">
		<option value="-1">Chọn ngành</option>
	</select>
	<hr />
	<label>Môn học: </label>
	<select id="selectmonhocforCH">
		<option value="-1">Chọn môn học</option>
	</select>
	<hr />
	<button onclick="addCauHoi()">Thêm câu hỏi</button>
	<hr />
	<div id="content-table-cauhoi"></div>
</body>
</html>