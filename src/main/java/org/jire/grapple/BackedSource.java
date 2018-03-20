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

public abstract class BackedSource implements Source {
	
	private final Source backing;
	
	public BackedSource(Source backing) {
		this.backing = backing;
	}
	
	@Override
	public long getSize() {
		return backing.getSize();
	}
	
	@Override
	public long getBase() {
		return backing.getBase();
	}
	
	@Override
	public boolean read(Pointer address, Pointer data, int bytesToRead) {
		return backing.read(address, data, bytesToRead);
	}
	
	@Override
	public boolean read(long address, Pointer data, int bytesToRead) {
		return backing.read(address, data, bytesToRead);
	}
	
	@Override
	public byte readByte(long offset) {
		return backing.readByte(offset);
	}
	
	@Override
	public byte readByte() {
		return backing.readByte();
	}
	
	@Override
	public short readShort(long offset) {
		return backing.readShort(offset);
	}
	
	@Override
	public short readShort() {
		return backing.readShort();
	}
	
	@Override
	public int readInt(long offset) {
		return backing.readInt(offset);
	}
	
	@Override
	public int readInt() {
		return backing.readInt();
	}
	
	@Override
	public long readLong(long offset) {
		return backing.readLong(offset);
	}
	
	@Override
	public long readLong() {
		return backing.readLong();
	}
	
	@Override
	public float readFloat(long offset) {
		return backing.readFloat(offset);
	}
	
	@Override
	public float readFloat() {
		return backing.readFloat();
	}
	
	@Override
	public double readDouble(long offset) {
		return backing.readDouble(offset);
	}
	
	@Override
	public double readDouble() {
		return backing.readDouble();
	}
	
	@Override
	public char readChar(long offset) {
		return backing.readChar(offset);
	}
	
	@Override
	public char readChar() {
		return backing.readChar();
	}
	
	@Override
	public boolean readBoolean(long offset) {
		return backing.readBoolean(offset);
	}
	
	@Override
	public boolean readBoolean() {
		return backing.readBoolean();
	}
	
}
