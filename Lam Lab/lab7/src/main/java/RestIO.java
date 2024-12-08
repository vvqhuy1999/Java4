import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class RestIO {
    private static final ObjectMapper mapper = new ObjectMapper();

    // Đọc chuỗi JSON gửi từ client
    public static String readJson(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("utf-8");
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        return json.toString();
    }

    // Gửi chuỗi JSON về client
    public static void writeJson(HttpServletResponse resp, String json) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(json);
    }

    // Đọc JSON từ client và chuyển thành Object
    public static <T> T readObject(HttpServletRequest req, Class<T> clazz) throws IOException {
        return mapper.readValue(readJson(req), clazz);
    }

    // Chuyển Object thành JSON và gửi về client
    public static void writeObject(HttpServletResponse resp, Object data) throws IOException {
        writeJson(resp, mapper.writeValueAsString(data));
    }

    // Gửi Object rỗng
    public static void writeEmptyObject(HttpServletResponse resp) throws IOException {
        writeObject(resp, Map.of());
    }
    
    
    
}
