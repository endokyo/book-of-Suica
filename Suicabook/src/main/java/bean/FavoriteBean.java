package bean;

	
	import java.io.Serializable;
	import java.util.Date;


	public class FavoriteBean implements Serializable {
		private int favorite_id;
		private int user_id;
		private String book_id;
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
