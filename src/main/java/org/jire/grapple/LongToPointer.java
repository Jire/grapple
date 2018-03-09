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

public final class LongToPointer {
	
	public static Pointer modifyPeer(Pointer pointer, long newPeer) {
		Pointer.nativeValue(pointer, newPeer);
		return pointer;
	}
	
	public static final class ThreadSafe {
		
		private static final ThreadLocal<Pointer> pointer =
				new ThreadLocal<Pointer>() {
					@Override
					public Pointer get() {
						return Pointer.createConstant(0L);
					}
				};
		
		public static Pointer get(long peer) {
			return LongToPointer.modifyPeer(ThreadSafe.pointer.get(), peer);
		}
		
		private ThreadSafe() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public static final class NonThreadSafe {
		
		private static final Pointer pointer = Pointer.createConstant(0L);
		
		public static Pointer get(long peer) {
			return LongToPointer.modifyPeer(pointer, peer);
		}
		
		private NonThreadSafe() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private LongToPointer() {
		throw new UnsupportedOperationException();
	}
	
}
