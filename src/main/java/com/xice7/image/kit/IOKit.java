package com.xice7.image.kit;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author mdc
 * @date 2016年8月14日
 */
public class IOKit {

	public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}
