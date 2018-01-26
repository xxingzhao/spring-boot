package com.xiaoxz.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程读取、写入文件
 * @author : xiaoxz
 * @Date: Created in 2018/1/24
 * @Modified by :
 **/
public class Reader implements Runnable{

    private static final int BYTE = 1024;
    private static final long NAVIGATE_ONE = -1L;
    private static final long ZERO = 0L;
    private static final long ONE = 1L;
    /**
     * 全局变量，供多线程访问
     */
    private static final AtomicLong segment = new AtomicLong(NAVIGATE_ONE);
    /**
     * 单个线程读取文件的大小
     */
    private final int segmentLength = 30 * BYTE * BYTE;

    /**
     * 原始文件
     */
    private  File source;
    /**
     * 目标文件
     */
    private File target;
    /**
     * 文件分割后的段数
     */
    private long segments;
    /**
     * 文件分割后，剩余大小
     */
    private long remains;

    public Reader(String sourcePath, String targetPath) {
        source = new File(sourcePath);
        target = new File(targetPath);
        try {
            if(!target.exists()) {
                target.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.remains = source.length() % segmentLength;
        if(remains == ZERO) {
            this.segments = source.length() / segmentLength;
        } else {
            this.segments = source.length() / segmentLength + ONE;
        }
    }

    @Override
    public void run() {
        RandomAccessFile reader = null;
        RandomAccessFile writer = null;
        try{
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入文件........");
            reader = new RandomAccessFile(source,"r");
            writer = new RandomAccessFile(target, "rw");
            long position = -1L;
            while((position = segment.incrementAndGet()) < segments) {
                final byte[] bytes = readBytes(reader, position);
                writeBtes(writer, bytes, position);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close(writer);
            close(reader);
        }
    }

    private void writeBtes(RandomAccessFile writer, byte[] bytes, long position) throws IOException {
        writer.seek(position * segmentLength);
        writer.write(bytes);
    }

    private byte[] readBytes(RandomAccessFile reader, long position) throws IOException {
        reader.seek(position * segmentLength);
        final byte[] bytes = new byte[getWriteLength(position)];
        reader.read(bytes);
        return bytes;
    }

    private int getWriteLength(long position) {
        if(position == segments + NAVIGATE_ONE && remains > ZERO) {
            return (int)remains;
        }
        return segmentLength;
    }

    private void close(Closeable closeable) {
        if(Objects.nonNull(closeable)) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
