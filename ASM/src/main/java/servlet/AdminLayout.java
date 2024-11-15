package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AdminLayout
 */
@WebServlet({"/admin/index", "/admin/home", "/admin/videoManager", "/admin/userManager", "/admin/report" })
public class AdminLayout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLayout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("home")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/user/Home.jsp");
		} else if (uri.contains("videoManager")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/admin/VideoManager.jsp");
		} else if (uri.contains("userManager")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/admin/UserManager.jsp");
		} else if (uri.contains("report")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/admin/Report.jsp");
		}else {
			System.out.println(uri);
		}
		// Chuyển hướng về trang admin layout
		request.getRequestDispatcher("/views/adminLayout.jsp").forward(request, response);
	}
}
