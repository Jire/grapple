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
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public interface Psapi extends StdCallLibrary {
	
	boolean EnumProcessModulesEx(WinNT.HANDLE hProcess, WinDef.HMODULE[] lphModule, int cb,
	                             IntByReference lpcbNeeded, int dwFilterFlag);
	
	int GetModuleBaseNameA(WinNT.HANDLE hProcess, WinDef.HMODULE hModule, byte[] lpBaseName, int nSize);
	
	Psapi INSTANCE = Native.loadLibrary("Psapi", Psapi.class);
	
}
