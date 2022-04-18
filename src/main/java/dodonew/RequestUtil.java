package dodonew;


import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class RequestUtil {
    public static Map<String, String> sortMapByKey(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> map = new TreeMap<>(new Comparator<String>() { // from class: com.dodonew.online.http.RequestUtil.1
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);
            }
        });
        map.putAll(oriMap);
        return map;
    }


    public static String paraMap(Map<String, String> addMap, String append, String sign) {
        try {
            Set<String> keyset = addMap.keySet();
            StringBuilder builder = new StringBuilder();
            List<String> list = new ArrayList<>();
            for (String keyName : keyset) {
                list.add(keyName + "=" + addMap.get(keyName));
            }
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                builder.append("&");
            }
            builder.append("key=" + append);
            String checkCode = Utils.md5(builder.toString()).toUpperCase();
            addMap.put("sign", checkCode);
            String result = new Gson().toJson(sortMapByKey(addMap));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encodeDesMap(String data, String desKey, String desIV) {
        try {
            DesSecurity ds = new DesSecurity(desKey, desIV);
            return ds.encrypt64(data.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}