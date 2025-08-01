package com.island.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.InputStream;

public class ObjectLoaderUtil {

    public static final String CONFIG_FOLDER = "com/island/configs/";

    public static ObjectsParametersConfig loadConfig(String configName) {
        try(InputStream inputStream = ObjectsParametersConfig.class.getClassLoader().getResourceAsStream(CONFIG_FOLDER + configName)) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(inputStream, ObjectsParametersConfig.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config " + configName, e);
        }
    }
}
