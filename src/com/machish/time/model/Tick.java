package com.machish.time.model;

/**
 * Represents a single tick of the clock.
 *
 * @author mihirsathe
 */
public class Tick {

	/**
	 * Number of milliseconds since the beginning of time (for this clock).
	 */
	private final long milliseconds;

	public Tick(final long milliseconds) {
		this.milliseconds = milliseconds;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

}
