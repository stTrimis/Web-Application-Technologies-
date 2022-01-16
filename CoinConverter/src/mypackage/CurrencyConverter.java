/*Τρίμης Στυλιανός-Αθανάσιος 1564*/


package mypackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet ("/currencyconverter")

public class CurrencyConverter extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response) throws 
			ServletException, IOException{
		
		String curParam;
		double cur = 0.00;
		boolean buttonPressed;
		boolean badValues = false;
		
		buttonPressed = (request.getParameter("showConvertion") != null);
		curParam = request.getParameter("cur");
		
		if(buttonPressed) {
			try {
				cur = Double.parseDouble(curParam);
			}catch(NumberFormatException e) {
				badValues = true;
			}
		}
		
		response.setContentType( "text/html; charset=utf-8" );
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>\n" + 
	              "<html>\n" + 
	              "<head><title>Μετατροπέας Νομισμάτων</title></head>\n" + 
	              "<body bgcolor=\"#84b5cc\">\n" + 
	              "<h1>Μετατροπή Ποσών Σε Διαφορετικά Νομίσματα</h1>\n");
				
		out.println(
			    "<form>\n" + 
					    "Ποσό: <input type=\"text\" name=\"cur\" value=\"" + (curParam == null? "" : curParam)+ 
					    "\" />\n"+ 
					    "Από: <select name=\"coins_1\">"+ 
					    "<option value=\"eur_1\">Ευρώ</option>"+
					    "<option value=\"usd_1\">Δολάριο ΗΠΑ</option>"+
					    "<option value=\"gbp_1\">Λίρα Αγγλίας</option>"+
					    "\" </select>\n" +
					    "Σε: <select name=\"coins_2\">"+
					    "<option value=\"eur_2\">Ευρώ</option>"+
					    "<option value=\"usd_2\">Δολάριο ΗΠΑ</option>"+
					    "<option value=\"gbp_2\">Λίρα Αγγλίας</option>"+
					    "\" </select>\n"+
					    "<input type=\"submit\" name=\"showConvertion\" value=\"Μετατροπή\" /><br>\n" + 
			    "</form>"
			  );
		
		String s1 = request.getParameter("coins_1");
		String s2 = request.getParameter("coins_2");
		
		if(buttonPressed) {
			if(badValues) {
				out.println("<strong style=\"color:red\">Λάθος. Δώστε Κανονικό Ποσό για Μετατροπή</strong>");
			}
			else {
				if(s1.equals("eur_1") && s2.equals("eur_2")) {
					cur*=1*100;
				}
				if(s1.equals("eur_1") && s2.equals("usd_2")) {
					cur*=1.15*100;					
				}
				if(s1.equals("eur_1") && s2.equals("gbp_2")) {
					cur*=0.85*100;
				}
				if(s1.equals("usd_1") && s2.equals("eur_2")) {
					cur*=0.87*100;
				}
				if(s1.equals("usd_1") && s2.equals("usd_2")) {
					cur*=1*100;
				}
				if(s1.equals("usd_1") && s2.equals("gbp_2")) {
					cur*=0.75*100;
				}
				if(s1.equals("gbp_1") && s2.equals("eur_2")) {
					cur*=1.17*100;
				}
				if(s1.equals("gbp_1") && s2.equals("usd_2")) {
					cur*=1.34*100;
				}
				if(s1.equals("gbp_1") && s2.equals("gbp_2")) {
					cur*=1*100;
				}
				out.println(cur/100);
							
			}
		}
		out.println("</body></html>");
	}
}
