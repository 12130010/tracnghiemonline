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
	var dialogKhoa, form;
	$(document).ready(function() {
		$('#table_khoa').DataTable();

		dialogKhoa = $("#dialog-form").dialog({
			autoOpen : false,
			height : 350,
			width : 350,
			modal : true,
			buttons : {
				"Tạo khoa" : addKhoa,
				Cancel : function() {
					dialogKhoa.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				// 				allFields.removeClass("ui-state-error");
			}
		});
		form = dialogKhoa.find("form").on("submit", function(event) {
			event.preventDefault();
			addKhoa();
		});
	});

	function addKhoa() {
		var id = $("input[name='id']").val();
		var maKhoa = $("input[name='maKhoa']").val();
		var tenKhoa = $("input[name='tenKhoa']").val();
		dialogKhoa.dialog("close");
		$.post("addkhoa", {
			id: id,
			maKhoa : maKhoa,
			tenKhoa : tenKhoa
		}).done(function(data) {
			alert(data);
			if (data === "success") {
			location.reload();
			}
		});

	}
	function opendialogKhoa() {
		$("input[name='id']").val('0'); 
		$("input[name='maKhoa']").val('');
		$("input[name='tenKhoa']").val(''); 
		dialogKhoa.dialog("open");
	}
	function opendialogForEditKhoa(id, maKhoa,tenKhoa) {
		$("input[name='id']").val(id); 
		$("input[name='maKhoa']").val(maKhoa);
		$("input[name='tenKhoa']").val(tenKhoa); 
		dialogKhoa.dialog("open");
	}
	
	function deleteKhoa(indexKhoa, tenKhoa) {
		if (confirm('Bạn có chắc muốn xóa khoa ' + tenKhoa + '?')) {
			$.post("deletekhoa", {
			indexKhoa : indexKhoa,
			}).done(function(data) {
			alert(data);
			if (data === "success") {
				location.reload();
				}
			});
		}
	}
</script>
</head>
<body>
	<h1>Xin chào Quản lí Khoa</h1>
	<button onclick="opendialogKhoa()">Thêm Khoa</button>
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
					<td><button
							onclick="opendialogForEditKhoa('${khoa.id}', '${khoa.maKhoa }', '${khoa.tenKhoa }')">Edit</button>
						<button onclick="deleteKhoa(${status.index}, '${khoa.tenKhoa }')">Delete</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- Dialog  -->
	<div id="dialog-form" title="Tạo khoa mới">
		<p class="validateTips">Vui lòng điền thông tin của khoa mới.</p>
		<form action="addkhoa" enctype="application/x-www-form-urlencoded"
			method="post" accept-charset="UTF-8">
			<input type="hidden" name="id" value="0" />
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
