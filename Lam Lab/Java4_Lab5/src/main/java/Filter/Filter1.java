package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter(filterName = "Filter1")
public class Filter1 extends HttpFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        super.doFilter(req, res, chain);
        req.setAttribute("hello","Toi la filter 1");

        System.out.println("Filter 1 dang chay");
        // Chuyển tiếp request
        chain.doFilter(req,res);
    }
}
