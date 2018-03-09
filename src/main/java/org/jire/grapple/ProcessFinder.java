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

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinNT;
import org.jire.grapple.windows.DWORDConstants;
import org.jire.grapple.windows.WindowsProcess;

import java.util.ArrayList;
import java.util.List;

public final class ProcessFinder {
	
	// TODO support other OS
	
	public static Process findProcessByID(int processID, int accessFlags) {
		final WinNT.HANDLE handle = Kernel32.INSTANCE.OpenProcess(accessFlags, true, processID);
		if (handle != null) {
			return new WindowsProcess(0L/*XXX*/, handle);
		}
		return null;
	}
	
	public static List<Process> findProcessesByName(String name, int accessFlags) {
		final List<Process> processes = new ArrayList<Process>();
		
		final WinNT.HANDLE snapshot = Kernel32.INSTANCE.CreateToolhelp32Snapshot(
				Tlhelp32.TH32CS_SNAPALL, DWORDConstants.ZERO);
		final Tlhelp32.PROCESSENTRY32.ByReference entry = new Tlhelp32.PROCESSENTRY32.ByReference();
		try {
			while (Kernel32.INSTANCE.Process32Next(snapshot, entry)) {
				final String fileName = Native.toString(entry.szExeFile);
				if (name.equals(fileName)) {
					final Process processByID = findProcessByID(entry.th32ProcessID.intValue(), accessFlags);
					if (processByID != null) processes.add(processByID);
				}
			}
		} finally {
			Kernel32.INSTANCE.CloseHandle(snapshot);
		}
		
		return processes;
	}
	
	public static List<Process> findProcessesByTitle(String title, int accessFlags) {
		throw new UnsupportedOperationException("TODO");//XXX
	}
	
}
