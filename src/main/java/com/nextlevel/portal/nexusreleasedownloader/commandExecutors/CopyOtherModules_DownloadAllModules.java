package com.nextlevel.portal.nexusreleasedownloader.commandExecutors;

import com.nextlevel.portal.nexusreleasedownloader.commands.CopyOtherModulesCommand;
import com.nextlevel.portal.nexusreleasedownloader.commands.DownloadAllModulesCommand;
import org.springframework.stereotype.Component;

/**
 * Use this executor when you want just the .zip files. Maybe you have two releases and
 * .property files differ.
 */

@Component("CopyOtherModules_DownloadAllModules")
public class CopyOtherModules_DownloadAllModules implements CommandExecutor {

    private final CopyOtherModulesCommand copyOtherModulesCommand;
    private final DownloadAllModulesCommand downloadAllModulesCommand;

    public CopyOtherModules_DownloadAllModules(CopyOtherModulesCommand copyOtherModulesCommand, DownloadAllModulesCommand downloadAllModulesCommand) {
        this.copyOtherModulesCommand = copyOtherModulesCommand;
        this.downloadAllModulesCommand = downloadAllModulesCommand;
    }

    @Override
    public void executeCommands() {
        copyOtherModulesCommand.execute();
        downloadAllModulesCommand.execute();

        System.out.println("Finished!");
        System.exit(0);
    }
}
