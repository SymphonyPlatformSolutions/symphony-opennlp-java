package nlp.model;

import java.util.List;

public class Pattern {

    private String action;
    private List<POSRequirement> POSRequirementList;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<POSRequirement> getPOSRequirementList() {
        return POSRequirementList;
    }

    public void setPOSRequirementList(List<POSRequirement> POSRequirementList) {
        this.POSRequirementList = POSRequirementList;
    }
}
