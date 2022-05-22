package com.nextlevel.portal.nexusreleasedownloader.commands;

import com.nextlevel.portal.nexusreleasedownloader.service.ModulePostDownloadService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Component("renameModulesCommand")
public class RenameModulesCommand implements Command {

    private final ModulePostDownloadService postDownloadService;

    public RenameModulesCommand(ModulePostDownloadService postDownloadService) {
        this.postDownloadService = postDownloadService;
    }

    @Override
    public void execute() {
        List<Path> md5Paths = postDownloadService.findAllThatEndIn(".zip.md5");
        Map<Path, String> pathMd5Map = postDownloadService.pathMd5Map(md5Paths);
        for (Path path : md5Paths) {
            renameFiles(path, pathMd5Map);
        }
    }

    private void renameFiles(Path path, Map<Path, String> pathMd5Map) {
        try {
            if (Files.exists(path)) {
                FileUtils.moveFile(postDownloadService.removeFromPath(path, ".md5").toFile(),
                        new File(postDownloadService.removeFromPath(path, ".zip.md5").toString() + "_" + pathMd5Map.get(path) + ".zip"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
