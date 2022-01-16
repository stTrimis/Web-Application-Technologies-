<%-- Τρίμης Στυλιανός-Αθανάσιος 1564 --%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="mypackage.*,java.util.*" %>
<%
request.setCharacterEncoding("utf-8");
	String name=request.getParameter("fname");
    String lastname=request.getParameter("lastname");
	String id=request.getParameter("id");
	String sem=request.getParameter("semester");
	String mail=request.getParameter("mail");
	String tablevisible[]={"ΑΡΙΘΜΟΣ ΜΗΤΡΩΟΥ","ΟΝΟΜΑ","ΕΠΙΘΕΤΟ","E-MAIL","ΕΞΑΜΗΝΟ","ΕΠΕΞΑΡΓΑΣΙΑ","ΔΙΑΓΡΑΦΗ"};
%>

<%
Vector <student> students=database.getInstance().print();
String action=(String)request.getAttribute("action");
boolean readonlyid=request.getAttribute("readonly")!=null?(boolean)request.getAttribute("readonly"):false;
String buttonname=(String)request.getAttribute("buttonname");
student edited=(student)request.getAttribute("editaction");
%>

<%
  String idvalue=edited!=null?edited.getId():id!=null?id:"";
  String namevalue=edited!=null?edited.getName():name!=null?name:"";
  String lastnamevalue=edited!=null?edited.getLastname():lastname!=null?lastname:"";
  String mailvalue=edited!=null?edited.getEmail():mail!=null?mail:"";
  String semestervalue=edited!=null?String.valueOf(edited.getSemester()):sem!=null?sem:"1";
  String buttonvalue=buttonname!=null?buttonname:"SAVE";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students</title>

</head>
<body>

<form action=handler?action=table method="POST">
<label for="id" class="lbstyle">ΑΡΙΘΜΟΣ ΜΗΤΡΩΟΥ</label>
<input type="text" name="id" value="<%=idvalue%>" class="inputtext" <%if(readonlyid) {%>readonly<%}%>/><br/>
<label for="fname" class="labelstyle">ΟΝΟΜΑ</label>
<input type="text" name="fname" class="inputtext" value="<%=namevalue %>" /><br>
<label for="lastname" class="labelstyle">ΕΠΙΘΕΤΟ</label>
<input type="text" name="lastname" class="inputtext" value="<%= lastnamevalue %>" /><br>
<label for="number" class="labelstyle">ΕΞΑΜΗΝΟ</label>
<input id="number" type="number" min="1" max="32" step="1"  class="inputtext" name="semester" value="<%=semestervalue%>"/><br>
<label for="mail" class="labelstyle">E-MAIL</label>
<input type="text" class="inputtext" name="mail" value="<%=mailvalue %>" /><br><br>
<input type="submit" name="submit" value=<%=buttonvalue %> class="buttonform" style="margin-left:50px;"/>
</form>

<%out.println("<center>"); %>
<table border="3" class="tablestyle">
<tr>
<%
   for(String x:tablevisible)
   {
%>
<th><%=x %></th>
<%}%>
</tr>
<%
for(student s:students)
{%>
<tr>
<td><%=s.getId() %></td>
<td><%=s.getName() %></td>
<td><%=s.getLastname() %></td>
<td><%=s.getEmail() %></td>
<td><%=s.getSemester() %></td>
<td>
<form action="handler" method="POST">
<input type="submit" name="edit" value="ΕΠΕΞΕΡΓΑΣΙΑ" class="buttontable"/>
<input type="hidden" name="eid" id="eid" value="<%=s.getId() %>"/>
</form>
</td>
<td>
<form action="handler" method="POST">
<input type="submit" name="delete" value="ΔΙΑΓΡΑΦΗ" class="buttontable"/>
<input type="hidden" name="did" id="did" value="<%=s.getId()%>"/>
</form>
</td>
</tr>
<%}%>
</table>
<%out.println("</center>"); %>
<hr>
<%out.println("<center>"); %>
<marquee direction="left" width="60%" style="color:red; font-size:28px; background-color:#dbbeba;" ><%=action!=null?action:"Μπάρα Ενημέρωσης Αλλαγών"%></marquee>
</body>
</html>