//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xiaoxz.qixin.api.util;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpUtil {
    public HttpUtil() {
    }

    public static String post(String url, String content) throws Exception {
        return post((String)url, content, 4000, 5000, "application/json");
    }

    public static String post(String url, String content, String mimeType) throws Exception {
        return post((String)url, content, 4000, 5000, mimeType);
    }

    public static String post(String url, String content, int connectTimeout, int readTimeout, String mimeType) throws Exception {
        HttpURLConnection conn = null;
        HttpsURLConnection connHttps = null;

        String var8;
        try {
            URL consoleUrl = new URL(url);
            if (url.contains("https")) {
                connHttps = (HttpsURLConnection)consoleUrl.openConnection();
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init((KeyManager[])null, new TrustManager[]{new HttpUtil.TrustAnyTrustManager()}, new SecureRandom());
                connHttps.setSSLSocketFactory(sc.getSocketFactory());
                connHttps.setHostnameVerifier(new HttpUtil.TrustAnyHostnameVerifier());
                String var9 = post((HttpURLConnection)connHttps, content, connectTimeout, readTimeout, mimeType);
                return var9;
            }

            conn = (HttpURLConnection)consoleUrl.openConnection();
            var8 = post(conn, content, connectTimeout, readTimeout, mimeType);
        } catch (Exception var13) {
            throw var13;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

            if (connHttps != null) {
                connHttps.disconnect();
            }

        }

        return var8;
    }

    private static String post(HttpURLConnection connection, String params, int connectTimeout, int readTimeout, String mimeType) throws Exception {
        BufferedReader br = null;
        InputStreamReader inr = null;
        InputStream ins = null;
        OutputStreamWriter out = null;

        String var13;
        try {
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", mimeType);
            connection.setRequestProperty("Content-Type", mimeType);
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            connection.connect();
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.append(params);
            out.flush();
            ins = connection.getInputStream();
            String charset = "UTF-8";
            inr = new InputStreamReader(ins, charset);
            br = new BufferedReader(inr);
            String line = "";
            StringBuffer sb = new StringBuffer();

            do {
                sb.append(line);
                line = br.readLine();
            } while(line != null);

            int status = connection.getResponseCode();
            if (status != 200) {
                throw new RuntimeException(sb.toString());
            }

            var13 = sb.toString();
        } catch (Exception var22) {
            throw var22;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                if (inr != null) {
                    inr.close();
                }

                if (ins != null) {
                    ins.close();
                }

                if (out != null) {
                    out.close();
                }
            } catch (IOException var21) {
                var21.printStackTrace();
            }

        }

        return var13;
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        private TrustAnyHostnameVerifier() {
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        private TrustAnyTrustManager() {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
