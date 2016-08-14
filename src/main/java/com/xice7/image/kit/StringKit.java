package com.xice7.image.kit;

import java.nio.charset.Charset;

/**
 * 字符串处理
 * @author mdc
 * @date 2016年8月14日
 */
public class StringKit {

    /**
     * Constructs a new <code>String</code> by decoding the specified array of bytes using the UTF-8 charset.
     *
     * @param bytes
     *            The bytes to be decoded into characters
     * @return A new <code>String</code> decoded from the specified array of bytes using the UTF-8 charset,
     *         or {@code null} if the input byte array was {@code null}.
     * @throws NullPointerException
     *             Thrown if {@link Charsets#UTF_8} is not initialized, which should never happen since it is
     *             required by the Java platform specification.
     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException
     */
    public static String newStringUtf8(final byte[] bytes) {
    	return bytes == null ? null : new String(bytes, Charset.forName("UTF-8"));
    }
    
    /**
     * Encodes the given string into a sequence of bytes using the UTF-8 charset, storing the result into a new byte
     * array.
     *
     * @param string
     *            the String to encode, may be {@code null}
     * @return encoded bytes, or {@code null} if the input string was {@code null}
     * @throws NullPointerException
     *             Thrown if {@link Charsets#UTF_8} is not initialized, which should never happen since it is
     *             required by the Java platform specification.
     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException
     * @see <a href="http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
     * @see #getBytesUnchecked(String, String)
     */
    public static byte[] getBytesUtf8(final String string) {
        if (string == null) {
            return null;
        }
        return string.getBytes(Charset.forName("UTF-8"));
    }
}
