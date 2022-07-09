<%@page import="java.util.ArrayList"%>
<%@page import="com.mmit.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="common/res.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container">
		<div class="row py-4">
			<div class="col-10">
				<h2 class="">Student</h2>
			</div>
			<div class="col-2">
				<a href="student-add" class="btn btn-primary "><i
					class="fa-solid fa-plus fa-sm"></i>Add Student</a>
			</div>
		</div>
	</div>

	<hr>
	
	<div class="container">
		<div class="py-3">
			<table class="table table-hover">
				<thead>
					<tr>
						<td>No</td>
						<td>Student Name</td>
						<td>Phone</td>
						<td>Email</td>
						<td>Batch</td>
						<td>Registration Date</td>
						<td>Old Student</td>
					</tr>
					
				</thead>
				<tbody>
					<%
						List<Student> list = (ArrayList<Student>) session.getAttribute("students");
						if(list == null)
							list = new ArrayList();
						
						for(int i = 0; i < list.size(); i++){
							Student std = list.get(i);
							%>
							<tr>
								<td><%=(i + 1) %></td>
								<td><%=std.getName() %></td>
								<td><%=std.getPhone() %></td>
								<td><%=std.getEmail() %></td>
								<td><%=std.getBatchName() %></td>
								<td><%=std.getRegisteredDate() %></td>
								<td><%=std.isOld_student() %></td>
							</tr>
					<% 		
						}
						%>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>