import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getEmployeeData")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập kiểu nội dung trả về là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Chuỗi JSON
        String json = "{"
                + "\"manv\": \"TeoNV\","
                + "\"hoTen\": \"Nguyễn Văn Tèo\","
                + "\"gioiTinh\": true,"
                + "\"luong\": 950.5"
                + "}";

        // Ghi JSON vào response
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
