package Filter;


import Daos.LogDAO;
import Daos.UserDAO;
import Entity.Log;
import Entity.User;
import Impl.LogDAOImpl;
import Impl.UserDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter(filterName = "AppFilter", urlPatterns = {"/Lab5/admin/test", "/Lab5/test"})
public class AppFilter extends HttpFilter implements Filter {
    private LogDAO ld;
    private UserDAO ud;

    @Override
    public void init(FilterConfig config) throws ServletException {
//        super.init(config);
        // Khởi tạo DAO trong init thay vì trong doFilter
        ld = new LogDAOImpl();
        ud = new UserDAOImpl();
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ld = new LogDAOImpl();
        ud = new UserDAOImpl();
        try {
//            System.out.println("test APPPPPPPPPP");
            // 1. Thiết lập UTF-8 cho request và response
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            // 2. Kiểm tra session và ghi log
//            User u = (User) request.getSession().getAttribute("user");

            // Lấy username từ session nếu đã đăng nhập
            HttpSession session = request.getSession(false); // Get session without creating a new one
            String userid = null;

            if (session != null) {
                User user = (User) session.getAttribute("user");
                System.out.println(user.getFullname());
                if (user != null) {
                    userid = user.getId();
                }
            }

            // Nếu đã đăng nhập thì ghi log
            if (userid != null) {
                String uri = request.getRequestURI();
                LocalDateTime now = LocalDateTime.now();
                String dateString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                Log log = new Log(uri, dateString, userid);
                System.out.println(log.getUsername());
                ld.create(log);
            }

            // Tiếp tục chuỗi filter
            chain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            // Đóng EntityManager và giải phóng tài nguyên
            if (ld instanceof LogDAOImpl) {
                ((LogDAOImpl) ld).closeEntityManager();
            }
            if (ud instanceof UserDAOImpl) {
                ((UserDAOImpl) ud).closeEntityManager();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
