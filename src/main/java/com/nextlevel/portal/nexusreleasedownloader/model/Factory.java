package com.nextlevel.portal.nexusreleasedownloader.model;

public interface Factory {

    Module getModule(String moduleName, ModuleFactory.Scope scope, String fileExtension);
}
