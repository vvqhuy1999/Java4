package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Daos.*;
import Impl.*;
import Entity.*;

/**
 * Servlet implementation class AdminLayout
 */
@WebServlet({"/admin/home", "/admin/videoManager", "/admin/userManager", "/admin/report", "/admin/report/search" })
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
			request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
//			request.setAttribute("namepage", "Home");
		} else if (uri.contains("videoManager")) {
			// thực hiện truy xuất dữ liệu
			VideoDAOImpl VideoManagerdao =new VideoDAOImpl();
			Video form = new Video();
			int currentPage = 1;
			List<Video> list = VideoManagerdao.showpage(currentPage);
			Long so = VideoManagerdao.countVideo();
			int sopage = (so.intValue() % 5 == 0) ? (so.intValue() / 5) : (so.intValue() / 5 + 1);
			request.setAttribute("item", form);
			request.setAttribute("sopage", sopage);
			request.setAttribute("list", list);
			request.setAttribute("page", "/admin/VideoManager.jsp");
			request.setAttribute("namepage", "VIDEO MANAGER");
		} else if (uri.contains("userManager")) {
			// thực hiện truy xuất dữ liệu
			UserDAOImpl UserManagerdao =new UserDAOImpl();
			User form = new User();
			int currentPage = 1;
			List<User> list = UserManagerdao.showpage(currentPage);
			Long so = UserManagerdao.countUser();
			int sopage = (so.intValue() % 5 == 0) ? (so.intValue() / 5) : (so.intValue() / 5 + 1);
			request.setAttribute("item", form);
			request.setAttribute("sopage", sopage);
			request.setAttribute("list", list);
			request.setAttribute("page", "/admin/UserManager.jsp");
			request.setAttribute("namepage", "USER MANAGER");
		} else if (uri.contains("report")) {
			// thực hiện truy xuất dữ liệu
			FavoriteDAOImpl Reportdao = new FavoriteDAOImpl();
			VideoDAOImpl Videodao = new VideoDAOImpl();

			List<Object[]> titleDates = Reportdao.getFavoritedVideos();
			List<String> titleVideos = Videodao.getTitleVideos();

			request.setAttribute("videoTitles", titleVideos);
			request.setAttribute("titleDates",titleDates);
			request.setAttribute("page", "/admin/Report.jsp");
			request.setAttribute("namepage", "REPORTS");
		}
//		else if (uri.contains("search")) {
//			FavoriteDAOImpl Reportdao = new FavoriteDAOImpl();
//			// xử lý search
//			String searchtitle = request.getParameter("title");
//			System.out.println(searchtitle);
//			List<Favorite> favorites = Reportdao.getFavoritesByVideoTitle(searchtitle);
//			request.setAttribute("favoriteUsers", favorites);
//
//	        VideoDAOImpl Videodao = new VideoDAOImpl();
//
//			List<Object[]> titleDates = Reportdao.getFavoritedVideos();
//	        List<String> titleVideos = Videodao.getTitleVideos();
//
//	        request.setAttribute("videoTitles", titleVideos);
//	        request.setAttribute("titleDates",titleDates);
//			request.setAttribute("page", "/admin/Report.jsp");
//			request.setAttribute("namepage", "REPORTS");
//
//		}
		else {
			System.out.println(uri);
		}
		// Chuyển hướng về trang admin layout
		request.getRequestDispatcher("/views/adminLayout.jsp").forward(request, response);
	}
}
