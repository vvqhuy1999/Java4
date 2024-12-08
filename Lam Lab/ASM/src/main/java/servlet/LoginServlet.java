package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Daos.*;
import Entity.*;
import Impl.UserDAOImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String id = request.getParameter("id");
		String password = request.getParameter("password");
		User user = userDAO.findById(id);

		if (user == null) {
			request.setAttribute("error", "Sai username!");
            request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
            request.setAttribute("page", "/user/Login.jsp");
		} else if (!user.getPassword().equals(password)) {
			request.setAttribute("error", "Sai password!");
            request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
            request.setAttribute("page", "/user/Login.jsp");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // Lưu thông tin user vào session
			session.setAttribute("isAdmin", user.getAdmin());

			// Chuyển hướng đến trang phù hợp
			if (user.getAdmin()) {
				response.sendRedirect("/views/adminLayout.jsp");
			} else {
				response.sendRedirect("/views/userLayout.jsp");
			}
		}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra nếu người dùng đã đăng nhập (có session)
        HttpSession session = req.getSession(false); // false để không tạo session mới nếu chưa có
        if (session != null && session.getAttribute("user") != null) {
            // Nếu người dùng đã đăng nhập, chuyển hướng đến trang chủ (index.jsp)
			 resp.sendRedirect("/views/userLayout.jsp"); 
//            resp.sendRedirect("index.jsp");
        } else {
            // Nếu chưa đăng nhập, hiển thị trang login.jsp
            req.getRequestDispatcher("/views/userLayout.jsp").forward(req, resp);
            req.setAttribute("page", "/user/Login.jsp");
        }
    }
}
