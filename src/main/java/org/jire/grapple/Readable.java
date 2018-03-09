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

public interface Readable {
	
	byte readByte(long offset);
	
	byte readByte();
	
	short readShort(long offset);
	
	short readShort();
	
	int readInt(long offset);
	
	int readInt();
	
	long readLong(long offset);
	
	long readLong();
	
	float readFloat(long offset);
	
	float readFloat();
	
	double readDouble(long offset);
	
	double readDouble();
	
	char readChar(long offset);
	
	char readChar();
	
	boolean readBoolean(long offset);
	
	boolean readBoolean();
	
}
