package com.xiaojukeji.carrera.pproxy.config;

import com.xiaojukeji.carrera.config.ConfigurationValidator;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.yaml.snakeyaml.Yaml;


public class ConfigurationLoader {
    public static <T extends ConfigurationValidator> T newConfig(String configFile, Class<T> clz) throws Exception {
        T config;
        Yaml yaml = new Yaml();
        try (InputStream in = Files.newInputStream(Paths.get(configFile))) {
            config = yaml.loadAs(in, clz);
        }
        if (!config.validate()) {
            throw new Exception("invalid config.");
        }
        return config;
    }
}