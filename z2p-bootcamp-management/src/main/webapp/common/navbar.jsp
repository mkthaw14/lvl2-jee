<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="home" value="/home">
</c:url>
<c:url var="level" value="/levels">
</c:url>

<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
	<div class="container">
		<ul class="navbar-nav">
			<li class="nav-item"><a href="${home}" class="nav-link">Home</a></li>
			<li class="nav-item"><a href="${level}" class="nav-link">Level</a></li>
			<li class="nav-item"><a href="batches" class="nav-link">Batch</a></li>
			<li class="nav-item"><a href="courses" class="nav-link">Course</a></li>
		</ul>
	</div>
</nav>