package nlp;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;

public class POSTagger {

    private POSTaggerME tagger;
    private SentenceDetectorME sdetector;

    public POSTagger(String modelPath) {
        POSModel model = new POSModelLoader()
                .load(new File(modelPath));
        tagger = new POSTaggerME(model);

    }

    public POSSample POSTag(String sentence) {

            //Tokenizing the sentence using WhitespaceTokenizer class
            WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
            String[] tokens = whitespaceTokenizer.tokenize(sentence);

            //Generating tags
            String[] tags = tagger.tag(tokens);

            //Instantiating the POSSample class
            POSSample sample = new POSSample(tokens, tags);
            return sample;

    }



}
