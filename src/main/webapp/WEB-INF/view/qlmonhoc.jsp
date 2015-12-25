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
	var dialogMonHoc, form;
	$(document).ready(
			function() {
				dialogMonHoc = $("#dialog-form-monhoc").dialog({
					autoOpen : false,
					height : 450,
					width : 350,
					modal : true,
					buttons : {
						"Tạo môn học" : addMonHoc,
						Cancel : function() {
							dialogMonHoc.dialog("close");
						}
					},
					close : function() {
						form[0].reset();
						// 				allFields.removeClass("ui-state-error");
					}
				});
				form = dialogMonHoc.find("form").on("submit", function(event) {
					event.preventDefault();
					addMonHoc();
				});

				// su kiện cho 2 select
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
	function addMonHoc() {
		var id = $("input[id='idMonHoc']").val();
		var maMH = $("input[name='maMH']").val();
		var tenMonHoc = $("input[name='tenMonHoc']").val();
		var thoiGian = $("input[name='thoiGian']").val();
		var soLgCauHoi = $("input[name='soLgCauHoi']").val();
		var indexKhoa = $("#selectkhoaforMH option:selected").attr('value');
		var indexNganh = $("#selectnganhforMH option:selected").attr('value');
		if (indexKhoa === "-1") {
			alert("Bạn chưa chọn Khoa!");
		} else if (indexNganh === "-1") {
			alert("Bạn chưa chọn Ngành!");
		} else {
// 			alert(id  + " : " + maMH +  " : " + tenMonHoc + " : " + thoiGian);
			dialogMonHoc.dialog("close");
			$.post("addmonhoc", {
				id : id,
				maMH : maMH,
				tenMonHoc : tenMonHoc,
				thoiGian : thoiGian,
				soLgCauHoi : soLgCauHoi,
				indexKhoa : indexKhoa,
				indexNganh : indexNganh
			}).done(
					function(data) {
						alert(data);
						if (data === "success") {
							var indexkhoa = $(
									"#selectkhoaforMH option:selected").attr(
									'value');
							var indexnganh = $(
									"#selectnganhforMH option:selected").attr(
									'value');
							selectMonHoc(indexkhoa, indexnganh);
						}
					});
		}
	}
	function opendialogMonHoc() {
		$("input[id='idMonHoc']").val('0');
		$("input[name='maMH']").val('');
		$("input[name='tenMonHoc']").val('');
		$("input[name='thoiGian']").val('');
		$("input[name='soLgCauHoi']").val('');
		dialogMonHoc.dialog("open");
	}

	function opendialogForEditMonHoc(id, maMH, tenMonHoc, thoiGian, soLgCauHoi) {
		$("input[id='idMonHoc']").val(id);
		$("input[name='maMH']").val(maMH);
		$("input[name='tenMonHoc']").val(tenMonHoc);
		$("input[name='thoiGian']").val(thoiGian);
		$("input[name='soLgCauHoi']").val(soLgCauHoi);
		dialogMonHoc.dialog("open");
	}
	function deleteMonHoc(indexMonHoc, tenMonHoc) {
		if (confirm('Bạn có chắc muốn xóa môn học ' + tenMonHoc + '?')) {
			var indexKhoa = $("#selectkhoaforMH option:selected").attr('value');
			var indexNganh = $("#selectnganhforMH option:selected").attr('value');
			
			$.post("deletemonhoc", {
				indexNganh : indexNganh,
				indexKhoa : indexKhoa,
				indexMonHoc : indexMonHoc
			}).done(function(data) {
				alert(data);
				if (data === "success") {
					selectMonHoc(indexKhoa, indexNganh);
				}
			});
		}
	}
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
	<button onclick="opendialogMonHoc()">Thêm môn học</button>
	<hr />
	<div id="content-table-monhoc"></div>
	<!-- 	dialog -->
	<div id="dialog-form-monhoc" title="Tạo môn học mới">
		<p class="validateTips">Vui lòng điền thông tin của môn học mới.</p>
		<form action="addkhoa" enctype="application/x-www-form-urlencoded"
			method="post" accept-charset="UTF-8">
			<input type="hidden" id="idMonHoc" name="id" value="0" />
			<fieldset>
				<label for="maMH">Mã môn học: </label> <input type="text"
					name="maMH" id="maMH" value=""
					class="text ui-widget-content ui-corner-all" /> <br /> <label
					for="tenMonHoc">Tên môn học: </label> <input type="text"
					name="tenMonHoc" id="tenMonHoc" value=""
					class="text ui-widget-content ui-corner-all" /> <br /> <label
					for="thoiGian">Thời gian: </label> <input type="text"
					name="thoiGian" id="thoiGian" value=""
					class="text ui-widget-content ui-corner-all" /> <br /> <label
					for="soLgCauHoi">Số lương câu hỏi: </label> <input type="text"
					name="soLgCauHoi" id="soLgCauHoi" value=""
					class="text ui-widget-content ui-corner-all" />

				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>
</body>
</html>
