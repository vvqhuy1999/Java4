package Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.UserDao;
import DAO.UserDaoImpl4;
import entity.user;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public LoginServlet() {
        super();
        userDao = new UserDaoImpl4();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user loggedInUser = userDao.validateUser(username, password);
        if (loggedInUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", loggedInUser.getId());
            session.setAttribute("username", loggedInUser.getEmail());
            session.setAttribute("isAdmin", loggedInUser.getAdmin());

            // Chuyển hướng đến trang phù hợp
            if (loggedInUser.getAdmin()) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            // Đăng nhập không thành công
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}