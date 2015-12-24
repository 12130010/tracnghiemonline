<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>

<!-- Datatable and editor -->
<link rel="stylesheet" type="text/css" href="css/dataTables.css">


<script type="text/javascript" charset="utf8" src="js/dataTables.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#table_nganh').DataTable();

		$('#selectkhoa').change(function() {
			var index = $("#selectkhoa option:selected").attr('value');
			selectNganh(index);
		});

	});
</script>
</head>
<body>
	<table id="table_nganh" class="display">
		<thead>
			<tr>
				<th>STT</th>
				<th>Mã ngành</th>
				<th>Tên ngành</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody id="tbody_nganh">
			<c:forEach var="nganh" items="${selectKhoa.dsNganh}"
				varStatus="status">
				<tr>
					<td>${status.index +1}</td>
					<td>${nganh.maNganh }</td>
					<td>${nganh.tenNganh }</td>
					<td><button onclick="opendialogForEditNganh('${nganh.id}', '${nganh.maNganh }','${nganh.tenNganh }')">Edit</button>
						<button onclick="deleteNganh(${status.index}, '${nganh.tenNganh }')">Delete</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
