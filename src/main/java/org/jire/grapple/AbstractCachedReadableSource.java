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

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import org.jire.grapple.memory.MemoryCache;
import org.jire.grapple.memory.MemoryCaches;
import org.jire.grapple.pointers.PointerCache;

public abstract class AbstractCachedReadableSource extends AbstractReadable implements Source {
	
	private final MemoryCache memoryCache;
	private final PointerCache pointerCache;
	
	public AbstractCachedReadableSource(MemoryCache memoryCache, PointerCache pointerCache) {
		this.memoryCache = memoryCache;
		this.pointerCache = pointerCache;
	}
	
	public MemoryCache getMemoryCache() {
		return memoryCache;
	}
	
	public PointerCache getPointerCache() {
		return pointerCache;
	}
	
	public Memory readMemory(int size, long offset) {
		return MemoryCaches.readSourceUsingCache(this, memoryCache, getBase() + offset, size);
	}
	
	@Override
	public boolean read(long address, Pointer data, int bytesToRead) {
		return read(pointerCache.forPeer(getBase() + address), data, bytesToRead);
	}
	
	@Override
	public byte readByte(long offset) {
		return readMemory(1, offset).getByte(0);
	}
	
	@Override
	public short readShort(long offset) {
		return readMemory(2, offset).getShort(0);
	}
	
	@Override
	public int readInt(long offset) {
		return readMemory(4, offset).getInt(0);
	}
	
	@Override
	public long readLong(long offset) {
		return readMemory(8, offset).getLong(0);
	}
	
	@Override
	public float readFloat(long offset) {
		return readMemory(4, offset).getFloat(0);
	}
	
	@Override
	public double readDouble(long offset) {
		return readMemory(8, offset).getDouble(0);
	}
	
	@Override
	public char readChar(long offset) {
		return readMemory(2, offset).getChar(0);
	}
	
	@Override
	public boolean readBoolean(long offset) {
		return readByte(offset) != 0;
	}
	
	@Override
	public ReadablePointer readablePointer(Pointer address) {
		return readablePointer(Pointer.nativeValue(address));
	}
	
	@Override
	public ReadablePointer readablePointer(long address) {
		final int resolvedPointerAddress = readInt(address);
		return resolvedPointerAddress <= 0 ? null
				: new ReadablePointer(getMemoryCache(), getPointerCache(),
				0, resolvedPointerAddress, this);
	}
	
}
