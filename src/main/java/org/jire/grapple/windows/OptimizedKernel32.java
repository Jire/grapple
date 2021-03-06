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

package org.jire.grapple.windows;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;

public final class OptimizedKernel32 {
	
	public static native long ReadProcessMemory(final Pointer hProcess,
	                                            final Pointer lpBaseAddress,
	                                            final Pointer lpBuffer,
	                                            final int nSize,
	                                            final Pointer lpNumberOfBytesRead);
	
	static {
		Native.register(NativeLibrary.getInstance("Kernel32"));
	}
	
	private OptimizedKernel32() {
	}
	
}
