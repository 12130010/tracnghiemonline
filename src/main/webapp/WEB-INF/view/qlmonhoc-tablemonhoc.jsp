<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>

<!-- Datatable and editor -->
<link rel="stylesheet" type="text/css" href="css/dataTables.css">


<script type="text/javascript" charset="utf8" src="js/dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		datatable = $('#table_monhoc').DataTable();
	});
</script>
</head>
<body>
	<table id="table_monhoc" class="display">
		<thead>
			<tr>
				<th>STT</th>
				<th>Mã môn học</th>
				<th>Tên môn học</th>
				<th>Số lượng câu hỏi</th>
				<th>Thời gian</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody id="tbody_monhoc">
			<c:forEach var="monhoc" items="${selectNganh.dsMonHoc}"
				varStatus="status">
				<tr>
					<td>${status.index +1}</td>
					<td>${monhoc.maMH }</td>
					<td>${monhoc.tenMonHoc }</td>
					<td>${monhoc.soLgCauHoi}</td>
					<td>${monhoc.thoiGian}</td>
					<td><button>Edit</button>
						<button>Delete</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>