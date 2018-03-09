/*
 * Grapple - Easy interaction with native processes, all from Java
 * Copyright (C) 2018 Thomas G. P. Nappo (Jire)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.jire.grapple.snapshotter;

import com.sun.jna.Pointer;
import org.jire.grapple.AbstractSource;
import org.jire.grapple.Source;

import java.util.concurrent.ThreadFactory;

public abstract class AbstractSnapshotter extends AbstractSource implements Snapshotter {
	
	public static final long DEFAULT_SNAPSHOT_DELAY_MILLIS = 16L; // approximately 60 FPS
	
	private static final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
		@Override
		public Thread newThread(final Runnable r) {
			return new Thread("Snapshotter") {
				@Override
				public void run() {
					r.run();
				}
			};
		}
	};
	
	private final Source source;
	
	public AbstractSnapshotter(Source source) {
		super(source.getSize(), source.getBase());
		
		this.source = source;
	}
	
	@Override
	public void autoTakeSnapshot(ThreadFactory threadFactory, final long delayMillis) {
		final Thread thread = threadFactory.newThread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					takeSnapshot();
					
					try {
						Thread.sleep(delayMillis);
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	@Override
	public void autoTakeSnapshot(long delayMillis) {
		autoTakeSnapshot(DEFAULT_THREAD_FACTORY, delayMillis);
	}
	
	@Override
	public void autoTakeSnapshot() {
		autoTakeSnapshot(DEFAULT_SNAPSHOT_DELAY_MILLIS);
	}
	
	@Override
	public long getSize() {
		return getSource().getSize();
	}
	
	@Override
	public long getBase() {
		return getSource().getBase();
	}
	
	@Override
	public boolean read(Pointer address, Pointer data, int bytesToRead) {
		return getSource().read(address, data, bytesToRead);
	}
	
	@Override
	public boolean read(long address, Pointer data, int bytesToRead) {
		return getSource().read(address, data, bytesToRead);
	}
	
	@Override
	public Source getSource() {
		return source;
	}
	
}
