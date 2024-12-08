package Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DAO.UserDao;
import DAO.UserDaoImpl4;
import entity.user;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({"/admin/video",
	"/admin/user", 
	"/admin/like", 
	"/admin/share"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public UserServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/page.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý quản lý video, người dùng, like và share tại đây
        request.getRequestDispatcher("/views/page.jsp").forward(request, response);
    }
}
