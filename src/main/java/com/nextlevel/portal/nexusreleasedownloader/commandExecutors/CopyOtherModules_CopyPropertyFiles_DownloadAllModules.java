package com.nextlevel.portal.nexusreleasedownloader.commandExecutors;

import com.nextlevel.portal.nexusreleasedownloader.commands.Command;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Use this executor when you already have the list of modules and you only need to copy the other modules (that
 * are not in the nexus) from the oldReleaseVersion folder.
 */

@Component("CopyOtherModules_CopyPropertyFiles_DownloadAllModules")
public class CopyOtherModules_CopyPropertyFiles_DownloadAllModules implements CommandExecutor {

    private final Command copyOtherModulesCommand;
    private final Command copyPropertyFilesCommand;
    private final Command downloadAllModulesCommand;

    public CopyOtherModules_CopyPropertyFiles_DownloadAllModules(
            @Qualifier("copyOtherModulesCommand") Command copyOtherModulesCommand,
            @Qualifier("copyPropertyFilesCommand") Command copyPropertyFilesCommand,
            @Qualifier("downloadAllModulesCommand") Command downloadAllModulesCommand) {
        this.copyOtherModulesCommand = copyOtherModulesCommand;
        this.copyPropertyFilesCommand = copyPropertyFilesCommand;
        this.downloadAllModulesCommand = downloadAllModulesCommand;
    }

    @Override
    public void executeCommands() {
        copyOtherModulesCommand.execute();
        copyPropertyFilesCommand.execute();
        downloadAllModulesCommand.execute();

        System.out.println("Finished!");
        System.exit(0);
    }
}
