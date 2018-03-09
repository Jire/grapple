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
import com.sun.jna.platform.win32.Psapi;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import org.jire.grapple.AbstractModule;

public class WindowsModule extends AbstractModule<WindowsProcess> {
	
	private final WinDef.HMODULE hModule;
	
	public WindowsModule(WindowsProcess process, String name, long base, WinDef.HMODULE hModule) {
		super(process, name, base);
		this.hModule = hModule;
	}
	
	public WinDef.HMODULE getHModule() {
		return hModule;
	}
	
	public static String getModuleName(WinNT.HANDLE processHandle, WinDef.HMODULE module) {
		final byte[] name = new byte[256]; // support 256 ASCII characters for the name as of latest Windows 10
		if (Psapi.INSTANCE.GetModuleFileNameExA(processHandle, module, name, name.length) > 0) {
			return Native.toString(name);
		}
		return null;
	}
	
}
