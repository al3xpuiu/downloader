package com.nextlevel.portal.nexusreleasedownloader;

import com.nextlevel.portal.nexusreleasedownloader.commandExecutors.CommandExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NexusReleaseDownloaderApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(NexusReleaseDownloaderApplication.class, args);

        CommandExecutor moduleService = (CommandExecutor) applicationContext.getBean("activeCommandExecutor");
        moduleService.executeCommands();
    }
}
