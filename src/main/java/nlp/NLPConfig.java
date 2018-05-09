package nlp;

public class NLPConfig {

    private String POSModelPath;
    private String SentenceSplitterModelPath;
    private String patternsFile;

    public String getPOSModelPath() {
        return POSModelPath;
    }

    public void setPOSModelPath(String POSModelPath) {
        this.POSModelPath = POSModelPath;
    }

    public String getSentenceSplitterModelPath() {
        return SentenceSplitterModelPath;
    }

    public void setSentenceSplitterModelPath(String sentenceSplitterModelPath) {
        SentenceSplitterModelPath = sentenceSplitterModelPath;
    }

    public String getPatternsFile() {
        return patternsFile;
    }

    public void setPatternsFile(String patternsFile) {
        this.patternsFile = patternsFile;
    }
}
