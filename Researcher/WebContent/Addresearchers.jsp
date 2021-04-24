<%@page import="com.gadgetBadget.model.Researcher"%>
<%@page import="com.gadgetBadget.service.ImpReasercher"%>
<%@page import="com.gadgetBadget.service.IResearcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GadgetBadget</title>
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

	<h2>Update Researcher</h2>
</div>
<div >
<form action="updateResearcher" method="post"  style=padding: 15px;border:1px;">
	<div  style="margin-bottom: 15px;">
	<label for="name">Researcher Name</label>
	<input type="text" placeholder="Researcher Name" value="<%=researcher.getName() %>" name="name" id="name" required="required"> 
	</div>
	<div  style="margin-bottom: 15px;">
	<label for="project">Choose a Project Type:</label>
		<select name="project" id="project">
		  <option <%if (researcher.getName().equals("project 24")){ %> selected="selected"<%} %> value="project 24">project 24</option>
		  <option <%if (researcher.getName().equals("HRM_project")){ %> selected="selected"<%} %> value="HRM_project">HRM_project</option> 
		  <option <%if (researcher.getName().equals("ERP")){ %> selected="selected"<%} %> value="ERP">ERP</option>
		  <option <%if (researcher.getName().equals("POS")){ %> selected="selected"<%} %> value="POS">POS</option> 
		 </select>
		</div>
		<input type="hidden" name="rId" value="<%=id%>">
		<div  style="margin-bottom: 15px;">
		<label for="payment">Amount Payable</label>
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

	<h2><b>Add Researcher</b></h2>
</div>
<div >
<form action="addResearcher" method="post"  style=padding: 15px;border:1px;">
	<div  style="margin-bottom: 15px;">
	<label for="name">Researcher Name</label>
	<input type="text" placeholder="Researcher Name" name="name" id="name" required="required"> 
	</div>
	<div  style="margin-bottom: 15px;">
	<label for="project">Choose a Project Type:</label>
		<select name="project" id="project">
		  <option value="project 24">project_24</option>
		 _ <option value="HRM_project">HRM_project</option> 
		  <option value="ERP">ERP </option>
		  <option value="POS">POS </option>
		</select>
		</div>
		<div  style="margin-bottom: 15px;">
		<label for="payment">Amount Payable</label>
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