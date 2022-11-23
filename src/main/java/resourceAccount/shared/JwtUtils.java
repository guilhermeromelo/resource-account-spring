package resourceAccount.shared;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

public class JwtUtils {

    public static String getUserLoginFromJwtToken(String jwtToken){
        String[] chunks = jwtToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        HashMap<String, String> tokenDecoded = JwtUtils.stringToHashMap(payload);
        return tokenDecoded.get("sub");
    }

    public static HashMap<String, String> stringToHashMap(String stringToParse){
        HashMap<String,String> result = new HashMap<String,String>();
        List<String> values = Arrays.stream(stringToParse.split(",")).toList();
        values.forEach((element) -> {
            String key = element.split(":")[0].replace("{","").replace("\"","");
            String value = element.split(":")[1].replace("}","").replace("\"","");
            result.put(key,value);
        });
        return result;
    }
}
