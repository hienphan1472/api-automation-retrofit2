package com.automation.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration _instance;

    private Properties configProps = new Properties();

    private Logger logger = LoggerFactory.getLogger(Configuration.class);

    public void loadTestProperties() throws Exception {

        // Check if we have overridden, if so use that path
        String configFile = System.getProperty("configFile");

        if (configFile != null) {
            logger.info("Loading configuration from configuration: " + configFile);
            InputStream in = new FileInputStream(new File(configFile));
            configProps.load(in);
        } else {
            logger.info("Loading configuration from local.properties on classpath");
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("local.properties");
            configProps.load(in);
        }
    }

    public String getTestProperty(String property) {
        String result = configProps.getProperty(property);
        if (result == null) {
            throw new RuntimeException("Could not resolve config key " + property + ". Please check it is defined");
        }
        return result;
    }

    public Configuration() {
        try {
            loadTestProperties();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Configuration get() {

        if (_instance == null) {
            _instance = new Configuration();
        }

        return _instance;

    }

}
