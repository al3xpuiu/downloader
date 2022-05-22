package com.nextlevel.portal.nexusreleasedownloader.commands;

import com.nextlevel.portal.nexusreleasedownloader.service.ModulePreDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;

@Component("copyPropertyFilesCommand")
public class CopyPropertyFilesCommand implements Command {

    private final ModulePreDownloadService modulePreDownloadService;

    @Autowired
    public CopyPropertyFilesCommand(ModulePreDownloadService modulePreDownloadService) {
        this.modulePreDownloadService = modulePreDownloadService;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Trying to copy the property files...");
            Deque<Path> modules = modulePreDownloadService.collectPropertyFilesPath();
            System.out.println(modules);
            modulePreDownloadService.copyFiles(modules);

        } catch (IOException e) {
            System.out.println("Could not copy modules in the ModulePreDownloadServiceImpl. " + e.getMessage());
            e.printStackTrace();
        }
    }
}
