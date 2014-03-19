package com.namoosori.namooshop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/complete.xhtml")
public class CompleteServlet extends HttpServlet {

	private static final long serialVersionUID = -6255426892927710640L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		HttpSession session = req.getSession();
		if (session.getAttribute("loginId") == null) {
			resp.sendRedirect("login.xhtml");
		} else {
			if (req.getSession().getAttribute("loginId") == null) {
				resp.sendRedirect("login.xhtml");
			} else {
				resp.setContentType("text/html; charset=utf-8");

				PrintWriter writer = resp.getWriter();
				writer.println("<html>");
				writer.println("<head>");
				writer.println("<title>주문 완료</title>");
				writer.println("<link href='complete.css' rel='stylesheet' type='text/css' />");
				writer.println("</head>");
				writer.println("<body>");
				writer.println("<div id='complete'");
				writer.println("<form action='main.xhtml'>");
				writer.println("<h1>상품 주문이 완료되었습니다.</h1>");
				writer.println("<input type='submit' value='확인'>");
				writer.println("</form>");
				writer.println("</div>");
				writer.println("</body>");
				writer.println("</html>");
			}
		}
	}
}