//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xiaoxz.qixin.api.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    private static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static MessageDigest messagedigest = null;

    public Md5Utils() {
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(MapMode.READ_ONLY, 0L, file.length());
        messagedigest.update(byteBuffer);
        in.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getObjectMd5String(Object obj) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();

        try {
            ObjectOutputStream out = new ObjectOutputStream(bo);
            out.writeObject(obj);
            out.flush();
            byte[] bytes = bo.toByteArray();
            return getMD5String(bytes);
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String getUtf8MD5String(String s) {
        try {
            byte[] bytes = s.getBytes("utf-8");
            return getMD5String(bytes);
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;

        for(int l = m; l < k; ++l) {
            appendHexPair(bytes[l], stringbuffer);
        }

        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 240) >> 4];
        char c1 = hexDigits[bt & 15];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var1) {
            var1.printStackTrace();
        }

    }
}
