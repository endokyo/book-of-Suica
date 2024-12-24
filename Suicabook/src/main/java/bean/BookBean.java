package bean;

public class BookBean {
	private int id;	//書籍IDを保持
	private String title; //書籍タイトルを保持
	private String img; // 書籍表紙を保持
	private String creater; //書籍の著者を保持
	private String genre; //書籍ジャンルを保持
	private int avecount; //評価を行った人数を保持
	private double average; //評価の平均点を保持
	private int twicount; //コメント数を保持
	private int favcount; //お気に入り登録された回数を保持
	private boolean favorite; //そのユーザーがお気に入り登録している書籍かどうかを保持
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getAvecount() {
		return avecount;
	}
	public void setAvecount(int avecount) {
		this.avecount = avecount;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public int getTwicount() {
		return twicount;
	}
	public void setTwicount(int twicount) {
		this.twicount = twicount;
	}
	public int getFavcount() {
		return favcount;
	}
	public void setFavcount(int favcount) {
		this.favcount = favcount;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	
}
