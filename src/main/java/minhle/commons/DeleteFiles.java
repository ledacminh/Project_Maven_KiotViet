package minhle.commons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DeleteFiles {
    public static void deleteFileInFolder(String path) {
        File files = new File(path);
        File[] listOfFiles = files.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                boolean isDeleted = file.delete();
                if (!isDeleted) {
                    throw new RuntimeException("[DeleteFiles][deleteFileInFolder] Deleted unsuccessfully");
                }
            }
        }
    }

    public static void cleanDirectory(String path) {
        File directory = new File(path);
        try {
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            throw new RuntimeException("[DeleteFiles][cleanDirectory] Cleaned unsuccessfully");

        }
    }
    public static void deleteFolder(String path) {
        File index = new File(path);
        if (!index.exists()) {
            boolean isCreated = index.mkdir();
        } else {
            boolean isDeleted = index.delete();
            if (!index.exists()) {
                boolean isCreated = index.mkdir();
            }
        }
    }
}
