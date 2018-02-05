package com.machish.time.model;

/**
 * A clock interface that can be subscribed to to receive every tick.
 * 
 * @author mihirsathe
 */
public interface ObservableClock {

	/**
	 * Add a clockable that will listen to the ticks from this call.
	 * 
	 * @param clockable that will be "clocked" on every tick in this clock.
	 */
	public void addClockable(final Clockable clockable);

	/**
	 * Tick the clock for all the clockables registered with this clock.
	 */
	public void clock();

}
