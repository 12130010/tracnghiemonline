<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Add Khoa</title>
<script type="text/javascript">
	var dialogNganh, form;
	$(document).ready(function() {
		$('#selectkhoa').change(function() {
			selectNganh("-1");
			var index = $("#selectkhoa option:selected").attr('value');
			selectNganh(index);
		});
		dialogNganh = $("#dialog-form-nganh").dialog({
			autoOpen : false,
			height : 350,
			width : 350,
			modal : true,
			buttons : {
				"Tạo ngành" : addNganh,
				Cancel : function() {
					dialogNganh.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				// 				allFields.removeClass("ui-state-error");
			}
		});
		form = dialogNganh.find("form").on("submit", function(event) {
			event.preventDefault();
			addNganh();
		});
	});
	function selectNganh(index) {
		if (index === '-1') {
			$('#content-table-nganh').html("");
		} else {
			$('#content-table-nganh').load(
					"qlnganhtablenganh?indexkhoa=" + index);
		}
	}

	function addNganh() {
		var id = $("input[id='idNganh']").val(); 
		var maNganh = $("input[name='maNganh']").val();
		var tenNganh = $("input[name='tenNganh']").val();
		var indexKhoa = $("#selectkhoa option:selected").attr('value');
		if (indexKhoa === "-1") {
			alert("Bạn chưa chọn khoa!")
		} else {
			dialogNganh.dialog("close");
			$.post("addnganh", {
				id: id,
				maNganh : maNganh,
				tenNganh : tenNganh,
				indexKhoa : indexKhoa
			}).done(function(data) {
				alert(data);
				if (data === "success") {
					var index = $("#selectkhoa option:selected").attr('value');
					selectNganh(index);
				}
			});
		}
	}
	function opendialogForEditNganh(id, maNganh,tenNganh) {
		$("input[id='idNganh']").val(id); 
		$("input[name='maNganh']").val(maNganh);
		$("input[name='tenNganh']").val(tenNganh); 
		dialogNganh.dialog("open");
	}
	
	function deleteNganh(indexNganh, tenNganh) {
		if (confirm('Bạn có chắc muốn xóa khoa ' + tenNganh + '?')) {
			var indexKhoa = $("#selectkhoa option:selected").attr('value');
			$.post("deletenganh", {
				indexNganh : indexNganh,
				indexKhoa : indexKhoa,
			}).done(function(data) {
				alert(data);
				if (data === "success") {
					var index = $("#selectkhoa option:selected").attr('value');
					selectNganh(index);
				}
			});
		}
	}
	function opendialogNganh() {
		$("input[id='idNganh']").val('0'); 
		$("input[name='maNganh']").val('');
		$("input[name='maNganh']").val(''); 
		dialogNganh.dialog("open");
	}
</script>
</head>
<body>
	<h1>Xin chào Quản lí Ngành</h1>
	<label>Khoa: </label>
	<select id="selectkhoa">
		<option value="-1">Chọn khoa</option>
		<c:forEach var="khoa" items="${listKhoa}" varStatus="status">
			<option value="${status.index}">${khoa.tenKhoa }</option>
		</c:forEach>
	</select>
	<hr />
	<button onclick="opendialogNganh()">Thêm ngành</button>
	<hr />
	<div id="content-table-nganh"></div>
	<!-- Dialog  -->
	<div id="dialog-form-nganh" title="Tạo khoa mới">
		<p class="validateTips">Vui lòng điền thông tin của ngành mới.</p>
		<form action="addkhoa" enctype="application/x-www-form-urlencoded"
			method="post" accept-charset="UTF-8">
			<input type="hidden" id="idNganh" name="id" value="0"/>
			<fieldset>
				<label for="maKhoa">Mã ngành: </label> <input type="text"
					name="maNganh" id="maKhoa" value=""
					class="text ui-widget-content ui-corner-all" /> <br /> <label
					for="tenKhoa">Tên ngành: </label> <input type="text"
					name="tenNganh" id="tenKhoa" value=""
					class="text ui-widget-content ui-corner-all" />

				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>
</body>
</html>
