package bean;

import java.io.Serializable;

public class TwintterBean implements Serializable {
	private String user_name;	//ユーザーの名前を保存
	private String twintter_text;	//コメントの内容を保存
	
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
	
}
