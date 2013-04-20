package me.thinkjet.utils;

/**
 * 
 * @ClassName UniqueTicketIdGenerator
 * @author johnny_zyc
 * @Modified 2013-3-2 下午2:50:14
 * 
 */
public final class UniqueTicketIdGenerator {

	private final LongNumericGenerator numericGenerator;

	private final RandomStringGenerator randomStringGenerator;

	private final String suffix;

	public UniqueTicketIdGenerator() {
		this(null);
	}

	public UniqueTicketIdGenerator(final int maxLength) {
		this(maxLength, null);
	}

	public UniqueTicketIdGenerator(final String suffix) {
		this.numericGenerator = new LongNumericGenerator(1);
		this.randomStringGenerator = new RandomStringGenerator();

		if (suffix != null) {
			this.suffix = "-" + suffix;
		} else {
			this.suffix = null;
		}
	}

	public UniqueTicketIdGenerator(final int maxLength, final String suffix) {
		this.numericGenerator = new LongNumericGenerator(1);
		this.randomStringGenerator = new RandomStringGenerator(maxLength);

		if (suffix != null) {
			this.suffix = "-" + suffix;
		} else {
			this.suffix = null;
		}
	}

	public String getNewTicketId(final String prefix) {
		final String number = this.numericGenerator.getNextNumberAsString();
		final StringBuilder buffer = new StringBuilder(prefix.length() + 2
				+ (this.suffix != null ? this.suffix.length() : 0)
				+ this.randomStringGenerator.getMaxLength() + number.length());

		buffer.append(prefix);
		buffer.append("-");
		buffer.append(number);
		buffer.append("-");
		buffer.append(this.randomStringGenerator.getNewString());

		if (this.suffix != null) {
			buffer.append(this.suffix);
		}

		return buffer.toString();
	}
}
