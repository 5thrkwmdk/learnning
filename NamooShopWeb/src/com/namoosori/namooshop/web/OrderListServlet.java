package com.namoosori.namooshop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.domain.Customer;
import com.namoosori.namooshop.domain.Product;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/orderList.xhtml")
public class OrderListServlet extends HttpServlet {

	private static final long serialVersionUID = 1458301677091720664L;

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
		String[] nums = req.getParameterValues("orders");

		Customer customer = (Customer) req.getSession().getAttribute("loginId");
		if (customer == null) {
			resp.sendRedirect("login.xhtml");
		} else {
			resp.setContentType("text/html; charset=utf-8");

			PrintWriter writer = resp.getWriter();
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<meta charset='utf-8'>");
			writer.println("<title>주문화면</title>");
			writer.println("<link href='orderList.css' rel='stylesheet' type='text/css' />");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<div id='orderList'>");
			writer.println("<form action='confirm.xhtml' method='post'>");
			writer.println("[" + customer.getName() + "] 님이 로그인 하셨습니다.");
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

			ProductService Proservice = NamooShopServiceFactory.getInstance()
					.getProductService();
			for (String num : nums) {
				Product findProduct = Proservice.getProduct(Integer
						.parseInt(num));
				writer.println("<tr>");
				writer.println("<td>" + findProduct.getSerialNo()
						+ "<input type='hidden' name='number' value='"
						+ findProduct.getSerialNo() + "'/></td>");
				writer.println("<td>" + findProduct.getName() + "</td>");
				writer.println("<td>" + findProduct.getPrice() + "</td>");
				writer.println("</tr>");
			}

			writer.println("</tbody>");
			writer.println("</table>");
			writer.println("</div>");
			writer.println("<h1>주문정보 입력</h1>");
			writer.println("<div id='table2'>");
			writer.println("<table>");
			writer.println("<tr>");
			writer.println("<th>결제방법</th>");
			writer.println("<td><input type='radio' name='a' value='신용카드'>신용카드"
					+ " <input type='radio' name='a' value='실시간이체'>실시간이체</td>");
			writer.println("</tr>");
			writer.println("<tr>");
			writer.println("<th>배송지주소</th>");
			writer.println("<td><input type='text' name='address'/></td>");
			writer.println("</tr>");
			writer.println("</table>");
			writer.println("</div>");
			writer.println("<input type='submit' value='주문신청' />");
			writer.println("</form>");
			writer.println("</div>");
			writer.println("</body>");
			writer.println("</html>");
		}
	}
}
