package org.marina.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileSystemService {
    public static String APPLICATION_FOLDER = ".supermarket-application";
    private static final String USER_FOLDER = System.getProperty("user.home");

    public static Path getPathToFile(String... path) {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER).resolve(Paths.get(".", path));
    }

    public static Path getApplicationHomeFolder(){
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    public static void initDirectory() {
        Path applicationHomePath = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
}
