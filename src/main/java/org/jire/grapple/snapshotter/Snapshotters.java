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

import org.jire.grapple.Source;
import org.jire.grapple.memory.MemoryCache;
import org.jire.grapple.memory.MemoryCaches;
import org.jire.grapple.pointers.PointerCache;
import org.jire.grapple.pointers.PointerCaches;

public final class Snapshotters {
	
	public static Snapshotter snapshotter(Source source, SnapshotListener snapshotListener) {
		return memorySnapshotter(source, snapshotListener,
				MemoryCaches.defaultMemoryCache(),
				PointerCaches.defaultPointerCache());
	}
	
	public static Snapshotter memorySnapshotter(Source source, SnapshotListener snapshotListener,
	                                            MemoryCache memoryCache, PointerCache pointerCache) {
		return new MemorySnapshotter(memoryCache, pointerCache, source, snapshotListener);
	}
	
	private Snapshotters() {
		throw new UnsupportedOperationException();
	}
	
}
