package nlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.File;
import java.io.IOException;

public class SentenceSplitter {
    private SentenceDetectorME sdetector;


    public SentenceSplitter(String modelPath) {
        SentenceModel sentenceModel = null;
        try {
            sentenceModel = new SentenceModel(new File(modelPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sdetector = new SentenceDetectorME(sentenceModel);
    }

    public String[] splitSentences(String message){
        String sentences[] = sdetector.sentDetect(message);
        return sentences;
    }
}
