package bean;

	
	import java.io.Serializable;
	import java.util.Date;


	public class FavoriteBean implements Serializable {
		private int id;
		private int book_id;
        private int user_id;

    	public int getId() {
    		return id;
    	}
    	public void setId(int id) {
    		this.id = id;
    	}
		public String getBook_id() {
			return book_id;
		}
		public void setBook_id(String book_id) {
			this.book_id = book_id;
		}
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
	}
