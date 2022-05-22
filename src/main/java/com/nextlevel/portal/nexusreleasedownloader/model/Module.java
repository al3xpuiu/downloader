package com.nextlevel.portal.nexusreleasedownloader.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Module {

    private String name;
    private String version;
    private String url;
    private String fileExtension;

    Module() {
    }

    @Override
    public String toString() {
        return name + "-" + version + fileExtension;
    }
}
