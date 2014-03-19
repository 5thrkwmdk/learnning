package com.namoosori.namooshop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.domain.Product;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/confirm.xhtml")
public class OrderCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 7404434613261067746L;

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
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		if (session.getAttribute("loginId") == null) {
			resp.sendRedirect("login.xhtml");
		} else {
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter writer = resp.getWriter();
			writer.println("");
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<meta charset='utf-8'>");
			writer.println("<title>주문화면</title>");
			writer.println("<link href='OrderList.css' rel='stylesheet' type='text/css' />");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<form action='complete.xhtml' method='post'>");
			writer.println("<h1>주문 상품</h1>");
			writer.println("<div id='table1'>");
			writer.println("<table>");
			writer.println("<thead>");
			writer.println("<tr>");
			writer.println("<th>상품번호</th>");
			writer.println("<th>상품명</th>");
			writer.println("<th>가격</th>");
			writer.println("</tr>");
			writer.println("</thead>");
			writer.println("<tbody>");

			ProductService service = NamooShopServiceFactory.getInstance()
					.getProductService();
			String[] nums = req.getParameterValues("num");
			for (String num : nums) {
				//
				Product findProducts = service.getProduct(Integer.parseInt(num));

				writer.println("<tr>");
				writer.println("<th>" + findProducts.getSerialNo() + "</th>");
				writer.println("<td>" + findProducts.getName() + "</td>");
				writer.println("<td>" + findProducts.getPrice() + "</td>");
				writer.println("</tr>");
			}

			writer.println("</tbody>");
			writer.println("</table>");
			writer.println("</div>");
			writer.println("<h1>주문정보 입력</h1>");
			writer.println("<div id='table2'>");
			writer.println("<table>");
			writer.println("<tr>");
			writer.println("<td>결제방법</td>");
			writer.println("<td>" + req.getParameter("a")+ "</td>");
			writer.println("</tr>");
			writer.println("<tr>");
			writer.println("<th>배송지주소</th>");
			writer.println("<td>" + req.getParameter("address") + "</td>");
			writer.println("</tr>");
			writer.println("</table>");
			writer.println("</div>");
			writer.println("<input type='submit' />");
			writer.println("</form>");
			writer.println("</body>");
			writer.println("</html>");
		}
	}
}
