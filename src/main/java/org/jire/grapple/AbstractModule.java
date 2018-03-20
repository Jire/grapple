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

public abstract class AbstractModule<PROCESS_TYPE extends Process>
		extends AbstractSource implements Module<PROCESS_TYPE> {
	
	private final PROCESS_TYPE process;
	private final String name;
	
	public AbstractModule(MemoryCache memoryCache, PointerCache pointerCache,
	                      long size, long base, PROCESS_TYPE process, String name) {
		super(memoryCache, pointerCache, size, base);
		this.process = process;
		this.name = name;
	}
	
	@Override
	public PROCESS_TYPE getProcess() {
		return process;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean read(Pointer address, Pointer data, int bytesToRead) {
		Pointer.nativeValue(address, getBase() + Pointer.nativeValue(address)); // set the pointer to the modified address
		return getProcess().read(address, data, bytesToRead); // then do the read via process
	}
	
	@Override
	public boolean read(long address, Pointer data, int bytesToRead) {
		return getProcess().read(getBase() + address, data, bytesToRead);
	}
	
}
