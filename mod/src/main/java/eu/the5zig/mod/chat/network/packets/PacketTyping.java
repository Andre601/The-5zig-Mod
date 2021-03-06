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

package eu.the5zig.mod.chat.network.packets;

import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.manager.ChatTypingManager;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by 5zig.
 * All rights reserved © 2015
 */
public class PacketTyping implements Packet {

	private UUID friend;
	private boolean typing;

	public PacketTyping(UUID friend, boolean typing) {
		this.friend = friend;
		this.typing = typing;
	}

	public PacketTyping() {
	}

	@Override
	public void read(ByteBuf buffer) throws IOException {
		this.friend = PacketBuffer.readUUID(buffer);
		this.typing = buffer.readBoolean();
	}

	@Override
	public void write(ByteBuf buffer) throws IOException {
		PacketBuffer.writeUUID(buffer, friend);
		buffer.writeBoolean(typing);
	}

	@Override
	public void handle() {
		ChatTypingManager manager = The5zigMod.getDataManager().getChatTypingManager();
		if (typing) {
			manager.addToTyping(friend);
		} else {
			manager.removeFromTyping(friend);
		}
	}
}
