package servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Impl.*;
import Entity.*;
/**
 * Servlet implementation class Report
 */
@WebServlet("/report/search")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	FavoriteDAOImpl Reportdao = new FavoriteDAOImpl();	
		String path = request.getServletPath();
		if (path.contains("search")) {
			// xử lý search
			String searchtitle = request.getParameter("title");
//			System.out.println(searchtitle);
			
			// Hiển thị bảng Favourite Users
			List<Favorite> favorites = Reportdao.getFavoritesByVideoTitle(searchtitle);
			request.setAttribute("favoriteUsers", favorites);
			// Hiển thị bảng Shared Friends
			ShareDAOImpl Sharedao = new ShareDAOImpl();
			List<Share> shares = Sharedao.getSharesByVideoTitle(searchtitle);
			request.setAttribute("sharedFriends", shares);
			
			//Hiển thị list title
			VideoDAOImpl Videodao = new VideoDAOImpl();
	        List<String> titleVideos = Videodao.getTitleVideos();
	        request.setAttribute("videoTitles", titleVideos);
	        
	        // Hiển thị bảng Favorites
			List<Object[]> titleDates = Reportdao.getFavoritedVideos();
	        request.setAttribute("titleDates",titleDates);

		}
		request.setAttribute("page", "/admin/Report.jsp");
		request.setAttribute("namepage", "REPORTS");
		// Chuyển hướng về trang servlet.admin layout
		request.getRequestDispatcher("/views/adminLayout.jsp").forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
