package com.nextlevel.portal.nexusreleasedownloader.config;

import com.nextlevel.portal.nexusreleasedownloader.commandExecutors.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private final ApplicationContext appContext;

    @Autowired
    public Configuration(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Bean
    public CommandExecutor activeCommandExecutor(@Value("${Configuration.commandExecutor}") String qualifier) {

        return (CommandExecutor) appContext.getBean(qualifier);
    }

}
