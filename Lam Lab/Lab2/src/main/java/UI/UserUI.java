package UI;

import Daos.UserDAO;
import Daos.UserDaoImpl;
import Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

//@WebServlet(name = "UserUI", value = "/UserUI")
@WebServlet({ "/UserUI", "/UserUI/edit/*", "/UserUI/create", "/UserUI/update",
        "/UserUI/delete", "/UserUI/reset", "/UserUI/page" })
public class UserUI extends HttpServlet {
    public UserUI(){
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        User form = new User();

        int currentPage = 1;

        // crud user
        try {
            BeanUtils.populate(form, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        UserDaoImpl dao = new UserDaoImpl();

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
            form = dao.findByID(idedit);
        } else if (path.contains("create")) {
            form = new User(id, password, fullname, email, isAdmin);
            dao.create(form);
            form = new User();
            request.setAttribute("message", "Tạo người dùng thành công!");
        } else if (path.contains("update")) {
            form = new User(id, password, fullname, email, isAdmin);
            dao.update(form);
            request.setAttribute("message", "Cập nhật người dùng thành công!");
        } else if (path.contains("delete")) {
            dao.deleteById(id);
            form = new User();
            request.setAttribute("message", "Xóa thành công!");
        }
        else if (path.contains("page")){
            // xử lý page
            String pageParam = request.getParameter("page");
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
            form = new User();
        }
        request.setAttribute("item", form);

//        System.out.println("Number of users: " + list.size());
        Long so = dao.countUser();

        int sopage = (so.intValue() / 5 == 0) ? (so.intValue() / 5) : (so.intValue() / 5 + 1);

        request.setAttribute("sopage", sopage);
        request.setAttribute("list", list);
//        request.setAttribute("view","index.jsp");
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }

}