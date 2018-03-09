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

package org.jire.grapple.memory;

import com.sun.jna.Memory;

final class NonThreadSafe implements MemoryCache {
	
	// reason we index by size is because it's far faster than a map-lookup!
	
	private final Memory[] MEMORY_INDEXED_BY_SIZE = new Memory[8 + 1]; // support up to size of long (8 bytes)
	
	NonThreadSafe() {
		for (int i = 2; i <= 8; i += 2) {
			MEMORY_INDEXED_BY_SIZE[i] = new Memory(i);
		}
	}
	
	@Override
	public boolean isSupportedSize(int size) {
		return size <= 8 && size % 2 == 0;
	}
	
	@Override
	public Memory forSize(int size) {
		final Memory memory = MEMORY_INDEXED_BY_SIZE[size];
		memory.clear(size);
		return memory;
	}
	
}