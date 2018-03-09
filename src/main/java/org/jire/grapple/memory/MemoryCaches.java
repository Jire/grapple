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
	
	public static final MemoryCache NON_THREAD_SAFE = new NonThreadSafe();
	
	public static Memory readCachedIfPossible(MemoryCache memoryCache, Source source, long address, int bytesToRead) {
		final boolean sizeSupported = memoryCache.isSupportedSize(bytesToRead);
		final Memory memory = sizeSupported ? memoryCache.forSize(bytesToRead) : new Memory(bytesToRead);
		source.read(address, memory, bytesToRead);
		return memory;
	}
	
	public static Memory readCachedIfPossible(Source source, long address, int bytesToRead) {
		return readCachedIfPossible(NON_THREAD_SAFE, source, address, bytesToRead);
	}
	
}
