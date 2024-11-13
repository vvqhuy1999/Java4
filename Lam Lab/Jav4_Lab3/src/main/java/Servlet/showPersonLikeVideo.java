package Servlet;

import Daos.FavoriteDAO;
import Daos.UserDAO;
import Entity.Favorite;
import Entity.User;
import Entity.Video;
import Impl.FavoriteDAOImpl;
import Impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//@WebServlet(name = "showPersonLikeVideo", value = "/showPersonLikeVideo")
@WebServlet({"/showPersonLikeVideo","/showPersonLikeVideo/search"})
public class showPersonLikeVideo extends HttpServlet {
    public showPersonLikeVideo(){
        super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO u = new UserDAOImpl();
        FavoriteDAO f = new FavoriteDAOImpl();
        String path = request.getServletPath();
        if (path.contains("search")){
            // xử lý search
            String searchid = request.getParameter("id");
//            request.setAttribute("searchid",searchid);
            User user = u.findByID(searchid);
            // lay  thich video
            List<Favorite> videos = user.getFavorites();
//            f.getVideo().getTitle();
            request.setAttribute("user",user);
            request.setAttribute("videos",videos);

        }else {
            List<Favorite> favorites = f.findAll();
            request.setAttribute("favorites",favorites);

        }
        request.getRequestDispatcher("/views/bai3.jsp").forward(request, response);
    }
}
