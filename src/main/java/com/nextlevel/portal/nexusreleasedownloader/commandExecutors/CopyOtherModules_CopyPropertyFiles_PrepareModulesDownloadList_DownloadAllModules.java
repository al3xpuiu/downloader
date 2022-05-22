package com.nextlevel.portal.nexusreleasedownloader.commandExecutors;

import com.nextlevel.portal.nexusreleasedownloader.commands.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Use this executor when you only have the oldReleaseVersion.
 */

@Component("CopyOtherModules_CopyPropertyFiles_PrepareModulesDownloadList_DownloadAllModules")
public class CopyOtherModules_CopyPropertyFiles_PrepareModulesDownloadList_DownloadAllModules implements CommandExecutor {

    private final Command copyOtherModulesCommand;
    private final Command copyPropertyFilesCommand;
    private final Command prepareModulesDownloadListCommand;
    private final Command downloadAllModulesCommand;

    @Autowired
    public CopyOtherModules_CopyPropertyFiles_PrepareModulesDownloadList_DownloadAllModules(
            @Qualifier("copyOtherModulesCommand") Command copyOtherModulesCommand,
            @Qualifier("copyPropertyFilesCommand") Command copyPropertyFilesCommand,
            @Qualifier("downloadAllModulesCommand") Command downloadAllModulesCommand,
            @Qualifier("prepareModulesDownloadListCommand") Command prepareModulesDownloadListCommand) {

        this.copyOtherModulesCommand = copyOtherModulesCommand;
        this.copyPropertyFilesCommand = copyPropertyFilesCommand;
        this.downloadAllModulesCommand = downloadAllModulesCommand;
        this.prepareModulesDownloadListCommand = prepareModulesDownloadListCommand;
    }

    @Override
    public void executeCommands() {
        copyOtherModulesCommand.execute();
        copyPropertyFilesCommand.execute();
        prepareModulesDownloadListCommand.execute();
        downloadAllModulesCommand.execute();

        System.out.println("Finished!");
        System.exit(0);
    }
}
