package Servlet;

import Daos.UserDAO;
import Entity.User;
import Impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/Lab5","/Lab5/login", "/Lab5/test","/Lab5/admin/test"})
public class Lab5 extends HttpServlet{
    public Lab5(){
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO u = new UserDAOImpl();

        String path = request.getServletPath();

        if (path.contains("login")){
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            User user = u.findByID(id);
            if(user == null){
                request.setAttribute("message","Sai Username");
            }else {
                if(!user.getPassword().equals(password)){
                    request.setAttribute("message","dang nhap k thanh cong");
                }else {
                    request.setAttribute("user",user);
                    request.setAttribute("message","dang nhap thanh cong");
                    request.getSession().setAttribute("user",user);
//                    request.getRequestDispatcher("/views/testsession.jsp").forward(request, response);
                }
            }
        }

        request.getRequestDispatcher("/views/Lab5.jsp").forward(request, response);
    }
}
