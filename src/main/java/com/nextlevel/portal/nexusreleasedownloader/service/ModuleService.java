package com.nextlevel.portal.nexusreleasedownloader.service;

import com.nextlevel.portal.nexusreleasedownloader.model.Module;

import java.util.Deque;

public interface ModuleService {

    void downloadModule(Module module);
    Deque<String> getModulesFromFile(String moduleListUrl);
    Deque<Module> createModuleObjects(Deque<String> modules, String fileExtension);
    void downloadModules(Deque<Module> modules);
}
