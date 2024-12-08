import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Message {
    public Message(String string, int i, String username, int size) {
		// TODO Auto-generated constructor stub
	}
	private String text; // Nội dung tin nhắn
    private int type;    // Loại tin nhắn (0: vào, 1: ra, 2: lời thoại)
    private String sender; // Username của người gửi (null với type 0 hoặc 1)
    private Integer count; // Số lượng client (null khi type là 2)
	public Message(String text, int type, String sender, Integer count) {
		super();
		this.text = text;
		this.type = type;
		this.sender = sender;
		this.count = count;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
    
}
