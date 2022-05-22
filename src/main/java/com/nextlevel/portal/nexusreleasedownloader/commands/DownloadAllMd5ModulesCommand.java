package com.nextlevel.portal.nexusreleasedownloader.commands;

import com.nextlevel.portal.nexusreleasedownloader.model.Module;
import com.nextlevel.portal.nexusreleasedownloader.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Deque;

@Component("downloadAllMd5Modules")
public class DownloadAllMd5ModulesCommand implements Command {

    private final ModuleService moduleService;
    private final String moduleListPath;

    @Autowired
    public DownloadAllMd5ModulesCommand(ModuleService moduleService,
                                        @Value("${ModuleServiceImpl.modulesPath}") String moduleListPath) {
        this.moduleService = moduleService;
        this.moduleListPath = moduleListPath;
    }

    @Override
    public void execute() {
        Deque<String> moduleNames = moduleService.getModulesFromFile(moduleListPath);
        Deque<Module> modules = moduleService.createModuleObjects(moduleNames, ".zip.md5");
        moduleService.downloadModules(modules);
    }
}
