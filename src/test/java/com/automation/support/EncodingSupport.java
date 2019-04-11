package com.automation.support;

import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.Base64;

public class EncodingSupport {

    private static final String REGEX_VALIDATE_BASE64 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";

    public static String base64Encode(final String source)
    {
        if ( StringUtils.isEmpty(source) )
        {
            return null;
        }

        return Base64.getEncoder().encodeToString(source.getBytes(Charset.forName("UTF-8")));
    }


}
