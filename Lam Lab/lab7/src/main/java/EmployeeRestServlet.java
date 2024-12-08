
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/employees/*")
public class EmployeeRestServlet extends HttpServlet {
    private final Map<String, Employee> employees = new HashMap<>(Map.of(
        "NV01", new Employee("NV01", "Nhân viên 01", true, 500),
        "NV02", new Employee("NV02", "Nhân viên 02", false, 1500),
        "NV03", new Employee("NV03", "Nhân viên 03", true, 5000),
            "NV04", new Employee("NV04", "Nhân viên 04", true, 5000),
            "NV05", new Employee("NV05", "Nhân viên 05", true, 5000),
            "NV06", new Employee("NV06", "Nhân viên 06", true, 5000)
    ));

    // Lấy danh sách 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = getPathId(req);
        if (id == null) {
            RestIO.writeObject(resp, employees.values());
        } else {
            Employee emp = employees.get(id);
            RestIO.writeObject(resp, emp != null ? emp : Map.of("error", "Không tìm thấy nhân viên"));
        }
    }

    // Thêm 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Employee employee = RestIO.readObject(req, Employee.class);
        employees.put(employee.getId(), employee);
        RestIO.writeObject(resp, employee);
    }

    // Cập nhật
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = getPathId(req);
        if (id != null && employees.containsKey(id)) {
            Employee updatedEmp = RestIO.readObject(req, Employee.class);
            employees.put(id, updatedEmp);
            RestIO.writeEmptyObject(resp);
        } else {
            RestIO.writeObject(resp, Map.of("error", "Không tìm thấy nhân viên"));
        }
    }

    // Xóa 
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = getPathId(req);
        if (id != null && employees.remove(id) != null) {
            RestIO.writeEmptyObject(resp);
        } else {
            RestIO.writeObject(resp, Map.of("error", "Không tìm thấy nhân viên"));
        }
    }

    // Hàm lấy ID từ URL
    private String getPathId(HttpServletRequest req) {
        String path = req.getPathInfo();
        return (path != null && path.length() > 1) ? path.substring(1) : null;
    }
    
    
    
 

}
