package nlp.model;

import java.util.List;

public class POSRequirement {
    private String POS;
    private List<String> options;
    private String parameter;
    private String value;


    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public POSRequirement() {
    }

    public POSRequirement(String POS, List<String> options) {
        this.POS = POS;
        this.options = options;
    }



    public String getPOS() {
        return POS;
    }

    public void setPOS(String POS) {
        this.POS = POS;
    }

    public POSRequirement(String POS, List<String> options, String parameter) {
        this.POS = POS;
        this.options = options;
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
