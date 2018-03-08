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
import org.jire.grapple.AbstractModule;

public class WindowsModule extends AbstractModule<WindowsProcess> {
	
	private final WinDef.HMODULE hModule;
	private final long base;
	
	public WindowsModule(WindowsProcess process, WinDef.HMODULE hModule, long base) {
		super(process);
		this.hModule = hModule;
		this.base = base;
	}
	
	public WinDef.HMODULE getHModule() {
		return hModule;
	}
	
	@Override
	public long getBase() {
		return base;
	}
	
	public static String getModuleName(WindowsModule module) {
		final byte[] name = new byte[256]; // support 256 ASCII characters for the name as of latest Windows 10
		if (Psapi.INSTANCE.GetModuleFileNameExA(module.getProcess().getHandle(), module.hModule, name, name.length) > 0) {
			return Native.toString(name);
		}
		return null;
	}
	
}
