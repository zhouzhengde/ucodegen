package ${root.basePackage}.common.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author ucodegen
 */
public final class SHA1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SHA1.class);
    private static final String ALGORITHM_NAME = "SHA-1";

    private SHA1() {
    }

    /**
     * SHA-1加密
     *
     * @param args
     * @return
     */
    public static String encrypt(String[] args) {
        Arrays.sort(args);
        String signToken = Helper.toString(args);
        return encrypt(signToken);
    }

    /**
     * SHA-1加密
     *
     * @param bts
     * @return
     */
    public static String encrypt(byte[] bts) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_NAME);
            digest.update(bts);
            return new String(Hex.encodeHex(digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("[SHA Encrypt]", e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * SHA-1加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        try {
            return encrypt(content.getBytes(Constants.ENCODING));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("[SHA Encrypt UnsupportedEncodingException]", e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * SHA 加密
     *
     * @param keyItems
     * @return
     */
    public static String encrypt(KeyItem[] keyItems) {
        try {
            Map<String, String> map = new HashedMap();
            String[] attrs = new String[keyItems.length];
            for (int i = 0; i < keyItems.length; i++) {
                attrs[i] = keyItems[i].getKey();
                map.put(attrs[i], keyItems[i].getValue());
            }
            Arrays.sort(attrs);
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < attrs.length; i++) {
                content.append(attrs[i] + "=" + map.get(attrs[i]) + "&");
            }
            LOGGER.info("SHA1 encrypt content:" + content);
            return SHA1.encrypt(content.toString().substring(0, content.toString().length() - 1));
        } catch (Exception e) {
            LOGGER.error("[SHA Encrypt]", e);
            throw new IllegalStateException(e);
        }
    }

    public static class KeyItem {

        private String key;

        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public KeyItem(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
