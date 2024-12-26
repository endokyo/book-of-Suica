package bean;

import java.io.Serializable;
import java.util.Date;

public class TwintterBean implements Serializable {
	private int user_id; //コメントしたユーザーのIDを保持
	private String user_name;	//ユーザーの名前を保存
	private int book_id; //コメントされた書籍のIDを保持
	private String twintter_text;	//コメントの内容を保存
	private Date created_at;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTwintter_text() {
		return twintter_text;
	}
	public void setTwintter_text(String twintter_text) {
		this.twintter_text = twintter_text;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
}
