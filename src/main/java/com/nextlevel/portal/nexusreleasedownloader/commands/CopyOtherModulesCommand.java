package com.nextlevel.portal.nexusreleasedownloader.commands;

import com.nextlevel.portal.nexusreleasedownloader.service.ModulePreDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;

@Component("copyOtherModulesCommand")
public class CopyOtherModulesCommand implements Command {

    private final ModulePreDownloadService modulePreDownloadService;

    @Autowired
    public CopyOtherModulesCommand(ModulePreDownloadService modulePreDownloadService) {
        this.modulePreDownloadService = modulePreDownloadService;
    }

    @Override
    public void execute() {
        System.out.println("Starting to copy the modules that are not in the nexus...");
        try {

            Deque<Path> modules = modulePreDownloadService.collectOtherModulesPath();
            modulePreDownloadService.copyFiles(modules);

            System.out.println("I've finished copying the modules that are not in the nexus!");
            Thread.sleep(5000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Could not copy the modules that are not in the nexus!  " + e.getMessage());
            e.printStackTrace();
        }
    }
}
