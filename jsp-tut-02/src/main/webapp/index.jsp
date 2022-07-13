<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<% 
	String title = (String)request.getAttribute("title");
	title = title == null ? "Home" : title;
%>
<title><%= title %></title>

<jsp:include page="common/res.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container">
		<h2 class="text-primary position-absolute top-50 start-50 translate-middle">Welcome From Zero to
			Pro Bootcamp</h2>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>