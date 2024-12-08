package Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class VideoSevrelet
 */
@WebServlet({"/video/list", 
	"/video/detail/*", 
	"/video/like/*", 
	"/video/share/*"})
public class VideoSevrelet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public VideoSevrelet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/page.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý like, share video tại đây
        request.getRequestDispatcher("/views/page.jsp").forward(request, response);
    }
}
