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

import java.util.List;

public abstract class AbstractProcessFinder implements ProcessFinder {
	
	@Override
	public Process findProcessByID(int processID) {
		return findProcessByID(processID, defaultAccessFlags());
	}
	
	@Override
	public List<Process> findProcessesByName(String processName) {
		return findProcessesByName(processName, defaultAccessFlags());
	}
	
	@Override
	public Process findFirstProcessByName(String processName, int accessFlags) {
		final List<Process> processesByName = findProcessesByName(processName, accessFlags);
		return processesByName == null ? null : processesByName.get(0);
	}
	
	@Override
	public Process findFirstProcessByName(String processName) {
		return findFirstProcessByName(processName, defaultAccessFlags());
	}
	
}
