package softwareplant.reportservice;

import org.springframework.stereotype.Component;

@Component
public class UrlUtils {

    static public String getIdFromUrl(String url) {
        String[] tokens = url.split("/");
        return tokens[tokens.length - 1];
    }
}
