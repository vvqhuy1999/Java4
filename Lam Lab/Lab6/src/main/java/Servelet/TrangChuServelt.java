package Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/account/sign-up", 
	"/account/change-password", 
	"/account/edit-profile", "/home"})
public class TrangChuServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public TrangChuServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/page.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý đăng ký, thay đổi mật khẩu, chỉnh sửa hồ sơ tại đây
        request.getRequestDispatcher("/views/page.jsp").forward(request, response);
    }
}



