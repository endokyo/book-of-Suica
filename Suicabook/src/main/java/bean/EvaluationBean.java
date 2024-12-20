package bean;

import java.io.Serializable;
import java.util.Date;

public class EvaluationBean {
	private int evaluation_id;
	private int user_id;
	private String book_id;
	private int evaluation_score;
	private String evaluation_review;
	private Date created_at;
	private Date updated_at;

	public int getFavorite_id() {
		return favorite_id;
	}
	public void setFavorite_id(int favorite_id) {
		this.favorite_id = favorite_id;
	}
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public Date getCreated_at() {
		return created_at;
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
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public int getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

}
