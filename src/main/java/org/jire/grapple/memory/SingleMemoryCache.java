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

final class SingleMemoryCache extends AbstractMemoryCache {
	
	private final int maxSupportedSize;
	private final Memory memory;
	
	SingleMemoryCache(int maxSupportedSize) {
		this.maxSupportedSize = maxSupportedSize;
		this.memory = new Memory(maxSupportedSize);
	}
	
	@Override
	public boolean isSupportedSize(int size) {
		return size <= maxSupportedSize;
	}
	
	@Override
	public Memory forSize(int size) {
		memory.clear(size);
		return memory;
	}
	
}
