package com.nextlevel.portal.nexusreleasedownloader.service;

import com.nextlevel.portal.nexusreleasedownloader.model.Factory;
import com.nextlevel.portal.nexusreleasedownloader.model.Module;
import com.nextlevel.portal.nexusreleasedownloader.model.ModuleFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("ModuleServiceImpl")
public class ModuleServiceImpl implements ModuleService {

    private final Factory factory;
    private final ModuleFactory.Scope scope;
    private final String fileDestinationPath;
    private final String errorFilePath;

    @Autowired
    public ModuleServiceImpl(Factory factory, @Value("${ModuleServiceImpl.scope}") ModuleFactory.Scope scope,
                             @Value("${ModuleServiceImpl.fileDestinationPath}") String fileDestinationPath,
                             @Value(("${ModuleServiceImpl.errorFilePath}")) String errorFilePath) {
        this.factory = factory;
        this.scope = scope;
        this.fileDestinationPath = fileDestinationPath;
        this.errorFilePath = errorFilePath;
    }

    @Override
    public Deque<String> getModulesFromFile(String moduleListUrl) {
        try (Stream<String> moduleListUrlStream = Files.lines(Paths.get(moduleListUrl))) {
            return moduleListUrlStream
                    .collect(Collectors.toCollection(ArrayDeque::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayDeque<>();
    }

    @Override
    public Deque<Module> createModuleObjects(Deque<String> modules, String fileExtension) {
        Deque<Module> createdModules = new ArrayDeque<>();
        while (!modules.isEmpty()) {
            Module module = factory.getModule(modules.pop(), scope, fileExtension);
            System.out.println("Created module: " + module);
            createdModules.add(module);
        }
        return createdModules;
    }

    @Override
    public void downloadModules(Deque<Module> modules) {
        while (!modules.isEmpty()) {
            System.out.println("Downloading module: " + modules.peek());
            downloadModule(modules.pop());
        }
    }

    @Override
    public void downloadModule(Module module) {
        try {
            if (!Files.exists(Paths.get(fileDestinationPath + "\\" + module.getName() + "-" + module.getVersion() + module.getFileExtension()))) {
                FileUtils.copyURLToFile(new URL(module.getUrl()), new File(fileDestinationPath + "\\" + module.getName() + "-" + module.getVersion() + module.getFileExtension()));
                System.out.println("Successfully downloaded module: " + module);
            } else {
                System.out.println("Module already exists: " + module);
            }
        } catch (Exception e) {
            System.out.println("Error downloading module: " + module + ".\\n " + e.getMessage());
            writeError(module);
        }
    }

    private void writeError(Module module) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(errorFilePath), StandardOpenOption.APPEND)) {
            writer.write(module.getUrl());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
