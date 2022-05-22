package com.nextlevel.portal.nexusreleasedownloader.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;

public interface ModulePreDownloadService {

    Deque<String> collectModuleNames() throws IOException;
    void writeModulesToFile(Deque<String> modules, BufferedWriter bufferedWriter) throws IOException;
    Deque<Path> collectOtherModulesPath() throws IOException;
    void copyFiles(Deque<Path> filePaths) throws IOException;
    Deque<Path> collectPropertyFilesPath() throws IOException;

}
