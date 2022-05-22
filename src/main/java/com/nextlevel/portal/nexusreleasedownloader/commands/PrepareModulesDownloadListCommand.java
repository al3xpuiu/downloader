package com.nextlevel.portal.nexusreleasedownloader.commands;

import com.nextlevel.portal.nexusreleasedownloader.service.ModulePreDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Deque;

@Component("prepareModulesDownloadListCommand")
public class PrepareModulesDownloadListCommand implements Command {

    private final ModulePreDownloadService modulePreDownloadService;
    private final String moduleListUrl;

    @Autowired
    public PrepareModulesDownloadListCommand(ModulePreDownloadService modulePreDownloadService,
                                             @Value("${ModuleServiceImpl.modulesPath}") String moduleListPath) {
        this.modulePreDownloadService = modulePreDownloadService;
        this.moduleListUrl = moduleListPath;
    }

    @Override
    public void execute() {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(moduleListUrl))) {

            System.out.println("Preparing download list....");
            Deque<String> modules = modulePreDownloadService.collectModuleNames();
            modulePreDownloadService.writeModulesToFile(modules, bufferedWriter);
            System.out.println("Download list prepared!");

        } catch (IOException e) {
            System.out.println("Could not copy modules in the ModulePreDownloadServiceImpl. " + e.getMessage());
            e.printStackTrace();
        }
    }
}
