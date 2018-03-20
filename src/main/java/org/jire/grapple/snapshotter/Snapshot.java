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

import org.jire.grapple.BackedSource;
import org.jire.grapple.Source;

import java.util.concurrent.ThreadFactory;

public class Snapshot extends BackedSource implements Snapshotter {
	
	private final Snapshotter snapshotter;
	
	public Snapshot(Snapshotter snapshotter) {
		super(snapshotter);
		this.snapshotter = snapshotter;
	}
	
	@Override
	public Source getSource() {
		return snapshotter.getSource();
	}
	
	@Override
	public void takeSnapshot() {
		snapshotter.takeSnapshot();
	}
	
	@Override
	public void autoTakeSnapshot(ThreadFactory threadFactory, long delayMillis) {
		snapshotter.autoTakeSnapshot(threadFactory, delayMillis);
	}
	
	@Override
	public void autoTakeSnapshot(long delayMillis) {
		snapshotter.autoTakeSnapshot(delayMillis);
	}
	
	@Override
	public void autoTakeSnapshot() {
		snapshotter.autoTakeSnapshot();
	}
	
	@Override
	public void afterSnapshot() {
		snapshotter.afterSnapshot();
	}
	
}
