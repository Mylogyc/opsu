/*
 * opsu! - an open-source osu! client
 * Copyright (C) 2014, 2015 Jeffrey Han
 *
 * opsu! is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * opsu! is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with opsu!.  If not, see <http://www.gnu.org/licenses/>.
 */

package itdelatrisu.opsu.replay;

import itdelatrisu.opsu.OsuHitObject;

/**
 * Captures a single replay frame.
 *
 * @author smoogipooo (https://github.com/smoogipooo/osu-Replay-API/)
 */
public class ReplayFrame {
	/** Key bits. */
	public static final int
		KEY_NONE = 0,
		KEY_M1 = (1 << 0),
		KEY_M2 = (1 << 1),
		KEY_K1 = (1 << 2) | (1 << 0),
		KEY_K2 = (1 << 3) | (1 << 1);

	/** Time, in milliseconds, since the previous action. */
	private int timeDiff;

	/** Time, in milliseconds. */
	private int time;

	/** Cursor coordinates (in OsuPixels). */
	private float x, y;

	/** Keys pressed (bitmask). */
    private int keys;
 
	/**
	 * Constructor.
	 * @param timeDiff time since the previous action (in ms)
	 * @param time time (in ms)
	 * @param x cursor x coordinate [0, 512]
	 * @param y cursor y coordinate [0, 384]
	 * @param keys keys pressed (bitmask)
	 */
	public ReplayFrame(int timeDiff, int time, float x, float y, int keys) {
		this.timeDiff = timeDiff;
		this.time = time;
		this.x = x;
		this.y = y;
		this.keys = keys;
	}

	/**
	 * Returns the frame time, in milliseconds.
	 */
	public int getTime() { return time; }

	/**
	 * Returns the time since the previous action, in milliseconds.
	 */
	public int getTimeDiff() { return timeDiff; }

	/**
	 * Returns the scaled cursor x coordinate.
	 */
	public int getX() { return (int) (x * OsuHitObject.getXMultiplier() + OsuHitObject.getXOffset()); }

	/**
	 * Returns the scaled cursor y coordinate.
	 */
	public int getY() { return (int) (y * OsuHitObject.getYMultiplier() + OsuHitObject.getYOffset()); }

	/**
	 * Returns the raw cursor x coordinate.
	 */
	public float getRawX() { return x; }

	/**
	 * Returns the raw cursor y coordinate.
	 */
	public float getRawY() { return y; }

	/**
	 * Returns the keys pressed (KEY_* bitmask).
	 */
	public int getKeys() { return keys; }

	/**
	 * Returns whether or not a key is pressed.
	 */
	public boolean isKeyPressed() { return (keys != KEY_NONE); }

	@Override
	public String toString() {
		return String.format("(%d, [%.2f, %.2f], %d)", time, x, y, keys);
	}
}