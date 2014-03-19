package com.namoosori.namooshop.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoosori.namooshop.domain.Product;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/main.xhtml")
public class BookListServlet extends HttpServlet {

	private static final long serialVersionUID = -4297113391260453210L;

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
		resp.setContentType("text/html; charset=utf-8");

		PrintWriter writer = resp.getWriter();

		ProductService service = NamooShopServiceFactory.getInstance()
				.getProductService();
		List<Product> productsList = service.getAllProducts();


		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>상품목록</title>");
		writer.println("<link href='bookList.css' rel='stylesheet' type ='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<div id='productlist'>");
		writer.println("<form action='order.do' method='post'>");
		writer.println("<h1>상품 목록</h1>");
		writer.println("<table>");
		writer.println("<thead>");
		writer.println("<tr>");
		writer.println("<th></th>");
		writer.println("<th>상품번호</th>");
		writer.println("<th>상품명</th>");
		writer.println("<th>가격</th>");
		writer.println("<th>평점</th>");
		writer.println("</tr>");
		writer.println("</thead>");
		writer.println("<tbody>");
		writer.println("<tr>");

		for (Product product : productsList) {
			writer.println("<tr>");
			writer.println("<td><input type='checkbox' name='orders' value='"
					+ product.getSerialNo() + "' /></td>");
			writer.println("<th>" + product.getSerialNo() + "</th>");
			writer.println("<td>" + product.getName() + "</td>");
			writer.println("<td>" + product.getPrice() + "</td>");
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				if (i < product.getLike()) {
					builder.append("★");
				} else {
					builder.append("☆");
				}
			}
			writer.println("<td>" + builder.toString() + "</td>");
			writer.println("</tr>");
		}

		writer.println("</tbody>");
		writer.println("</table>");
		writer.println("<input type='submit' value='주문하기' name='order'/>");
		writer.println("</form>");
		writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
	}
}