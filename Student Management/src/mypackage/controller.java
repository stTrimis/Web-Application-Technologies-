/*Τρίμης Στυλιανός-Αθανάσιος 1564*/

package mypackage;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/handler")

public class controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	public controller() {
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String save_btn = request.getParameter("submit");
		String edit_btn = request.getParameter("edit");
		String delete_btn = request.getParameter("delete");
		String name = request.getParameter("fname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("mail");
		String id = request.getParameter("id");
		int se;
		try {
			se = Integer.parseInt(request.getParameter("semester"));
		} catch(NumberFormatException ne) {
			se=-1;
		}
		if(save_btn != null && save_btn.equals("SAVE")) {
			String message = "";
			if(se == -1) {
				message = "Unknown Semester Type:" + request.getParameter("semester");
			}
			else {
				student newStudent = new student(id,name,lname,se,email);
				message = "Last Input "+newStudent.to_str();
				database.getInstance().insert(id, name, lname, se, email);
			}
			HttpSession session = request.getSession();
			request.setAttribute("action", message);
			request.setAttribute("buttonname", "SAVE");
			request.setAttribute("reaonly", false);
			RequestDispatcher rd = request.getRequestDispatcher("table.jsp");
			rd.forward(request, response);
		}
		else if(save_btn != null && save_btn.equals("ΕΝΗΜΕΡΩΣΗ")){
			String message;
			request.setAttribute("buttonname", "SAVE");
			if(se == -1) {
				message = "Unknown Semester Type:" +request.getParameter("Semester");
				RequestDispatcher rd = request.getRequestDispatcher("central.jsp");
				rd.forward(request, response);
			}
			else {
				message= "Last Updated Input: " +database.getInstance().update(id, name, lname, se, email);
				request.setAttribute("action", message);
				request.setAttribute("readonly", true);
				RequestDispatcher rd = request.getRequestDispatcher("table.jsp");
				rd.forward(request, response);
			}
		}
		else if(edit_btn != null) {
			String edit_id =request.getParameter("eid");
			student edit_student = database.getInstance().search(edit_id);
			request.setAttribute("editaction", edit_student);
			request.setAttribute("buttonname", "ΕΝΗΜΕΡΩΣΗ");
			request.setAttribute("readonly", true);
			RequestDispatcher rd = request.getRequestDispatcher("table.jsp");
			rd.forward(request, response);
		}
		else if(delete_btn != null) {
			String delid = request.getParameter("did");
			String message = "DELETE STUDENT: "+database.getInstance().delete(delid);
			request.setAttribute("readonly", false);
			request.setAttribute("action", message);
			RequestDispatcher rd = request.getRequestDispatcher("table.jsp");
			rd.forward(request, response);
		}
		else {
			System.out.println("Error on post method");
		}
	}

}
