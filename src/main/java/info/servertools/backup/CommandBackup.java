/*
 * Copyright 2014 ServerTools
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.servertools.backup;

import info.servertools.core.command.CommandLevel;
import info.servertools.core.command.ServerToolsCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandBackup extends ServerToolsCommand {

    public CommandBackup(String defaultName) {
        super(defaultName);
    }

    @Override
    public CommandLevel getCommandLevel() {
        if (MinecraftServer.getServer().isSinglePlayer())
            return CommandLevel.ANYONE;
        else
            return CommandLevel.OP;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        if (MinecraftServer.getServer().isSinglePlayer()
                && MinecraftServer.getServer().getServerOwner().equals(sender.getCommandSenderName())) {
            return true;
        } else {
            return super.canCommandSenderUseCommand(sender);
        }
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        BackupManager.getInstance().doBackup();
    }
}
