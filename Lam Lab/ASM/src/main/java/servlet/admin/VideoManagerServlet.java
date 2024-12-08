package servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import Impl.*;
import Entity.*;

/**
 * Servlet implementation class VideoManagerServlet
 */
@WebServlet({"/videoManager/edit/*", "/videoManager/create", "/videoManager/update",
    "/videoManager/delete", "/videoManager/reset", "/videoManager/page" })
public class VideoManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Video form = new Video();
//        User form2 = new User(LEGACY_DO_HEAD, LEGACY_DO_HEAD, LEGACY_DO_HEAD, LEGACY_DO_HEAD, null, null, null);

        int currentPage = 1;

        // crud user
        try {
            BeanUtils.populate(form, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        VideoDAOImpl dao =new VideoDAOImpl(); 

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String poster = request.getParameter("poster");
        String link = request.getParameter("link");
        String description = request.getParameter("description");
        String activeStr = request.getParameter("active");
        boolean isActive = "1".equals(activeStr);

        System.out.println(request.getParameter("page"));
        String path = request.getServletPath();
        List<Video> list = dao.showpage(currentPage);
        if (path.contains("edit")) {
            String idedit = request.getPathInfo().substring(1);
            form = dao.findById(idedit);
        } else if (path.contains("create")) {
        	int views = Integer.valueOf(request.getParameter("views"));
            form = new Video(id, title, poster, link,  description, isActive, views, null, null);
            dao.save(form);
            form = new Video();
            request.setAttribute("message", "Tao nguoi dung thanh cong!");
        } else if (path.contains("update")) {
        	int views = Integer.valueOf(request.getParameter("views"));
        	form = new Video(id, title, poster, link,  description, isActive, views, null, null);
            dao.update(form);
            request.setAttribute("message", "Cap nhat nguoi dung thanh cong!");
        } else if (path.contains("delete")) {
        	int views = Integer.valueOf(request.getParameter("views"));
        	form = new Video(id, title, poster, link,  description, isActive, views, null, null);
        	dao.delete(form);
            form = new Video();
            request.setAttribute("message", "Xoa thanh cong!");
        }
        else if (path.contains("page")){
            // xử lý page
            String pageParam = request.getParameter("page");
            
//            request.setAttribute("pageParam",pageParam);
            if (pageParam != null && !pageParam.isEmpty()) {
                try {
                    currentPage = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    currentPage = 1;
                }
            }
            list = dao.showpage(currentPage);
        }
        else {
            form = new Video();
            
        }
        
        Long so = dao.countVideo();
        int sopage = (so.intValue() % 5 == 0) ? (so.intValue() / 5) : (so.intValue() / 5 + 1);
        request.setAttribute("item", form);
        request.setAttribute("sopage", sopage);
        request.setAttribute("list", list);
        request.setAttribute("namepage", "VIDEO MANAGER");
        request.setAttribute("page", "/admin/VideoManager.jsp");
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
