package me.thinkjet.utils.encoder;

public class ShaEncoder extends MessageDigestEncoder {

	/**
	 * Initializes the ShaPasswordEncoder for SHA-1 strength
	 */
	public ShaEncoder() {
		this(1);
	}

	/**
	 * Initialize the ShaPasswordEncoder with a given SHA stength as supported
	 * by the JVM EX:
	 * <code>ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);</code>
	 * initializes with SHA-256
	 * 
	 * @param strength
	 *            EX: 1, 256, 384, 512
	 */
	public ShaEncoder(int strength) {
		super("SHA-" + strength);
	}
}
