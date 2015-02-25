package be.craftsmen.fiazard.common.test;

import java.io.File;

import com.google.common.io.Resources;

public class ResourceHelper {
    public static String resourceFilePath(final String resourceClassPathLocation) {
        try {
            return new File(Resources.getResource(resourceClassPathLocation).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}