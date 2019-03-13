package ug.co.sampletracker.app.utils.security;

import java.security.cert.CertificateException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 9/4/2018.
 */
public class SecurityHelper {

    private static final String VENDOR_CODE = "test";//"PEGPAY_APP";
    private static final String API_PASSWORD = "T3rr1613";//"T3rr1613";
    private static final String API_SECRET_KEY = "T3rr16132016";//"T3rr16132016";
    private static final String ENCRYPTION_ALGORITHM = "HmacSHA256";

    private static final String MERCHANT_CODE = /*"100097";*/ "100022";

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String flexipayPassword() {
        return hmacDigest(API_PASSWORD);
    }

    public static String hmacDigest(String msg) {

        String algo = ENCRYPTION_ALGORITHM;

        String digest = null;
        try {

            SecretKeySpec key = new SecretKeySpec((API_SECRET_KEY).getBytes("UTF-8"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();

            for (byte aByte : bytes) {
                String hex = Integer.toHexString(0xFF & aByte);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }

            digest = hash.toString();
        } catch (Exception ee){
            ee.printStackTrace();
        }
        return digest;
    }

    public static String vendorCode() {
        return VENDOR_CODE;
    }

    public static String merchantCode() {
        return MERCHANT_CODE;
    }
}
