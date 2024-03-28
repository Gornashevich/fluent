package utils;

import config.Environment;

public class EnvDeterminer {
    public static String getEnvironment() {
        String env = System.getProperty("testEnv");

        if (env == null) {
            return Environment.QA.getBaseUrl();
        }

        switch (env.toUpperCase()) {
            case "DEV":
                env = Environment.DEV.getBaseUrl();
                break;
            case "QA":
                env = Environment.QA.getBaseUrl();
                break;
            case "STAGE":
                env = Environment.STAGE.getBaseUrl();
                break;
            default:
                throw new IllegalArgumentException("Unsupported environment type");

        }

        return env.toUpperCase();
    }
}