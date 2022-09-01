<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${requestScope.Title }</title>
<jsp:include page="common/res.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container">

		<div class="row py-3">
			<div class="col-10">
				<h1>${requestScope.Title}</h1>
			</div>
			<div class="col-2 text-center">
				<a href="level-add" class="btn btn-primary">Add Level</a>
			</div>

		</div>
		<table class="table">
			<thead>
				<tr>
					<td>Name</td>
					<td>Duration</td>
					<td>Fees</td>
					<td>Action</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.Levels }" var="level" varStatus="i">
					<tr>
						<td>${level.name }</td>
						<td>${level.duration }</td>
						<td>${level.fees }</td>
						<td>
							<div class="row">
								<div class="col-2">
									<c:url var="update" value="level-update">
										<c:param name="id">${level.id }</c:param>
									</c:url>
									<a href="${update}" class="btn btn-success">Update</a>
								</div>
								<div class="col-2">
									<c:url var="batch" value="batches">
										<c:param name="batch-lvl-id">${level.id }</c:param>
									</c:url>
									<a href="${batch}" class="btn btn-outline-warning">Batches(${level.batchCount})</a>
								</div>
								<div class="col-2">
									<c:url var="course" value="courses">
										<c:param name="course-lvl-id">${level.id }</c:param>
									</c:url>
									<a href="${course}" class="btn btn-outline-warning">Courses(${level.courseCount})</a>
								</div>
							</div>

						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>