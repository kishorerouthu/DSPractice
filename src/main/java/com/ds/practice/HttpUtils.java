package com.ds.practice;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Kishore Routhu on 15/11/16 12:14 PM.
 */
public class HttpUtils {

    public static Map<String, Object> asMap(String urlencoded) throws UnsupportedEncodingException {
        return asMap(urlencoded, "UTF-8");
    }

    public static Map<String, Object> asMap(String urlencoded, String encoding) throws UnsupportedEncodingException {

        Map<String, Object> map = new LinkedHashMap();
        for (String keyValue : urlencoded.trim().split("&")) {

            String[] tokens = keyValue.trim().split("=");
            String key = tokens[0];
            String value = tokens.length == 1 ? null : URLDecoder.decode(tokens[1], encoding);

            String[] keys = key.split("\\.");
            Map<String, Object> pointer = map;

            for (int i = 0; i < keys.length - 1; i++) {

                String currentKey = keys[i];
                Map<String, Object> nested = (Map<String, Object>) pointer.get(keys[i]);

                if (nested == null) {
                    nested = new LinkedHashMap();
                }

                pointer.put(currentKey, nested);
                pointer = nested;
            }

            pointer.put(keys[keys.length - 1], value);
        }

        return map;
    }

    public static String asUrlEncodedData(Map<String, Object> map) throws UnsupportedEncodingException {
        return asUrlEncodedData(map,  "UTF-8");
    }

    public static String asUrlEncodedData(Map<String, Object> map, String encoding) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        buildEncodedData(sb, "", encoding, map);
        return sb.toString();
    }

    private static void buildEncodedData(StringBuffer sb, String prefix, String encoding, Map<String, Object> map)
            throws UnsupportedEncodingException {
        boolean first = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key = prefix.length() !=0 ? prefix + "." + key : key;
            if (value instanceof Map) {
                buildEncodedData(sb, key, encoding, (Map)value);
            } else {
                if (!first)
                    sb.append("&");
                sb.append(key + "=" + URLEncoder.encode(value.toString(), encoding));
                first = false;
            }
        }
    }

    public static Tuple.Two<String, String> extractParameter(String urlencoded, String paramName, String encoding) throws UnsupportedEncodingException {
        int startIndex = urlencoded.indexOf(paramName);
        String parameter = null;
        if (startIndex == -1)
            return new Tuple.Two(urlencoded, null);

        StringBuffer paramSb = new StringBuffer();
        StringBuffer encodedData = new StringBuffer();

        char[] chars = urlencoded.toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(!found && i >= startIndex) {
                if (c != '&') {
                    paramSb.append(c);
                    continue;
                }
                if (startIndex != 0)
                    encodedData.append(chars[i]);
                found = true;
            } else if(i != startIndex-1){
                encodedData.append(chars[i]);
            }
        }

        if (paramSb.length() != 0)
            parameter = URLDecoder.decode(paramSb.toString(), encoding);
        return new Tuple.Two(encodedData.toString(), parameter);
    }

    public static void main(String[] args) throws Exception {
        // id=7
            //    &name=Pablo+Mat%C3%ADas
        // &lastname=Gomez
        //&address.city=Rotterdam"

        String payload = "id=7&name=Pablo+Mat%C3%ADas&lastname=Gomez&githubUsername=pablomatiasgomez&" +
                "address.street=Coolsingel&address.number=42a&address.city=Rotterdam";
        Tuple.Two<String, String> name = extractParameter(payload, "id", "UTF-8");
        System.out.println("V1 : " + name.v1);
        System.out.println("V2 : " + name.v2);
        Tuple.Two<String, String> name1 = extractParameter(payload, "name", "UTF-8");
        System.out.println("V1 : " + name1.v1);
        System.out.println("V2 : " + name1.v2);
        Tuple.Two<String, String> name2 = extractParameter(payload, "address.city", "UTF-8");
        System.out.println("V1 : " + name2.v1);
        System.out.println("V2 : " + name2.v2);
    }

   /* public static void main(String[] args) throws Exception {
        String payload = "Body=%22Hello+Test+WM%22&From=%22%2B441480706178%22&To=%22%2B4407712606069%22&param1=test";
        System.out.println(payload);
        Map<String, Object> map = asMap(payload);
        System.out.println(map);
        map.remove("Body");
        System.out.println(asUrlEncodedData(map));
    }*/
}
