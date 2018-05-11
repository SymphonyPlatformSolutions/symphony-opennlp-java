package nlp;

import com.fasterxml.jackson.databind.ObjectMapper;
import nlp.model.Action;
import nlp.model.POSRequirement;
import nlp.model.Pattern;
import nlp.model.PatternList;
import opennlp.tools.postag.POSSample;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class NLPService {

    private List<Pattern> patterns;
    private POSTagger tagger;
    private SentenceSplitter sentenceSplitter;

    public NLPService(NLPConfig config) {
        //patterns = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            patterns = mapper.readValue(new File(config.getPatternsFile()), PatternList.class);
        } catch (IOException e) {
            //logger.error("Error reading json config file", e);
        }
        tagger = new POSTagger(config.getPOSModelPath());
        sentenceSplitter = new SentenceSplitter(config.getSentenceSplitterModelPath());
    }

    public List<Action> match(String message){
        List<Action> actions = new ArrayList<>();
        String[] sentences = sentenceSplitter.splitSentences(message);

        for (String sentence: sentences) {
            POSSample sample= tagger.POSTag(sentence);
            for (Pattern pattern: patterns) {
                Iterator<POSRequirement> itr = pattern.getPOSRequirementList().iterator();
                POSRequirement currentPOSReq = itr.next();
                Map<String, String> parameters = new HashMap<>();
                boolean match = false;
                for (int i = 0; i < sample.getTags().length; i++) {

                    if((sample.getTags()[i].contains(currentPOSReq.getPOS())||currentPOSReq.getPOS().equals("*")) && (currentPOSReq.getOptions()==null || currentPOSReq.getOptions().contains(sample.getSentence()[i]))){
                        if(currentPOSReq.getParameter()!=null)
                            if(currentPOSReq.getValue()!=null){
                                parameters.put(currentPOSReq.getParameter(),currentPOSReq.getValue());
                            } else {
                                parameters.put(currentPOSReq.getParameter(), sample.getSentence()[i]);
                            }
                        if(!itr.hasNext()){
                            match = true;
                            break;
                        } else {
                            currentPOSReq = itr.next();
                        }
                    } else {
                        itr = pattern.getPOSRequirementList().iterator();
                        currentPOSReq= itr.next();
                        parameters.clear();
                    }
                }
                if(match){
                    Action action = new Action();
                    action.setAction(pattern.getAction());
                    action.setParameters(parameters);
                    actions.add(action);
                }
            }
        }
        return actions;
    }
}
