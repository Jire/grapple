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

package org.jire.grapple;

import org.jire.grapple.memory.MemoryCache;
import org.jire.grapple.pointers.PointerCache;

public abstract class AbstractSource extends AbstractCachedReadableSource implements Source {
	
	protected static final long DEFAULT_BASE = 0L;
	
	private final long size;
	private final long base;
	
	public AbstractSource(MemoryCache memoryCache, PointerCache pointerCache, long size, long base) {
		super(memoryCache, pointerCache);
		
		this.size = size;
		this.base = base;
	}
	
	public AbstractSource(MemoryCache memoryCache, PointerCache pointerCache, long size) {
		this(memoryCache, pointerCache, size, DEFAULT_BASE);
	}
	
	@Override
	public long getSize() {
		return size;
	}
	
	@Override
	public long getBase() {
		return base;
	}
	
}
