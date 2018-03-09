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
import org.jire.grapple.memory.MemoryCaches;

public abstract class AbstractSource extends AbstractReadable implements Source {
	
	protected static final long DEFAULT_BASE = 0L;
	
	private final long size;
	private final long base;
	
	public AbstractSource(long size, long base) {
		this.size = size;
		this.base = base;
	}
	
	public AbstractSource(long size) {
		this(size, DEFAULT_BASE);
	}
	
	@Override
	public long getSize() {
		return size;
	}
	
	@Override
	public long getBase() {
		return base;
	}
	
	@Override
	public boolean read(long address, Pointer data, int bytesToRead) {
		return read(LongToPointer.NonThreadSafe.get(getBase() + address), data, bytesToRead);
	}
	
	@Override
	public byte readByte(long offset) {
		return MemoryCaches.readCachedIfPossible(this, getBase() + offset, 1).getByte(0);
	}
	
	@Override
	public short readShort(long offset) {
		return MemoryCaches.readCachedIfPossible(this, getBase() + offset, 2).getShort(0);
	}
	
	@Override
	public int readInt(long offset) {
		return MemoryCaches.readCachedIfPossible(this, getBase() + offset, 4).getInt(0);
	}
	
	@Override
	public long readLong(long offset) {
		return MemoryCaches.readCachedIfPossible(this, getBase() + offset, 8).getLong(0);
	}
	
	@Override
	public float readFloat(long offset) {
		return MemoryCaches.readCachedIfPossible(this, getBase() + offset, 4).getFloat(0);
	}
	
	@Override
	public double readDouble(long offset) {
		return MemoryCaches.readCachedIfPossible(this, getBase() + offset, 8).getDouble(0);
	}
	
	@Override
	public char readChar(long offset) {
		return MemoryCaches.readCachedIfPossible(this, getBase() + offset, 2).getChar(0);
	}
	
	@Override
	public boolean readBoolean(long offset) {
		return readByte(offset) != 0;
	}
	
}
