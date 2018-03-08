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
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Psapi;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import org.jire.grapple.AbstractProcess;

import static org.jire.grapple.windows.OptimizedKernel32.ReadProcessMemory;

public class WindowsProcess extends AbstractProcess {
	
	private final WinNT.HANDLE handle;
	
	public WindowsProcess(WinNT.HANDLE handle) {
		this.handle = handle;
	}
	
	public WinNT.HANDLE getHandle() {
		return handle;
	}
	
	@Override
	public void loadModules() {
		modules.clear(); // for reloading
		
		final WinDef.HMODULE[] modules = new WinDef.HMODULE[4096];
		final IntByReference needed = new IntByReference();
		if (Psapi.INSTANCE.EnumProcessModules(handle, modules, modules.length, needed)) {
			for (int i = 0; i < needed.getValue() / Native.getNativeSize(WinDef.HMODULE.class); i++) {
				final WinDef.HMODULE hModule = modules[i];
				if (hModule == null) continue;
				
				final Psapi.MODULEINFO moduleInfo = new Psapi.MODULEINFO();
				if (Psapi.INSTANCE.GetModuleInformation(handle, hModule, moduleInfo, moduleInfo.size())) {
					final long base = Pointer.nativeValue(hModule.getPointer());
					final WindowsModule module = new WindowsModule(this, hModule, base);
					
					WindowsProcess.this.modules.put(WindowsModule.getModuleName(module), module);
				}
			}
		}
	}
	
	@Override
	public boolean read(Pointer address, Pointer data, int bytesToRead) {
		return ReadProcessMemory(handle.getPointer(), address, data, bytesToRead, null) == 1L;
	}
	
}
