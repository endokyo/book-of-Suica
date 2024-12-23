package bean;

import java.io.Serializable;

public class EvaluationBean implements Serializable{
	private int id; //評価のIDを保持
	private int user_id; // 評価を行ったユーザーのIDを保持
	private String user_name; //評価を行ったユーザーの名前を保持(詳細画面用)
	private int evaluation_score; // 評価点を保持
	private String evaluation_review; //評価のレビューを保持
	private int book_id; // 評価を行った書籍のIDを保持
	private String img; // 評価を行った書籍の表紙(ユーザー個人画面用)
	private String title; // 評価を行った書籍のタイトルを保持(ユーザー個人画面用)
	
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
