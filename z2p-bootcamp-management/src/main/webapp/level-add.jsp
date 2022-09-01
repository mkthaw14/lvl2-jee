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
	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row py-3">
			<div class="col-10">
				<h1>${requestScope.Title }</h1>
			</div>
			<div class="col-2 text-center">
				<a href="levels" class="btn btn-primary">Back to Level List</a>
			</div>
		</div>

		<div class="row">
			<form action="level-add" method="Post" class="col-5 offset-3">
				<div class="mb-3">
					<input type="hidden" class="form-control" id="lvl-id" name="lvl-id"
						readonly="readonly"
						value="${requestScope.Level.id}" >

				</div>
				<div class="mb-3">
					<label for="lvl-name" class="form-label">Level Name</label> <input
						type="text" class="form-control" id="lvl-name" name="lvl-name"
						required="required"
						value="${requestScope.Level.name}">

				</div>
				<div class="mb-3">
					<label for="lvl-duration" class="form-label">Level Duration</label>
					<input type="number" class="form-control" id="lvl-duration"
						required="required"
						name="lvl-duration" value="${requestScope.Level.duration}">
				</div>
				<div class="mb-3">
					<label for="lvl-fees" class="form-label">Level Fees</label> <input
						type="number" class="form-control" id="lvl-fees" name="lvl-fees"
						required="required"
						value="${requestScope.Level.fees}">
				</div>

				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>