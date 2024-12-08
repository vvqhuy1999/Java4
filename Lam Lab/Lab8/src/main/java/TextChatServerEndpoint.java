
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/text/chat")
public class TextChatServerEndpoint {
    // Duy trì danh sách session của các client đang kết nối
    private static Map<String, Session> sessions = new ConcurrentHashMap<>();

    // Gửi thông điệp đến tất cả client
    private void broadcast(String message) {
        sessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(Session session) {
        // Thêm session vào danh sách
        sessions.put(session.getId(), session);
        // Gửi thông báo "Someone joined the chat!" đến tất cả
        broadcast("Someone joined the chat!");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Chuyển tiếp tin nhắn đến tất cả client
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) {
        // Xóa session khỏi danh sách
        sessions.remove(session.getId());
        // Gửi thông báo "Someone left the chat!" đến tất cả
        broadcast("Someone left the chat!");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
