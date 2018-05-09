package nlp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class NLPConfigLoader {


    public static NLPConfig loadFromFile(String path){
        ObjectMapper mapper = new ObjectMapper();
        NLPConfig config = null;
        try {
            config = mapper.readValue(new File(path), NLPConfig.class);
            return config;
        } catch (IOException e) {
           e.printStackTrace();
        }
        return config;
    }
}
