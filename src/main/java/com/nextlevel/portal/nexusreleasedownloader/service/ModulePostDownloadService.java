package com.nextlevel.portal.nexusreleasedownloader.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface ModulePostDownloadService {
    void renameModule(Path source, Path target);
    List<Path> findAllThatEndIn(String suffix);
    Path removeFromPath(Path path, String exp);
    Map<Path, String> pathMd5Map(List<Path> paths);
}
