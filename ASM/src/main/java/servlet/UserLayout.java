package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserLayout
 */
@WebServlet({"/user/index","/user/home","/user/myFavorites","/user/login","/user/registration"})
public class UserLayout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLayout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("home")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/user/Home.jsp");
		} else if (uri.contains("myFavorites")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/user/Favorites.jsp");
		} else if (uri.contains("login")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/user/Login.jsp");
		} else if (uri.contains("registration")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/user/Registration.jsp");
		}else {
			System.out.println(uri);
		}
		// Chuyển hướng về trang admin layout
		request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
	}

}
