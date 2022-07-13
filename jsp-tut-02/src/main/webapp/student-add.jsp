<%@page import="com.mmit.entity.Batch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=request.getAttribute("title") %></title>

<jsp:include page="common/res.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container">
		<div class="row mt-3">
			<h2 class="col-10">Add New Student</h2>
		</div>
	</div>

	<hr>
	<div class="container mb-5">
		<form action="add-student" method="post" class="form col-5 pb-5">
			<div class="mb-3">
				<label for="" class="form-label">Student Name</label> <input type="text"
					class="form-control" name="std-name" required="required">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Phone</label> <input type="text" class="form-control" name="std-phone" required="required">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Email</label> <input type="text" class="form-control" name="std-email" required="required">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Choose Batch</label> 
				<select class="form-select"
					name="batch-name">
					<% 
						List<Batch> batches = (ArrayList<Batch>) application.getAttribute("batches");
						if(batches == null)
							batches = new ArrayList<Batch>();
						
						for(Batch batch: batches) {
				    %>	
					    	<option value="<%= batch.getName() %>"> <%= batch.getName() %></option>
						<%
						}  %>
					
				</select>
			</div>
			<div class="mb-3">
				<label for="std" class="form-check-label">Old Student</label> <input type="checkbox" class="form-check-input"
					name="std-old" id="std">
			</div>
			
			<div class="row">
				<div class="col">
					<button class="btn btn-danger w-100" type="reset">Clear</button>
				</div>
				<div class="col">
					<button class="btn btn-primary w-100" type="submit">Save</button>
				</div>
			</div>
		</form>
	</div>


	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>