<%@page import="com.mmit.entity.Batch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
        <div class="row gx-5 ">
            <div class="col-4 ">
                <div class="p-5 position-fixed">
                    <form class="mt-5" action="add-batch" method="post">
                        <div class="mb-3">
                            <label for="" class="form-label">Batch Name</label>
                            <input type="text" class="form-control" name="batch-name" required="required">
                        </div>
                        <div class="mb-3">
                            <label for="" class="form-label">Start Date</label>
                            <input type="date" class="form-control" name="start-date" required="required">
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
            <div class="col">
                <div class="p-5">
                    <h2 class="pb-3">Batch List</h2>
                    <div class="pt-3">
                        <table class="table table-bordered" >
                            <thead class="table-dark">
                                <tr>
                                    <td>No</td>
                                    <td>Batch Name</td>
                                    <td>Start Date</td>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            	List<Batch> list = (ArrayList)application.getAttribute("batches");
                            	
                            	if(list == null)
                            		list = new ArrayList();
                            		
                            	for(int x = 0; x < list.size(); x++) {
                            		Batch b = list.get(x);
                            %>
                            	<tr>
                                    <td><%= (x + 1) %></td>
                                    <td><%=b.getName() %></td>
                                    <td><%=b.getDate() %></td>
                                </tr>
                            <% 		
                            	}
                            %>
 
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
	
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>