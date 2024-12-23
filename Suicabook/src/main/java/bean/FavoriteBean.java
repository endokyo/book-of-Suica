package bean;

	
	import java.io.Serializable;


	public class FavoriteBean implements Serializable {
		private int favorite_id;
		private int user_id;
		private int book_id;

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
		public void setBook_id(int book_id) {
			this.book_id = book_id;
		}
		public int getBook_id() {
			return book_id;
		}
		
	}
