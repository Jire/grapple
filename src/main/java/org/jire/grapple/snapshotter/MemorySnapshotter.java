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

import com.sun.jna.Memory;
import org.jire.grapple.Source;
import org.jire.grapple.memory.MemoryCache;
import org.jire.grapple.pointers.PointerCache;

final class MemorySnapshotter extends AbstractSnapshotter {
	
	private final Memory memory;
	
	MemorySnapshotter(MemoryCache memoryCache, PointerCache pointerCache,
	                  Source source, SnapshotListener snapshotListener) {
		super(memoryCache, pointerCache, source, snapshotListener);
		
		this.memory = new Memory(getSize());
	}
	
	public Memory getMemory() {
		return memory;
	}
	
	@Override
	public void takeSnapshot() {
		if (getSource().read(0L, memory, (int) getSize() /* supports 32-bit process size only */)) {
			afterSnapshot();
		}
	}
	
}
