package org.chris.study.cookbook.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileOperations {

    public static void fileCopyByteMapping(String sourcePath, String destPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        assert sourceFile.exists();
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        FileChannel sourceChannel = fis.getChannel();
        FileChannel destChannel = fos.getChannel();
        MappedByteBuffer buffer = sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, sourceChannel.size());
        destChannel.write(buffer);

        sourceChannel.close();
        destChannel.close();
    }

    public static void fileCopyChannel(String sourcePath, String destPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        assert sourceFile.exists();
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        FileChannel sourceChannel = fis.getChannel();
        FileChannel destChannel = fos.getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        sourceChannel.close();
        destChannel.close();
    }

    public static void fileCopyLegacy(String sourcePath, String destPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        assert sourceFile.exists();
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, length);
        }

        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        String sourcePath = "/Users/czhu30/sandbox/monster cage.txt";
        String destPath = "/Users/czhu30/sandbox/monster cage4.txt";
        fileCopyByteMapping(sourcePath, destPath);
    }

}
