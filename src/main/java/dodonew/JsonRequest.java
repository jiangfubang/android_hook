package dodonew;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonRequest {

    public static final String BASE_APPEND = "sdlkjsdljf0j2fsjk";
    public static final String BASE_DES_IV = "32028092";
    public static final String BASE_DES_KEY = "65102933";


    public JSONObject addRequestMap(Map<String, String> addMap, int a) {
        String time = System.currentTimeMillis() + "";
        if (addMap == null) {
            addMap = new HashMap<>();
        }
        addMap.put("timeStamp", time);
        String code = RequestUtil.paraMap(addMap, BASE_APPEND, "sign");
        String encrypt = RequestUtil.encodeDesMap(code, BASE_DES_KEY, BASE_DES_IV);
        JSONObject obj = new JSONObject();
        try {
            obj.put("Encrypt", encrypt);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Map<String, String> addMap = new HashMap();
        addMap.put("loginImei", "Androidnull");
        addMap.put("equtype", "ANDROID");
        addMap.put("userPwd", "777777");
        addMap.put("username", "17777777777");
        int a = 0;
        JSONObject  Encrypt = new JsonRequest().addRequestMap(addMap, a);
        System.out.println(Encrypt);
    }
}
