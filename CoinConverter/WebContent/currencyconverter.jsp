<%-- Τρίμης Στυλιανός-Αθανάσιος 1564 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*,java.io.*" %>

<%!
String curParam;
double cur = 0.0;
boolean buttonPressed;
boolean badValues = false; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Μετατροπέας Νομισμάτων</title>
</head>
<body bgcolor="#84b5cc">
	<form action="">
		Ποσό: <input type="text" name="cur" value="">
		
		Από: <select name="coins_1">
			<option value="eur_1">Ευρώ</option>
			<option value="usd_1">Δολάριο Ήπα</option>
			<option value="gbp_1">Λίρα Αγγλίας</option>
		</select>
		
		Σε: <select  name="coins_2">
			<option value="eur_2">Ευρώ</option>
			<option value="usd_2">Δολάριο Ήπα</option>
			<option value="gbp_2">Λίρα Αγγλίας</option>
		</select>
		<input type="submit" name="showConvertion" value="Μετατροπή"><br>
	</form>

<%
	buttonPressed = (request.getParameter("showConvertion") != null);
	curParam = request.getParameter("cur");
	String s1= request.getParameter("coins_1");
	String s2 = request.getParameter("coins_2");
	if (buttonPressed) {
		try {
			cur = Integer.parseInt(curParam);
		}catch(NumberFormatException e) {
			badValues = true;
		}
	    
	    if (badValues) {
	    
	    out.println("<strong style=\"color:red\">Λάθος. Δώστε Κανονικό Ποσό για Μετατροπή</strong>");
	
	    } else {
	    	
	    	if(s1.equals("eur_1") && s2.equals("eur_2")) {
				cur*=1;
			}
			if(s1.equals("eur_1") && s2.equals("usd_2")) {
				cur*=1.15;		
			}
			if(s1.equals("eur_1") && s2.equals("gbp_2")) {
				cur*=0.85;
			}
			if(s1.equals("usd_1") && s2.equals("eur_2")) {
				cur*=0.87;
			}
			if(s1.equals("usd_1") && s2.equals("usd_2")) {
				cur*=1;
			}
			if(s1.equals("usd_1") && s2.equals("gbp_2")) {
				cur*=0.75;
			}
			if(s1.equals("gbp_1") && s2.equals("eur_2")) {
				cur*=1.17;
			}
			if(s1.equals("gbp_1") && s2.equals("usd_2")) {
				cur*=1.34;
			}
			if(s1.equals("gbp_1") && s2.equals("gbp_2")) {
				cur*=1;
			}
			out.println(cur/100);
	    }
	  }	
%>

</body>
</html>