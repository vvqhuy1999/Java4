package Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter(filterName = "Filter2")
public class Filter2 extends HttpFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        super.doFilter(req, res, chain);
        // In ra giá trị attribute từ Filter1
        System.out.println(req.getAttribute("hello"));

        // In thông báo để debug
        System.out.println("Filter 2 dang chay...");
        // Chuyển tiếp request
        chain.doFilter(req,res);
    }
}
