package com.nextlevel.portal.nexusreleasedownloader.commandExecutors;

import com.nextlevel.portal.nexusreleasedownloader.commands.CopyPropertyFilesCommand;
import org.springframework.stereotype.Component;


/**
 * Use this executor when you have more then one release to make (one for the Q and one for the T), but you don't want
 * to download twice the same thing.
 */

@Component("CopyPropertyFiles_")
public class CopyPropertyFiles_ implements CommandExecutor {

    private final CopyPropertyFilesCommand copyPropertyFilesCommand;

    public CopyPropertyFiles_(CopyPropertyFilesCommand copyPropertyFilesCommand) {
        this.copyPropertyFilesCommand = copyPropertyFilesCommand;
    }

    @Override
    public void executeCommands() {
        copyPropertyFilesCommand.execute();

        System.out.println("Finished!");
        System.exit(0);
    }
}
