package code;
public class SystemAdministration {

	private User user;

	/**
	 * 
	 * @param user
	 */
	public SystemAdministration(User user) {
		this.user = user;
	}

	public void authorize() {
		this.user.logIn();
	}
}