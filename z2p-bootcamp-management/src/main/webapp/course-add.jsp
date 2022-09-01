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
	<div class="container">
		<div class="row py-3">
			<div class="col-10">
				<h1>${requestScope.Title }</h1>
			</div>
			<div class="col-2 text-center">
				<a href="courses" class="btn btn-primary">Back to Course List</a>
			</div>
		</div>

		<div class="row">
			<form action="course-add" method="Post" class="col-5 offset-3">
				<div class="mb-3">
					<input type="hidden" class="form-control" id="course-id" name="course-id"
						readonly="readonly" value="${requestScope.Course.id}">

				</div>
				<div class="mb-3">
					<label for="course-name" class="form-label">Course Name</label> <input
						type="text" class="form-control" id="course-name" name="course-name"
						required="required" value="${requestScope.Course.name}">

				</div>
				<div class="mb-3">
					<label for="level" class="form-label">Level</label>

					<select name="level-id" class="form-control">
						<c:forEach var="l" items="${requestScope.Levels}">
							<option value="${l.id }" ${requestScope.Course.level.id eq l.id ? 'selected' : ''}>${l.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3">
					<label for="course-content" class="form-label">Course Content</label>
					<textarea id="summernote" name="course-content" rows="4" cols="50"
						value="${requestScope.Course.content}" required="required"
						class="form-control">
					</textarea>
				</div>

				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$("#summernote").summernote();
		});
		
	</script>
</body>
</html>