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
@WebServlet({"/userManager/edit/*", "/userManager/create", "/userManager/update",
    "/userManager/delete", "/userManager/reset", "/userManager/page", "/userManager/search" })
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        User form = new User();
//        User form2 = new User(LEGACY_DO_HEAD, LEGACY_DO_HEAD, LEGACY_DO_HEAD, LEGACY_DO_HEAD, null, null, null);

        int currentPage = 1;

        // crud user
        try {
            BeanUtils.populate(form, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        UserDAOImpl dao =new UserDAOImpl(); 

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String adminStr = request.getParameter("admin");
        boolean isAdmin = "1".equals(adminStr);


        String path = request.getServletPath();
        List<User> list = dao.showpage(currentPage);
        if (path.contains("edit")) {
            String idedit = request.getPathInfo().substring(1);
            form = dao.findById(idedit);
        } else if (path.contains("create")) {
            form = new User(id, password, fullname, email, isAdmin, null, null);
            dao.save(form);
            form = new User();
            request.setAttribute("message", "Tao nguoi dung thanh cong!");
        } else if (path.contains("update")) {
            form = new User(id, password, fullname, email, isAdmin, null, null);
            dao.update(form);
            request.setAttribute("message", "Cap nhat nguoi dung thanh cong!");
        } else if (path.contains("delete")) {
            dao.deleteById(id);
            form = new User();
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
        }else if (path.contains("search")){
            // xử lý search
            String searchname = request.getParameter("fullname");
            String searchadmin = request.getParameter("admin");
            isAdmin = "1".equals(searchadmin);
            list = dao.search(searchname,isAdmin);
        }
        else {
            form = new User();
            
        }
        
        Long so = dao.countUser();
        int sopage = (so.intValue() % 5 == 0) ? (so.intValue() / 5) : (so.intValue() / 5 + 1);
        request.setAttribute("item", form);
        request.setAttribute("sopage", sopage);
        request.setAttribute("list", list);
        request.setAttribute("namepage", "USER MANAGER");
        request.setAttribute("page", "/admin/UserManager.jsp");
        request.getRequestDispatcher("/views/adminLayout.jsp").forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
