package com.machish.time.model;

/**
 * An object that can be woken up from a clock tick.
 * 
 * @author mihirsathe
 */
public interface Clockable {

	/**
	 * Ticks this clockable.
	 */
	public void tick(final Tick tick);

}
