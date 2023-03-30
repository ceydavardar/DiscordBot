package com.ceydavardar.discordBot.configuration.config;

import com.golub.golubBot.configuration.Configuration;
import com.golub.golubBot.configuration.ConfigurationProvider;
import com.golub.golubBot.configuration.Resource;
import com.golub.golubBot.configuration.YamlConfiguration;

import java.io.IOException;

public class ConfigHandler {

    private final ConfigContainer configContainer;
    private final Resource configResource;
    private final Configuration configuration;

    public ConfigHandler(Resource configResource) {

        this.configResource = configResource;

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configResource.getFile());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.configContainer = new ConfigContainer(configuration);

    }

    public ConfigContainer getConfigContainer() {
        return configContainer;
    }

    public Resource getConfigResource() {
        return configResource;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}