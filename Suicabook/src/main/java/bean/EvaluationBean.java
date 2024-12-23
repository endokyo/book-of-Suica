package bean;

import java.io.Serializable;

public class EvaluationBean implements Serializable{
	private int id;
	private int user_id;
	private String user_name;
	private int evaluation_score;
	private String evaluation_review;
	private int book_id;
	private String img;
	private String title;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getEvaluation_score() {
		return evaluation_score;
	}
	public void setEvaluation_score(int evaluation_score) {
		this.evaluation_score = evaluation_score;
	}
	public String getEvaluation_review() {
		return evaluation_review;
	}
	public void setEvaluation_review(String evaluation_review) {
		this.evaluation_review = evaluation_review;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
