package Filter;

import Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter({
        "/admin/*",
        "/user/detail/*",
//        "user/logoff"
})
public class AppFilter implements Filter{
    public  static  final String SECURITY_URI = "securityUri";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("FIlter running");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String uri = req.getRequestURI();
        String queryString = req.getQueryString();

        System.out.println("Current URI: " + uri); // Log URI
        System.out.println("Query String: " + queryString); // Log query string

        // Kiểm tra nếu là request detail và có query string
        if (uri.contains("/detail/") && queryString != null) {
            // Lấy videoId từ URI
            String videoId = uri.substring(uri.lastIndexOf("/") + 1);
            // Redirect về URL sạch
            res.sendRedirect(req.getContextPath() + "/user/detail/" + videoId);
            return;
        }

        if (uri.contains("/admin/") && !user.getAdmin()) {
            res.sendRedirect(req.getContextPath() + "/admin/home");
            return;
        }

        // Kiểm tra quyền truy cập
        if (user == null) {
            session.setAttribute(AppFilter.SECURITY_URI, uri);
            res.sendRedirect(req.getContextPath() + "/user/login");
            return;
        }

        chain.doFilter(request, response);
    }


    public void destroy() {
        // Dọn dẹp tài nguyên khi Filter bị hủy
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
}
