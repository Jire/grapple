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

import com.sun.jna.Pointer;
import org.jire.grapple.memory.MemoryCache;
import org.jire.grapple.pointers.PointerCache;

public abstract class AbstractBackedSource<PARENT_TYPE extends Source> extends AbstractSource {
	
	private final PARENT_TYPE parent;
	
	public AbstractBackedSource(MemoryCache memoryCache, PointerCache pointerCache,
	                            long size, long base, PARENT_TYPE parent) {
		super(memoryCache, pointerCache, size, base);
		this.parent = parent;
	}
	
	public PARENT_TYPE getParent() {
		return parent;
	}
	
	@Override
	public long getBase() {
		return parent.getBase() + getChildBase();
	}
	
	@Override
	public boolean read(Pointer address, Pointer data, int bytesToRead) {
		return parent.read(address, data, bytesToRead);
	}
	
	@Override
	public boolean read(long address, Pointer data, int bytesToRead) {
		return parent.read(address, data, bytesToRead);
	}
	
	public long getChildBase() {
		return super.getBase();
	}
	
}
