package AuthFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter({"/admin/*", 
	"/account/change-password", 
	"/account/edit-profile", 
	"/video/like/*", 
	"/video/share/*"})
public class AuthFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        // Khởi tạo bộ lọc
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        HttpSession session = httpRequest.getSession(false);

	        // Lấy thông tin người dùng từ session
	        String username = (String) session.getAttribute("username");
	        boolean isAdmin = (boolean) session.getAttribute("isAdmin");

	        // Kiểm tra quyền truy cập
	        if (username == null) {
	            // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
	            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
	            return;
	        }

	        if (httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/admin/") && !isAdmin) {
	            // Từ chối truy cập nếu không phải là admin
	            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập vào khu vực này.");
	            return;
	        }

	        // Cho phép truy cập nếu đã đăng nhập và có quyền
	        chain.doFilter(request, response);
	    }

	
	}
