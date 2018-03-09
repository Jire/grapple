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

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractProcess extends AbstractSource implements Process {
	
	protected final Map<String, Module> modules = new HashMap<String, Module>();
	
	public AbstractProcess(long size, long base) {
		super(size, base);
	}
	
	public AbstractProcess(long size) {
		this(size, DEFAULT_BASE);
	}
	
	@Override
	public Map<String, Module> getModules() {
		return new HashMap<String, Module>(modules); // defensive clone
	}
	
}
