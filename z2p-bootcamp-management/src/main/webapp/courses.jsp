
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
						<a href="course-add" class="btn btn-primary">Add Course</a>
					</div>
				</div>
				<div class="pt-3">
					<table class="table table-bordered">
						<thead class="table-dark">
							<tr>
								<td>No</td>
								<td>Course Name</td>
								<td>Level</td>
								<td>Content</td>
								<td>Created At</td>
								<td>Updated At</td>
								<td>Action</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.Courses}" var="c" varStatus="i">
								<tr>
									<td>${i.count }</td>
									<td>${c.name }</td>
									<td>${c.level.name }</td>
									<td>${c.content}</td>
									<td>${c.created_at }</td>
									<td>${c.updated_at }</td>
									<td>

										<div class="row">
											<div class="col-4">
												<c:url var="update" value="course-add">
													<c:param name="CourseID">${c.id }</c:param>
												</c:url>
												<a href="${update}" class="btn btn-success">Update</a>
											</div>
											<div class="col-4">
												<a href="#" class="btn btn-danger btn-delete" data-id="${c.id }">Delete</a>
											</div>

										</div>
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
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.btn-delete').click(function(event) {
				var c_id = $(this).attr("data-id")
				if(confirm("Are you sure to delete it?"))
				{
					//This is for get method
					window.location.href = "course-delete?course-id=" + c_id;
				}

			});
		});
	</script>
</body>
</html>