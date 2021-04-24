package com.gadgetBadget.servlet;

import java.io.IOException;
import java.util.InputMismatchException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gadgetBadget.model.Researcher;
import com.gadgetBadget.service.IResearcher;
import com.gadgetBadget.service.ImpReasercher; 

/**
 * Servlet implementation class addResearcher
 */ 
public class addResearcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addResearcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isTrue; 
		IResearcher service= new ImpReasercher(); 		
		Researcher researcher = new Researcher();
		
		 
		int tp = Integer.parseInt(request.getParameter("tel"));
		float payment = Float.parseFloat(request.getParameter("payment"));
		 
		 
		researcher.setName(request.getParameter("name"));
		researcher.setContact(tp); 
		researcher.setProjectType(request.getParameter("project"));   
		researcher.setPayment( payment);  
		
		 
		
		 
		try {
			
			isTrue = service.addResearcher(researcher);
			
			//if return value is true
			if(isTrue == true) {

				RequestDispatcher dispatcher = request.getRequestDispatcher("researchers.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("Addresearchers.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		catch ( InputMismatchException  e) {
			e.printStackTrace();
		}
	}

}
