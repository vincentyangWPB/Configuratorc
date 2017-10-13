package UserPackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAndAdmin {
	private String name;
	private String userType;
	private String password;
	private StringBuffer stringBuffer;
	
	public UserAndAdmin(String name, String password, String userType){
		this.name = name;
		this.password = password;
		this.userType = userType;
		try {
			hashedPassword();
		}catch(NoSuchAlgorithmException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void hashedPassword() throws NoSuchAlgorithmException {
		String nameAndPassword = name+password;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(nameAndPassword.getBytes());
		byte[] b = md.digest();
		stringBuffer = new StringBuffer();
		for(byte b1 : b) {
			stringBuffer.append(Integer.toHexString(b1 & 0xff).toString());
		}
	}
	
	public String getHashedPassowrd() {
		return stringBuffer.toString();
	}
	@Override
	public String toString() {
		String hashedPassword = getHashedPassowrd();
		return "{Name: " + name + "\nUser Type: " + userType + "\nHashed Password: " + hashedPassword + "}";
	}
	
//	public static void main(String[] args) {
//		UserAndAdmin user = new UserAndAdmin("k_yang6", "Vincent100m!", "admin");
//		System.out.println(user.getHashedPassowrd());
//		System.out.println(user.toString());
//	}
}
