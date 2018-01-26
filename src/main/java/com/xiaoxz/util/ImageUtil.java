package com.xiaoxz.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * 图片处理工具类
 * @author : xiaoxz
 * @Date: Created in 2018/1/26
 * @Modified by :
 **/
public class ImageUtil {


    /**
     * 将图片转化为字节数组，并Base64编码
     * @param filePath
     * @return
     */
    public static String imageToBase64(String filePath) {
        File file = new File(filePath);
        InputStream in = null;
        byte[] data = null;
        try {
             in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(data);
    }

    /**
     * 对字节数组字符串BASE64解码，并生成图片
     * @param base64
     * @param imagePath
     * @param imageName
     * @return
     * @throws IOException
     */
    public static boolean base64ToImageFile(String base64, String imagePath, String imageName) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] data = null;
        OutputStream out = null;
        try {
            data = decoder.decodeBuffer(base64);
            File file = new File(imagePath  + imageName);
            out = new FileOutputStream(file);
            out.write(data);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.close();
            }
        }
        return false;
    }

    /**
     * BASE64编码字符转化为InputStream
     * @param base64
     * @return
     */
    public static InputStream base64ToInputStream(String base64) {
        if(Objects.isNull(base64)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] data = decoder.decodeBuffer(base64);
            InputStream inputStream = new ByteArrayInputStream(data);
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将网络图片进行BASE64 编码
     * @param imageUrl
     * @return
     */
    public static String imageUrlToBase64(String imageUrl) {
        ByteArrayOutputStream outputStream = null;
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            image.getType();
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String base64 = base64Encoder.encode(outputStream.toByteArray());
        return base64;
    }

    public static byte[] imageUrlToBytes(String imageUrl) {
        ByteArrayOutputStream outputStream = null;
        try {
            URL url = new URL(imageUrl);
            BufferedImage bufferedImage = ImageIO.read(url);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream.toByteArray();
    }

    public static void main(String[] args) throws IOException {
        String imageUrl = "http://img.taopic.com/uploads/allimg/140729/240450-140HZP45790.jpg";
        String base64 = imageUrlToBase64(imageUrl);
        base64ToImageFile(base64, "C:\\Users\\Administrator\\Desktop\\图片\\新行驶证\\", "网络图片.jpg");
        System.out.println(base64);
    }
}
