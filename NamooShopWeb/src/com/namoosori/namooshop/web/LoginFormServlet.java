package com.namoosori.namooshop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.xhtml")
public class LoginFormServlet extends HttpServlet {

	private static final long serialVersionUID = -6737978632833768209L;

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
		// 로그인이 된 경우 로그인 사용자 페이지로 바로 이동
		if (session.getAttribute("loginId") != null) {
			resp.sendRedirect("main.xhtml");
			return;
		} else {
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter writer = resp.getWriter();
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>로그인</title>");
			writer.println("<link href='login.css' rel='stylesheet' type='text/css' />");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<div id='loginform'>");
			writer.println("<form action=\"login.do\" method=\"post\">");
			writer.println("<table>");
			writer.println("<tr>");
			writer.println("<th>로그인 ID</th>");
			writer.println("<td><input type='text' name='loginId' /></td>");
			writer.println("</tr>");
			writer.println("<tr>");
			writer.println("<th>패스워드</th>");
			writer.println("<td><input type='password' name='password'/></td>");
			writer.println("</table>");
			writer.println("<input type='submit' value='로그인'/>");
			writer.println("</div>");
			writer.println("</form>");
			writer.println("</body>");
			writer.println("</html>");
		}
	}
}