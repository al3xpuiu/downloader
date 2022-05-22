package com.nextlevel.portal.nexusreleasedownloader.commandExecutors;

import com.nextlevel.portal.nexusreleasedownloader.commands.DownloadAllMd5ModulesCommand;
import com.nextlevel.portal.nexusreleasedownloader.commands.RenameModulesCommand;
import org.springframework.stereotype.Component;

@Component("DownloadAllMd5ModulesCommand_RenameModulesCommand")
public class DownloadAllMd5ModulesCommand_RenameModulesCommand implements CommandExecutor {

    private final DownloadAllMd5ModulesCommand downloadAllMd5ModulesCommand;
    private final RenameModulesCommand renameModulesCommand;

    public DownloadAllMd5ModulesCommand_RenameModulesCommand(DownloadAllMd5ModulesCommand downloadAllMd5ModulesCommand, RenameModulesCommand renameModulesCommand) {
        this.downloadAllMd5ModulesCommand = downloadAllMd5ModulesCommand;
        this.renameModulesCommand = renameModulesCommand;
    }

    @Override
    public void executeCommands() {

        downloadAllMd5ModulesCommand.execute();
        renameModulesCommand.execute();

        System.out.println("Finished!");
        System.exit(0);
    }
}
