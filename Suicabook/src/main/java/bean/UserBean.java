package bean;

public class UserBean {
	private int id; //ユーザーIDを保持
	private String name; //ユーザーの名前を保持
	private String password; // ユーザーのパスワードを保持
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}