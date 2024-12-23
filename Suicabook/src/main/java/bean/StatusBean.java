package bean;

import java.io.Serializable;

public class StatusBean implements Serializable  {
	private int todaygenre;	//おすすめのジャンルを保存
	private String keyword;	//検索ワードを保存
	private String genre;	//検索ジャンルを保存
	private int page; 		//一覧画面の現在表示されているページを保存
	
	public int getTodaygenre() {
		return todaygenre;
	}
	public void setTodaygenre(int todaygenre) {
		this.todaygenre = todaygenre;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
