package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Impl.*;
import Entity.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * Servlet implementation class UserLayout
 */
@WebServlet({ "/user/home", "/user/myFavorites", "/user/login", "/user/registration" , "/user/detail/*", "/user/logoff"})
public class UserLayout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLayout() {
		super();
		// TODO Auto-generated constructor stub
	}

	private UserDAOImpl userDao = new UserDAOImpl();
	private VideoDAOImpl videoDao = new VideoDAOImpl();
	private  ShareDAOImpl shareDao = new ShareDAOImpl();
	private  FavoriteDAOImpl favoriteDAO = new FavoriteDAOImpl();

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
			List<Video> videos = videoDao.findAll();
			request.setAttribute("videos", videos);
			request.setAttribute("page", "/user/Home.jsp");
		}else if (uri.contains("detail")){
			// lay Id video
			String idvideo = request.getPathInfo().substring(1);
//			System.out.println(idvideo);
			Video OneVideo = videoDao.findOneVideo(idvideo);

			int views = videoDao.increaseViews(idvideo);
			request.setAttribute("OneVideo", OneVideo);
			request.setAttribute("views", views);
			String action = request.getParameter("action");
			String email = request.getParameter("email");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			if (email != null) {
				// Xử lý logic share
				try {
					Date ngayhomnay = new Date();
//					String userId = user.getId();
					Share share = new Share(user,OneVideo,email,ngayhomnay);
					shareDao.save(share);

					request.setAttribute("message", "Shared successfully to: " + email);
				} catch (Exception e) {
					request.setAttribute("error", "Failed to share: " + e.getMessage());
				}
			}
			if ("like".equals(action)){
				try {
					boolean alreadyLiked = favoriteDAO.checkUserLikedVideo(user.getId(),OneVideo.getId());

					if (alreadyLiked) {
						// Nếu đã like thì xóa like
						favoriteDAO.removeFavorite(user.getId(), idvideo);
						request.setAttribute("message", "xóa favorites");
						request.setAttribute("isLiked", false);
					} else {
						// Nếu chưa like thì thêm like mới
						Date ngayhomnay = new Date();
						Favorite favorite = new Favorite(OneVideo, user, ngayhomnay);
						favoriteDAO.addFavorite(favorite);
						request.setAttribute("message", "Favorites thành công");
						request.setAttribute("isLiked", true);
					}
				} catch (Exception e) {
					request.setAttribute("error", "Like thất bại : " + e.getMessage());
				}
			}
			request.setAttribute("page", "/user/Detail.jsp");


		} else if (uri.contains("myFavorites")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/user/Favorites.jsp");
		} else if (uri.contains("login")) {
			// thực hiện truy xuất dữ liệu
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				if(user.getAdmin()) {
					response.sendRedirect(request.getContextPath() + "/admin/home");
				} else {
					response.sendRedirect(request.getContextPath() + "/user/home");
				}
				return;
			}

			// Nếu chưa có session, xử lý login
			if ("POST".equalsIgnoreCase(request.getMethod())) {
				handleLogin(request, response);
				return;
			} else {
				request.setAttribute("page", "/user/Login.jsp");
				request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
			}
		} else if (uri.contains("registration")) {
			// thực hiện truy xuất dữ liệu
			request.setAttribute("page", "/user/Registration.jsp");
		}
		else if(uri.contains("logoff")){
			try {
				// Lấy session hiện tại
				HttpSession session = request.getSession();

				// Xóa tất cả các attribute trong session
				session.invalidate();

				// Chuyển hướng về trang chủ hoặc trang login
				request.setAttribute("page", "/user/Home.jsp");
				request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
			} catch (Exception e) {
				// Log lỗi
				e.getLocalizedMessage();
				request.setAttribute("page", "/user/Home.jsp");
				request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
			}
		}
		else {
			System.out.println(uri);
		}
		// Chuyển hướng về trang servlet.admin layout
		request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		User user = userDao.findById(id);
		if (user == null) {
			request.setAttribute("error", "Sai username!");
			request.setAttribute("page", "/user/Login.jsp");
//			request.setAttribute("page", "/user/Home.jsp");
			request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
		} else if (!user.getPassword().equals(password)) {
			request.setAttribute("error", "Sai password!");
			request.setAttribute("page", "/user/Login.jsp");
			request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("isAdmin", user.getAdmin());
			if (user.getAdmin()) {
//				response.sendRedirect(request.getContextPath() + "/views/adminLayout.jsp");
				response.sendRedirect(request.getContextPath() + "/admin/userManager");
			} else {
//				request.setAttribute("page", "/user/Home.jsp");
//				request.getRequestDispatcher("/views/userLayout.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath() + "/user/home");
			}
		}
	}
}
