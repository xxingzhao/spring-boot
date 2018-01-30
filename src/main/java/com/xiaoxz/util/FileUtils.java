package com.xiaoxz.util;

import java.io.*;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/29
 * @Modified by :
 **/
public class FileUtils {


    /**
     * 将内容写到文件里面
     * @param filePath
     * @param fileName
     * @param content
     */
    public static void writeToFile(String filePath, String fileName, String content) {
        File file = new File(filePath + fileName);
        OutputStreamWriter writer = null;
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            writer = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param filePath
     * @param fileName
     * @param content
     */
    public static void writeToFileByByte(String filePath, String fileName, String content) {
        File file = new File(filePath + fileName);
        OutputStream outputStream = null;
        ByteArrayOutputStream baos = null;
        try{
            outputStream = new FileOutputStream(file, true);
            baos = new ByteArrayOutputStream(1024);
            baos.write(content.getBytes());
            outputStream.write(baos.toByteArray());
        } catch(Exception e) {
            try {
                if(baos != null) {
                    baos.close();
                }
                if(outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
