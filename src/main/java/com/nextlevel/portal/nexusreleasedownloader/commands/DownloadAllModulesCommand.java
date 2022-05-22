package com.nextlevel.portal.nexusreleasedownloader.commands;

import com.nextlevel.portal.nexusreleasedownloader.model.Module;
import com.nextlevel.portal.nexusreleasedownloader.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Deque;

@Component("downloadAllModulesCommand")
public class DownloadAllModulesCommand implements Command {

    private final ModuleService moduleService;
    private final String moduleListPath;
    private final String moduleSuffix;

    @Autowired
    public DownloadAllModulesCommand(ModuleService moduleService,
                                     @Value("${ModuleServiceImpl.modulesPath}") String moduleListPath,
                                     @Value("${ModuleServiceImpl.moduleSuffix:.zip}") String moduleSuffix) {
        this.moduleService = moduleService;
        this.moduleListPath = moduleListPath;
        this.moduleSuffix = moduleSuffix;
    }

    @Override
    public void execute() {
        Deque<String> moduleNames = moduleService.getModulesFromFile(moduleListPath);
        Deque<Module> modules = moduleService.createModuleObjects(moduleNames, moduleSuffix);
        moduleService.downloadModules(modules);
    }
}
