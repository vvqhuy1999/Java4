package Servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class Lab5_bai2 implements ServletContextListener, HttpSessionListener {
    private static final String VISITOR_COUNT_FILE = "visitor_count.txt";
    private Path filePath;
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);
        ServletContext context = sce.getServletContext();
        AtomicInteger visitorCount = (AtomicInteger) context.getAttribute("visitors");

        if (visitorCount != null) {
            try {
                String appPath = context.getRealPath("/");
                filePath = Paths.get(appPath, "WEB-INF", VISITOR_COUNT_FILE);
                // In ra đường dẫn
                System.out.println("Visitor count file location: " + filePath.toAbsolutePath());
                // Lưu số đếm vào file
                Files.write(filePath, String.valueOf(visitorCount.get()).getBytes());
                System.out.println("Da luu so luong visitor: " + visitorCount.get());

                // Tạo backup
                Path backupPath = Paths.get(appPath, "WEB-INF", "visitor_count_backup.txt");
                Files.copy(filePath, backupPath, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                System.err.println("Lỗi khi lưu counter: " + e.getMessage());
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        AtomicInteger visitorCount = new AtomicInteger(0);
        try {
            // Lấy đường dẫn tuyệt đối để lưu file
            String appPath = context.getRealPath("/");
            Path filePath = Paths.get(appPath, "WEB-INF", VISITOR_COUNT_FILE);

            // Tạo thư mục WEB-INF nếu chưa tồn tại
            Files.createDirectories(filePath.getParent());

            // Kiểm tra và tạo file nếu chưa tồn tại
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                Files.write(filePath, "0".getBytes());
                System.out.println("Đã tạo file counter mới tại: " + filePath);
            }
            // In ra đường dẫn
            System.out.println("Visitor count file location: " + filePath.toAbsolutePath());
            // Đọc số đếm từ file
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String countStr = reader.readLine();
                if (countStr != null && !countStr.trim().isEmpty()) {
                    int count = Integer.parseInt(countStr.trim());
                    visitorCount.set(count);
                    System.out.println("Da doc so luong visitor: " + count);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khởi tạo counter: " + e.getMessage());
        }
        // Lưu vào application scope
        context.setAttribute("visitors", visitorCount);
        System.out.println("Da khoi tao  visitor counter voi gia tri: " + visitorCount.get());
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        HttpSessionListener.super.sessionCreated(se);
        HttpSession session = se.getSession();
        ServletContext context = se.getSession().getServletContext();
        // Kiểm tra xem session này đã được đếm chưa
        if (session.getAttribute("counted") == null) {
            AtomicInteger visitorCount = (AtomicInteger) context.getAttribute("visitors");

            if (visitorCount == null) {
                visitorCount = new AtomicInteger(0);
            }

            // Tăng số đếm và cập nhật lại vào application scope
            int newCount = visitorCount.incrementAndGet();
            context.setAttribute("visitors", visitorCount);

            // Đánh dấu session này đã được đếm
            session.setAttribute("counted", true);

            System.out.println("Visitor moi - Session ID: " + session.getId() +
                    ", Tong so: " + newCount);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        HttpSession session = se.getSession();
//        ServletContext context = session.getServletContext();
//
//        // Kiểm tra xem session này đã được đếm chưa
//        if (session.getAttribute("counted") != null) {
//            AtomicInteger visitorCount = (AtomicInteger) context.getAttribute("visitors");
//
//            if (visitorCount != null) {
//                // Giảm số đếm và cập nhật lại vào application scope
//                int newCount = visitorCount.decrementAndGet();
//                context.setAttribute("visitors", visitorCount);
//
//                System.out.println("Visitor roi di - Session ID: " + session.getId() +
//                        ", Tong so con lai: " + newCount);
//            }
//        }
    }

}
