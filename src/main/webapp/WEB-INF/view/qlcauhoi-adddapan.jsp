<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--  jquery and jquery ui -->
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>

<!-- Datatable and editor -->
<link rel="stylesheet" type="text/css" href="css/dataTables.css">
<script type="text/javascript" charset="utf8" src="js/dataTables.js"></script>
<script type="text/javascript">
	var dialogDapAn, form;
	var isEditDapAn = false;
	$(document).ready(function() {
		$('#table_dapan').DataTable();
		dialogDapAn = $("#dialog-form-adddapan").dialog({
			autoOpen : false,
			height : 450,
			width : 450,
			modal : true,
			buttons : {
				"Tạo đáp án" : addDapAn,
				Cancel : function() {
					dialogDapAn.dialog("close");
				}
			},
			close : function() {
				form[0].reset();
				// 				allFields.removeClass("ui-state-error");
			}
		});
		form = dialogDapAn.find("form");
		// 		.on("submit", function(event) {
		// 			event.preventDefault();
		// 			addDapAn();
		// 		});
	});
	function addDapAn() {
		if (isEditDapAn) {
			if (confirm('Bạn có chắc update đáp án chứ? Kiểm tra hình ảnh đính kèm nếu có!')) {
				form.submit();
				isEditDapAn = false;
			}
		} else {
			form.submit();
		}
	}
	function openDialogDapAn() {
		$("input[name='id']").val("0");
		$("#noiDungDA").val("");
		$("#laDADung").prop('checked', false);
		dialogDapAn.dialog("open");
	}
	function openDialogForEditDapAn(id, noiDungDA, laDADung) {
		isEditDapAn = true;
		$("input[name='id']").val(id);
		$("#noiDungDA").val(noiDungDA);
		if (laDADung === "true")
			$("#laDADung").prop('checked', true);
		else
			$("#laDADung").prop('checked', false);
		dialogDapAn.dialog("open");
	}
	function deleteDapAn(indexdapan) {
		if (confirm('Bạn có chắc muốn xóa đáp án này không?')) {
			$.post("deletedapan", {
				indexdapan : indexdapan
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
	<fieldset>
		<legend>Thông tin đán án</legend>
		<c:if test="${addCauHoiCauHoi ne null}">
			<button onclick="openDialogDapAn()">Thêm đáp án</button>
			<hr />
			<table id="table_dapan" class="display">
				<thead>
					<tr>
						<th>STT</th>
						<th>Nội dung đáp án</th>
						<th>Hình</th>
						<th>Là đáp án đúng</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dapAn" items="${addCauHoiCauHoi.dsDapAn}"
						varStatus="status">
						<tr>
							<td>${status.index +1}</td>
							<td>${dapAn.noiDungDA}</td>
							<td>${dapAn.convertHinhToTagA()}</td>
							<td><c:if test="${dapAn.laDADung}">
											X
										</c:if></td>
							<td><button
									onclick="openDialogForEditDapAn('${dapAn.id}','${dapAn.noiDungDA}' , '${dapAn.laDADung}')">Edit</button>
								<button onclick="deleteDapAn('${status.index}')">Delete</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${addCauHoiCauHoi eq null}">
			<p>Chưa tạo câu hỏi.</p>
		</c:if>
	</fieldset>
	<div id="dialog-form-adddapan" title="Tạo đáp án mới">
		<p class="validateTips">Vui lòng điền thông tin của đáp án mới.</p>
		<form action="adddapan" enctype="multipart/form-data" method="post">
			<fieldset>
				<input type="hidden" name="id" value="0" /> <input type="hidden"
					name="indexkhoa" value="${addCauHoiKhoaIndex}"> <input
					type="hidden" name="indexnganh" value="${addCauHoiNganhIndex}">
				<input type="hidden" name="indexmonhoc"
					value="${addCauHoiMonHocIndex }"> <label for="noiDungDA">Nội
					dung đáp án: </label> <br />
				<textarea class="text ui-widget-content ui-corner-all"
					id="noiDungDA" name="noiDungDA" cols="35" rows="5"></textarea>
				<br /> <label for="hinh">Chọn ảnh: </label> <input type="file"
					id="hinh" name="hinhs" class="text ui-widget-content ui-corner-all" />
				<br /> <label for="laDADung">Là đáp án đúng:</label> <input
					type="checkbox" id="laDADung" name="laDADung" value="true" />
				<!-- Allow form submission with keyboard without duplicating the dialog button -->
				<input type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>
</body>
</html>