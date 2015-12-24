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
	var dialog, form;
	$(document).ready(function() {
		$('#table_khoa').DataTable();

		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 350,
			width : 350,
			modal : true,
			buttons : {
				"Tạo khoa" : addKhoa,
				Cancel : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				// 				allFields.removeClass("ui-state-error");
			}
		});
		form = dialog.find("form").on("submit", function(event) {
			event.preventDefault();
			addKhoa();
		});
	});

	function addKhoa() {
		var maKhoa = $("input[name='maKhoa']").val();
		var tenKhoa = $("input[name='tenKhoa']").val();
		dialog.dialog("close");
		$.post("addkhoa", {
			maKhoa : maKhoa,
			tenKhoa : tenKhoa
		}).done(function(data) {
			alert(data);
		});

	}
	function opendialog() {
		dialog.dialog("open");
	}
</script>
</head>
<body>
	<h1>Xin chào Quản lí Khoa</h1>
	<button onclick="opendialog()">Thêm Khoa</button>
	<hr />
	<table id="table_khoa" class="display">
		<thead>
			<tr>
				<th>STT</th>
				<th>Mã khoa</th>
				<th>Tên khoa</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="khoa" items="${listKhoa}" varStatus="status">
				<tr>
					<td>${status.index +1}</td>
					<td>${khoa.maKhoa }</td>
					<td>${khoa.tenKhoa }</td>
					<td><button>Edit</button>
						<button>Delete</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- Dialog  -->
	<div id="dialog-form" title="Tạo khoa mới">
		<p class="validateTips">Vui lòng điền thông tin của khoa mới.</p>
		<form action="addkhoa" enctype="application/x-www-form-urlencoded"
			method="post" accept-charset="UTF-8">
			<fieldset>
				<label for="maKhoa">Mã khoa: </label> <input type="text"
					name="maKhoa" id="maKhoa" value=""
					class="text ui-widget-content ui-corner-all" /> <br /> <label
					for="tenKhoa">Tên Khoa: </label> <input type="text" name="tenKhoa"
					id="tenKhoa" value="" class="text ui-widget-content ui-corner-all" />

				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>
</body>
</html>
