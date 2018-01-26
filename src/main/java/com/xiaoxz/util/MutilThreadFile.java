package com.xiaoxz.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.*;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/24
 * @Modified by :
 **/
public class MutilThreadFile {

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader("D:" + File.separator +"max.txt", "D:" + File.separator + "max_bak.txt");
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        ExecutorService service = new ThreadPoolExecutor(3, 10, 50, TimeUnit.MILLISECONDS, blockingQueue);
        for(int i = 0; i < 15; i++) {
            service.execute(reader);
        }

        service.shutdown();
    }


    public static void createMaxFile() throws IOException {
        File file = new File("D:" + File.separator + "max.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
        String content = "texttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttexttext";
        OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        for (int i = 0; i < 10000000; i++) {
            outputStream.write(content + "\r\n");
        }

    }
}
