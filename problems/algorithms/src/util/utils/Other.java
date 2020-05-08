package util.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;

public class Other {
    public static String getPathToProject() {
        return FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    }

    public static String getPathToCurrentFolder(Class c, String src) throws IOException {
        String internalPath = c.getName().replace(".", File.separator);
        String externalPath = System.getProperty("user.dir") + File.separator + "problems/algorithms/" + src;
        return externalPath + File.separator + internalPath.substring(0, internalPath.lastIndexOf(File.separator));
    }

    public static DataInputStream getReader(Class c, String fileName) throws IOException {
        String file = getPathToCurrentFolder(c, "test") + "/" + fileName;
        return new DataInputStream(new FileInputStream(file));
    }
}
