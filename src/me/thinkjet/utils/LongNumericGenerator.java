package me.thinkjet.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @ClassName LongNumericGenerator
 * @author johnny_zyc
 * @Modified 2013-3-2 下午2:49:19
 * 
 */
public class LongNumericGenerator {

	private static final int MAX_STRING_LENGTH = Long.toString(Long.MAX_VALUE)
			.length();

	private static final int MIN_STRING_LENGTH = 1;

	private final AtomicLong count;

	public LongNumericGenerator() {
		this(0);
		// nothing to do
	}

	public LongNumericGenerator(long initialValue) {
		this.count = new AtomicLong(initialValue);
	}

	public long getNextLong() {
		return this.getNextValue();
	}

	public String getNextNumberAsString() {
		return Long.toString(this.getNextValue());
	}

	public int maxLength() {
		return LongNumericGenerator.MAX_STRING_LENGTH;
	}

	public int minLength() {
		return LongNumericGenerator.MIN_STRING_LENGTH;
	}

	protected long getNextValue() {
		if (this.count.compareAndSet(Long.MAX_VALUE, 0)) {
			return Long.MAX_VALUE;
		}
		return this.count.getAndIncrement();
	}
}
