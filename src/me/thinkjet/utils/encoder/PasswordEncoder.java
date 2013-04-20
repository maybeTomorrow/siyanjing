package me.thinkjet.utils.encoder;

/**
 * 
 * @ClassName PasswordEncoder
 * @author johnny_zyc
 * @Modified 2013-3-2 下午2:49:24
 * 
 */
public class PasswordEncoder {

	public static String encode(final String password, String salt) {
		Encoder encoder = new ShaEncoder();
		return encoder.encode(password, salt);
	}
}
