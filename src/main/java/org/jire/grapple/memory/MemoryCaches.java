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
import org.jire.grapple.Source;

public final class MemoryCaches {
	
	public static MemoryCache defaultMemoryCache() {
		return singleMemoryCache(8);
	}
	
	public static MemoryCache singleMemoryCache(int maxSupportedSize) {
		return new SingleMemoryCache(maxSupportedSize);
	}
	
	public static MemoryCache arrayMemoryCache(int maxSupportedSize) {
		return new BinarySizeArrayMemoryCache(maxSupportedSize);
	}
	
	public static Memory readSourceUsingCache(Source source, MemoryCache cache, long address, int bytesToRead) {
		final Memory memory = cache.forSize(bytesToRead);
		if (memory != null) {
			source.read(address, memory, bytesToRead);
		}
		return memory;
	}
	
	private MemoryCaches() {
		throw new UnsupportedOperationException();
	}
	
}
