package com.machish.time.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.machish.time.model.Clockable;
import com.machish.time.model.ObservableClock;
import com.machish.time.model.Tick;

/**
 * An observable clock that ticks in seconds. This clock takes various clockables and
 * delivers every tick to every clockable synchronously. This implementation guarantees
 * that every single clockable will be ticked before a new tick can return.
 * 
 * There's a TickLock at the class level that guards the tickSeconds number which prevents
 * the new tick() calls from changing the time before all clockables know about the click.
 * 
 * Note that this arrangement only makes sense since we are living in a simulated environment
 * and there's no correspondence between these seconds and real seconds.
 * 
 * @author mihirsathe
 */
public class ObservableSecondClock implements ObservableClock {

	/**
	 * Clockables to notify of ticks.
	 */
	private final List<Clockable> clockables;

	private final AtomicLong tickSeconds;
	private final Lock tickLock;

	public ObservableSecondClock() {
		this(0L);
	}

	public ObservableSecondClock(final long tickSeconds) {
		this.tickSeconds = new AtomicLong(tickSeconds);
		this.tickLock = new ReentrantLock();
		this.clockables = new ArrayList<>();
	}

    @Override
    public void addClockable(final Clockable clockable) {
        tickLock.lock();

        clockables.add(clockable);

        tickLock.unlock();
    }

    @Override
    public void tick() {
        tickLock.lock();

        // Notice that this updates the clock atomically.
        final Tick tick = new Tick(tickSeconds.incrementAndGet() * 1000L);

        for (final Clockable clockable: clockables) {
            clockable.tick(tick);
        }

        tickLock.unlock();
    }

}
