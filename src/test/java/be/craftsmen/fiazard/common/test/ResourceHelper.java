package be.craftsmen.fiazard.common.test;

import com.google.common.io.Resources;

import java.io.File;

public class ResourceHelper {
    public static String resourceFilePath(final String resourceClassPathLocation) {
        try {
            return new File(Resources.getResource(resourceClassPathLocation).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}