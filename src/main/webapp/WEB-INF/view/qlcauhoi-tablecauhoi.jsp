<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
		datatable = $('#table_cauhoi').DataTable();
	});
</script>

</head>
<body>
	<table id="table_cauhoi" class="display">
		<thead>
			<tr>
				<th>STT</th>
				<th>Nội dung câu hỏi</th>
				<th>Hình ảnh</th>
				<th>Số lượng đáp án</th>
				<th>Độ khó</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="cauhoi" items="${selectMonHocForCauHoi.dsCauHoi}"
				varStatus="status">
				<tr>
					<td>${status.index +1}</td>
					<td><pre>${cauhoi.noiDung }</pre></td>
					<s:eval
						expression="T(util.ToString).toStringImage(cauhoi.dsHinh)"
						var="dsHinh" />
					<td><pre>${dsHinh }</pre></td>
					<s:eval
						expression="T(util.ToString).toString(cauhoi.dsDapAn)"
						var="dsDapAn" />
					<td><pre>${dsDapAn}</pre></td>
					<td>${cauhoi.doKho}</td>
					<td><button>Edit</button>
						<button>Delete</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>