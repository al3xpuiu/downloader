package com.nextlevel.portal.nexusreleasedownloader.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ModuleFactory implements Factory {

    private final String baseUrl;
    private final String version;

    @Autowired
    public ModuleFactory(@Value("${ModuleFactory.baseUrl}") String baseUrl, @Value("${ModuleFactory.version}") String version) {
        this.baseUrl = baseUrl;
        this.version = version;
    }

    public Module getModule(String moduleName, Scope scope, String fileExtension) {

        switch (scope) {
            case RELEASE:
                Module module = new Module();
                String url = baseUrl + "/" + moduleName + "/" + version + "/" + moduleName + "-" + version + fileExtension;
                module.setName(moduleName);
                module.setVersion(version);
                module.setUrl(url);
                module.setFileExtension(fileExtension);
                return module;
            default:
                throw new IllegalStateException("Scope " + scope + " is not supported");
        }
    }

    public enum Scope {
        RELEASE
    }
}
