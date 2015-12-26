<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE >
<html>
<head>
<script src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thêm câu hỏi</title>
<script type="text/javascript">
	var isEdit = false;
	$(document)
			.ready(
					function() {
						setup($("input[id='doKhoHidden']").val());

						$('#form-addcauhoi')
								.on(
										'submit',
										function(e) {
											if (isEdit) {
												if (!confirm('Bạn có chắc muốn cập nhật câu hỏi không? Kiểm tra đính kèm hình ảnh nếu có!!')) {
													e.preventDefault();
												}
											}
										});
					});

	function setup(doKho) {
		disable();
		if (doKho === '1') {
			$("input[id='de']").attr('checked', true);
		} else if (doKho === '2') {
			$("input[id='trungbinh']").attr('checked', true);
		} else if (doKho === '3') {
			$("input[id='kho']").attr('checked', true);
		} else {
			$("input[id='de']").attr('checked', true);
			$("input[id='idCauHoi']").val('0');
			enable();
		}
	}
	function editForm() {
		enable();
		$("#dsHinh").css("visibility", "hidden");
		$("#cancel").css("visibility", "visible");
		$("#submit").val("Update câu hỏi");
		isEdit = true;
	}
	function enable() {
		$("#noiDung").attr("disabled", false);
		$("#giaiThich").attr("disabled", false);
		$("#hinh").attr("disabled", false);
		$("#submit").attr("disabled", false);
		$("input[name='doKho']").attr("disabled", false);
		$("#edit").css("visibility", "hidden");
		$("#cancel").css("visibility", "hidden");
	}
	function disable() {
		$("#noiDung").attr("disabled", true);
		$("#giaiThich").attr("disabled", true);
		$("#hinh").attr("disabled", true);
		$("#submit").attr("disabled", true);
		$("#edit").css("visibility", "visible");
		$("#cancel").css("visibility", "hidden");
		$("#dsHinh").css("visibility", "visible");
		$("input[name='doKho']").attr("disabled", true);
	}
	
</script>
</head>
<body>
	<h1>Thêm câu hỏi mới</h1>
	<h2>${addCauHoiKhoa.tenKhoa}&nbsp;&gt;&gt;&nbsp;${addCauHoiNganh.tenNganh}&nbsp;&gt;&gt;&nbsp;${addCauHoiMonHoc.tenMonHoc}</h2>
	<form id="form-addcauhoi" action="addcauhoi" method="post"
		enctype="multipart/form-data">
		<fieldset>
			<legend>Thông tin câu hỏi</legend>
			<input type="hidden" name="indexkhoa" value="${addCauHoiKhoaIndex}">
			<input type="hidden" name="indexnganh" value="${addCauHoiNganhIndex}">
			<input type="hidden" name="indexmonhoc"
				value="${addCauHoiMonHocIndex}"> <input type="hidden"
				name="id" id="idCauHoi" value="${ addCauHoiCauHoi.id}"> <label
				for="noiDung">Nội dung câu hỏi:</label> <br />
			<textarea id="noiDung" name="noiDung" rows="5" cols="50">${addCauHoiCauHoi.noiDung}</textarea>
			<br /> <label>Độ khó:</label><input type="hidden" id="doKhoHidden"
				value="${addCauHoiCauHoi.doKho}"> <label for="de">&nbsp;&nbsp;Dễ</label>
			<input type="radio" name="doKho" value="1" id="de" /> <label
				for="trungbinh">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Trung bình</label> <input
				type="radio" name="doKho" value="2" id="trungbinh" /> <label
				for="kho">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Khó</label> <input
				type="radio" name="doKho" value="3" id="kho" /> <br /> <label
				for="giaiThich">Giải thích cho đáp án:</label> <br />
			<textarea id="giaiThich" name="giaiThich" rows="5" cols="50">${addCauHoiCauHoi.giaiThich}</textarea>
			<br /> <label for="hinh">Hình ảnh cho câu hỏi:</label> <input
				type="file" name="hinh" id="hinh" multiple /> <br />
			<pre id="dsHinh">${addCauHoiCauHoi.convertHinhToTagA() }</pre>
		</fieldset>
		<input type="submit" value="Thêm câu hỏi" id="submit" />
		<button id="edit" onclick="editForm()" type="button">Edit</button>
		<button id="cancel" onclick="disable()" type="button">Cancel</button>
	</form>
	<%@ include file="qlcauhoi-adddapan.jsp"%>
</body>
</html>