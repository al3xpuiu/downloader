package com.nextlevel.portal.nexusreleasedownloader.commandExecutors;

import com.nextlevel.portal.nexusreleasedownloader.commands.RenameModulesCommand;
import org.springframework.stereotype.Component;

@Component("RenameModulesCommand_")
public class RenameModulesCommand_ implements CommandExecutor {
    private final RenameModulesCommand renameModulesCommand;

    public RenameModulesCommand_(RenameModulesCommand renameModulesCommand) {
        this.renameModulesCommand = renameModulesCommand;
    }

    @Override
    public void executeCommands() {
        this.renameModulesCommand.execute();
        System.out.println("Finished!");
        System.exit(0);
    }
}
