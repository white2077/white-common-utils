package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    private FileUtils() {
    }

    public static File createFile(String filePath) throws IOException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath is null or blank");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }

    public static String createFolder(String folderPath) {
        if (folderPath == null || folderPath.isBlank()) {
            throw new IllegalArgumentException("folderPath is null or blank");
        }
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder.getAbsolutePath();
    }

    public static void writeInputStreamToFile(String filePath, InputStream inputStream) throws IOException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath is null or blank");
        }
        if (inputStream == null) {
            throw new IllegalArgumentException("input stream is null");
        }
        File file = createFile(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
        }
    }

    public static InputStream getInputStreamFromFile(String filePath) throws FileNotFoundException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath is null or blank");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("file is not exist: " + filePath);
        }
        return new FileInputStream(file);
    }

    public static void changeInputStream(String filePath, InputStream newInputStream) throws IOException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("filePath is null or blank");
        }
        if (newInputStream == null) {
            throw new IllegalArgumentException("input stream is null");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("can not change file content " + filePath);
        }
        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = newInputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
        }
    }

    public static boolean deleteFile(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            return false;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return true;
        }
        return file.delete();
    }

}
