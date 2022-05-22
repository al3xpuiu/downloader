package com.nextlevel.portal.nexusreleasedownloader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("ModulePreDownloadServiceImpl")
public class ModulePreDownloadServiceImpl implements ModulePreDownloadService {

    private final String completeModuleListPath;
    private final String fileDestinationPath;
    private final String version;
    private final String propertyFilesPath;

    @Autowired
    public ModulePreDownloadServiceImpl(@Value("${ModulePreDownloadServiceImpl.completeModuleListPath}") String completeModuleListPath,
                                        @Value("${ModuleServiceImpl.fileDestinationPath}") String fileDestinationPath,
                                        @Value("${ModulePreDownloadServiceImpl.versionOlderRelease}") String version,
                                        @Value("${ModulePreDownloadServiceImpl.propertyFilesPath}") String propertyFilesPath) {
        this.completeModuleListPath = completeModuleListPath;
        this.fileDestinationPath = fileDestinationPath;
        this.version = version;
        this.propertyFilesPath = propertyFilesPath;
    }

    @Override
    public Deque<String> collectModuleNames() throws IOException {
        try (Stream<Path> completeModulePathsStream = Files.walk(Paths.get(completeModuleListPath))) {
            return completeModulePathsStream
                    .peek(System.out::println)
                    .filter(p -> p.toString().contains(version))
                    .filter(p -> !p.toString().contains(".properties"))
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .map(s -> s.replace("-" + version + ".zip", ""))
                    .distinct()
                    .collect(Collectors.toCollection(ArrayDeque::new));
        }
    }

    @Override
    public void writeModulesToFile(Deque<String> modules, BufferedWriter bufferedWriter) throws IOException {
        while (!modules.isEmpty()) {
            System.out.println(modules.peek());
            bufferedWriter.write(modules.pop());
            bufferedWriter.newLine();
        }
    }

    @Override
    public Deque<Path> collectOtherModulesPath() throws IOException {
        try (Stream<Path> completeModulePathsStream = Files.walk(Paths.get(completeModuleListPath))) {
            return completeModulePathsStream
                    .filter(p -> !p.toString().contains("-" + version))
                    .filter(p -> !p.toString().contains(".properties"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toCollection(ArrayDeque::new));
        }
    }

    @Override
    public void copyFiles(Deque<Path> filePaths) throws IOException {
        while (!filePaths.isEmpty()) {
            System.out.println("Copying file: " + filePaths.peek());
            Path sourcePath = filePaths.pop();
            String moduleName = sourcePath.getFileName().toString();
            Files.copy(sourcePath, Paths.get(fileDestinationPath + "/" + moduleName));
        }
    }

    @Override
    public Deque<Path> collectPropertyFilesPath() throws IOException {
        try (Stream<Path> propertyFilesPathStream = Files.walk(Paths.get(propertyFilesPath))) {
            return propertyFilesPathStream
                    .filter(p -> !p.toString().contains("-" + version))
                    .filter(p -> p.toString().contains(".properties"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toCollection(ArrayDeque::new));
        }
    }
}
