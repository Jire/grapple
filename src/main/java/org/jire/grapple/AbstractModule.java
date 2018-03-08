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

public abstract class AbstractModule<PROCESS_TYPE extends Process> implements Module<PROCESS_TYPE> {
	
	private final PROCESS_TYPE process;
	
	public AbstractModule(PROCESS_TYPE process) {
		this.process = process;
	}
	
	public PROCESS_TYPE getProcess() {
		return process;
	}
	
	@Override
	public boolean read(Pointer address, Pointer data, int bytesToRead) {
		return read(LongToPointer.get(address,
				Pointer.nativeValue(address) + process.getBase() + getBase()),
				data, bytesToRead);
	}
	
	@Override
	public boolean read(long address, Pointer data, int bytesToRead) {
		return read(address + process.getBase() + getBase(), data, bytesToRead);
	}
	
}
