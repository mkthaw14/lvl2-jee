<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>${requestScope.Title }</title>

<jsp:include page="common/res.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container">
		<h2 class="text-center text-primary mt-5">Welcome From Zero to
			Pro Bootcamp</h2>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>