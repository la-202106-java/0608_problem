
public class Kadai2 {
	public class Omikugi {
		public Omikugi() {
		}

		public Omikugi(int month, String omikugi) {
			this.month = month;
			this.omikugi = omikugi;
		}

		private int month;
		private String omikugi;

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public String getOmikugi() {
			return omikugi;
		}

		public void setOmikugi(String omikugi) {
			this.omikugi = omikugi;
		}

	}
}
