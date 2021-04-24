<%@page import="com.gadgetBadget.model.Researcher"%>
<%@page import="com.gadgetBadget.service.ImpReasercher"%>
<%@page import="com.gadgetBadget.service.IResearcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String getid =request.getParameter("id");
	if(getid != null){
		int id = Integer.parseInt(getid);
		IResearcher service = new ImpReasercher();
		Researcher researcher = service.getResearcher(id);
%>
<center>
	
<div style="margin: 10px;">
<a href="researchers.jsp" > <button style="cursor: pointer;padding: 3px;"> &notin;- back</button></a>

	<h3>Update Researcher</h3>
</div>
<div >
<form action="updateResearcher" method="post"  style=padding: 15px;border:1px;">
	<div  style="margin-bottom: 15px;">
	<label for="name">Name</label>
	<input type="text" placeholder="Researcher Name" value="<%=researcher.getName() %>" name="name" id="name" required="required"> 
	</div>
	<div  style="margin-bottom: 15px;">
	<label for="project">Choose a Project Type:</label>
		<select name="project" id="project">
		  <option <%if (researcher.getName().equals("project1")){ %> selected="selected"<%} %> value="project1">project1</option>
		  <option <%if (researcher.getName().equals("project2")){ %> selected="selected"<%} %> value="project2">project2</option> 
		</select>
		</div>
		<input type="hidden" name="id" value="<%=researcher.getId() %>">
		<div  style="margin-bottom: 15px;">
		<label for="payment">Payment</label>
		<input type="number" placeholder="enter payment" value="<%=researcher.getPayment() %>" name="payment" id="payment" required="required">
		</div>
		<div  style="margin-bottom: 15px;">
		<label for="rel">Contact number</label>
		<input type="tel" placeholder="enter contact number" value="<%=researcher.getContact() %>" name="tel" id="tel" required="required"  pattern="[0-9]{3}[0-9]{3-[0-9]{4}">
		</div>
		<div>
		<button type="submit" style="margin-right: 5px;">Save</button><button type="reset">Reset</button>
		</div>
</form>
</div>
</center>
<%}else{ %>
<center>
<div style="margin: 10px;">
<a href="researchers.jsp" > <button style="cursor: pointer;padding: 3px;"> &notin;- back</button></a>

	<h3>Add Researcher</h3>
</div>
<div >
<form action="addResearcher" method="post"  style=padding: 15px;border:1px;">
	<div  style="margin-bottom: 15px;">
	<label for="name">Name</label>
	<input type="text" placeholder="Researcher Name" name="name" id="name" required="required"> 
	</div>
	<div  style="margin-bottom: 15px;">
	<label for="project">Choose a Project Type:</label>
		<select name="project" id="project">
		  <option value="project1">project1</option>
		  <option value="project2">project2</option> 
		</select>
		</div>
		<div  style="margin-bottom: 15px;">
		<label for="payment">Payment</label>
		<input type="number" placeholder="enter payment" name="payment" id="payment" required="required">
		</div>
		<div  style="margin-bottom: 15px;">
		<label for="rel">Contact number</label>
		<input type="tel" placeholder="enter contact number" name="tel" id="tel" required="required"  pattern="[0-9]{3}[0-9]{3-[0-9]{4}">
		</div>
		<div>
		<button type="submit" style="margin-right: 5px;">Save</button><button type="reset">Reset</button>
		</div>
</form>
</div>
</center>
<%} %>
</body>
</html>