package bean;

import java.io.Serializable;
import java.util.Date;

public class EvaluationBean implements Serializable{
	private int id;
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
		
	public void setEvaluation_score(int evaluation_score) {
		this.evaluation_score = evaluation_score;
	}
	public String getEvaluation_score() {
		return evaluation_score;
	}
	public void setEvaluation_review(String evaluation_review) {
		this.evaluation_review = evaluation_review;
	}
	public Date getEvaluation_review() {
		return evaluation_review;
	}
	public void setBook_idt(int book_id) {
		this.book_id = book_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setImg(String imgt) {
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	
}
