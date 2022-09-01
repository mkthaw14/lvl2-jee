
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${requestScope.Title }</title>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="common/res.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container pb-5">
		<div class="col">
			<div class="">

				<div class="row py-3">
					<div class="col-10">
						<h1 class="">${requestScope.Title }</h1>
					</div>
					<div class="col-2 text-center">
						<a href="batch-add" class="btn btn-primary">Add Batch</a>
					</div>
				</div>
				<div class="pt-3">
					<table class="table table-bordered">
						<thead class="table-dark">
							<tr>
								<td>No</td>
								<td>Batch Name</td>
								<td>Start Date</td>
								<td>Level</td>
								<td>Action</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.Batches}" var="b"
								varStatus="i">
								<tr>
									<td>${i.count }</td>
									<td>${b.name }</td>
									<td>${b.startDate }</td>
									<td>${b.level.name}</td>
									<td>
										<c:url var="update" value="batch-add">
											<c:param name="BatchID">${b.id }</c:param>
										</c:url>
										<a href="${update}" class="btn btn-success">Update</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>