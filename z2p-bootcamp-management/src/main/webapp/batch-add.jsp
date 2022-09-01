<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${requestScope.Title }</title>
<jsp:include page="common/res.jsp"></jsp:include>
</head>
<body>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row py-3">
			<div class="col-10">
				<h2 class="">${requestScope.Title }</h2>
			</div>
			<div class="col-2 text-center">
				<a href="batches" class="btn btn-primary">Back To Batch List</a>
			</div>
		</div>
		<div class="row">
			<form class="mt-5 col-5" action="batch-add" method="post">
				<div class="mb-3">
					<label for="" class="form-label">Batch Name</label> <input
						type="text" class="form-control" name="batch-name"
						value="${Batch.name}"
						required="required">
				</div>
				<div class="mb-3">
					<label for="" class="form-label">Start Date</label> <input
						type="date" class="form-control" name="start-date"
						value="${Batch.startDate}"
						required="required">
				</div>
				<div class="mb-3">
					<label for="" class="form-label">Level</label> 
					<select name="batch-level"
						id="" required="required" class="form-control"
						>
						<c:forEach items="${requestScope.Levels }" var="level">
							<c:url var="currentSelected" value="${requestScope.Batch.level.id eq level.id ? 'selected': ''}"></c:url>
							<option value="${level.id }" ${currentSelected}> ${level.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="pt-3">
					<div class="row gx-5">
						<div class="col-6">
							<button type="submit" class="btn btn-primary w-100">Create</button>
						</div>
						<div class="col-6">
							<button type="reset" class="btn btn-danger w-100">Cancel</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>