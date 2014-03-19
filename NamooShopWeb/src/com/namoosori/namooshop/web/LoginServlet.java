package com.namoosori.namooshop.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.service.facade.CustomerService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

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
		// 요청파라미터
		String paramLoginId = req.getParameter("loginId");
		String paramPassword = req.getParameter("password");

		HttpSession session = req.getSession();

		CustomerService service = NamooShopServiceFactory.getInstance()
				.getCustomerService();
		boolean login = service.login(paramLoginId, paramPassword);

		if (login) {
			session.setAttribute("loginId", service.getCustomer(paramLoginId));
			resp.sendRedirect("main.xhtml");
		} else {
			// 로그인 실패
			session.removeAttribute("loginId");
			resp.sendRedirect("login.xhtml");
		}
	}

}