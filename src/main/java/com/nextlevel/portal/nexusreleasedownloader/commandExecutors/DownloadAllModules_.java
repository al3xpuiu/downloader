package com.nextlevel.portal.nexusreleasedownloader.commandExecutors;

import com.nextlevel.portal.nexusreleasedownloader.commands.DownloadAllModulesCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("DownloadAllModulesCommand_")
public class DownloadAllModules_ implements CommandExecutor {

    private final DownloadAllModulesCommand downloadAllModulesCommand;

    @Autowired
    public DownloadAllModules_(DownloadAllModulesCommand downloadAllModulesCommand) {
        this.downloadAllModulesCommand = downloadAllModulesCommand;
    }

    @Override
    public void executeCommands() {
        downloadAllModulesCommand.execute();

        System.out.println("Finished!");
        System.exit(0);
    }
}
