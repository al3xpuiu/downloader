package com.nextlevel.portal.nexusreleasedownloader.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ModulePostDownloadServiceImpl implements ModulePostDownloadService {

    private final String completeModuleListPath;

    @Autowired
    public ModulePostDownloadServiceImpl(@Value("${ModuleServiceImpl.fileDestinationPath}") String completeModuleListPath) {
        this.completeModuleListPath = completeModuleListPath;
    }

    @Override
    public void renameModule(Path source, Path target) {
        try {
            FileUtils.moveFile(source.toFile(), target.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Path> findAllThatEndIn(String suffix) {
        try (Stream<Path> completeModulePathsStream = Files.walk(Paths.get(completeModuleListPath), 1)) {
            return completeModulePathsStream
                    .peek(System.out::println)
                    .filter(p -> p.toString().contains(suffix))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public Path removeFromPath(Path path, String exp) {
        return Paths.get(path.toString().replace(exp, ""));
    }

    public Map<Path, String> pathMd5Map(List<Path> paths) {
        return paths
                .stream()
                .collect(Collectors.toMap(k -> k, this::readMd5, (v1, v2) -> v1));
    }

    private String readMd5(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
