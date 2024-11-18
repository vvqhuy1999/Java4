package Servlet;

import Entity.User;
import Impl.FavoriteDAOImpl;
import Impl.ShareDAOImpl;
import Impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/Lab4","/Lab4/login", "/Lab4/bai3"})
public class Lab4 extends HttpServlet {
    public Lab4(){
        super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // goi ham
        ShareDAOImpl s = new ShareDAOImpl();
        UserDAOImpl u = new UserDAOImpl();
        FavoriteDAOImpl f = new FavoriteDAOImpl();

        // khai bao
        List<Object[]> findTitleKeyCountSLLink = null;
        // lay duogn dan
        String path = request.getServletPath();
        if (path.contains("login")){
            String idemail = request.getParameter("id");
            String password = request.getParameter("password");
            User user = u.findByIdOrEmail(idemail);
            if(!user.getPassword().equals(password)){
                request.setAttribute("message","dang nhap k thanh cong");
            }else {
                request.setAttribute("user",user);
                request.setAttribute("message","dang nhap thanh cong");
            }
        }else if(path.contains("bai3")){
            String key = request.getParameter("title");
//            request.setAttribute("key",key);
            findTitleKeyCountSLLink = f.FindTitleKeyCountSLLink(key);
        }
        // bai 1
        List<Object[]> titleDates = s.findtTitleDate();
        request.setAttribute("titleDates",titleDates);

        // bai 3
        request.setAttribute("findTitleKeyCountSLLink",findTitleKeyCountSLLink);

        request.getRequestDispatcher("/views/Lab4.jsp").forward(request, response);
    }
}
