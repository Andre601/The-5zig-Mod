/*
 * Copyright (c) 2019-2020 5zig Reborn
 * Copyright (c) 2015-2019 5zig
 *
 * This file is part of The 5zig Mod
 * The 5zig Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The 5zig Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with The 5zig Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.the5zig.mod.gui.ts.entries;

import eu.the5zig.mod.I18n;
import eu.the5zig.mod.gui.elements.BasicRow;
import eu.the5zig.mod.gui.elements.Row;
import eu.the5zig.teamspeak.api.Channel;

import java.util.List;

public abstract class GuiTeamSpeakEntry {

	public abstract void render(int x, int y, int width, int height);

	/**
	 * Used, so that we have to bind {@link eu.the5zig.mod.The5zigMod#TEAMSPEAK_ICONS} only once per frame.
	 */
	public abstract void renderIcons(int x, int y, int width, int height);

	public abstract void onClick(boolean doubleClick);

	public abstract int getXOffset();

	public abstract List<? extends Row> getDescription(int width);

	protected int getParentChannels(Channel channel) {
		int parents = 0;
		while ((channel = channel.getParent()) != null) {
			parents++;
		}
		return parents;
	}

	protected String translate(String key, Object... format) {
		return I18n.translate("teamspeak.entry." + key, format);
	}

	protected Row row(int width, String key, Object... format) {
		return new BasicRow(translate(key, format), width);
	}

}
