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

public abstract class AbstractReadable implements Readable {
	
	protected static final long DEFAULT_OFFSET = 0L;
	
	@Override
	public byte readByte() {
		return readByte(DEFAULT_OFFSET);
	}
	
	@Override
	public short readShort() {
		return readShort(DEFAULT_OFFSET);
	}
	
	@Override
	public int readInt() {
		return readInt(DEFAULT_OFFSET);
	}
	
	@Override
	public long readLong() {
		return readLong(DEFAULT_OFFSET);
	}
	
	@Override
	public float readFloat() {
		return readFloat(DEFAULT_OFFSET);
	}
	
	@Override
	public double readDouble() {
		return readDouble(DEFAULT_OFFSET);
	}
	
	@Override
	public char readChar() {
		return readChar(DEFAULT_OFFSET);
	}
	
	@Override
	public boolean readBoolean() {
		return readBoolean(DEFAULT_OFFSET);
	}
	
}
