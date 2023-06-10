package contact_management.enumeration;

public enum OpportunityStageEnum {
    FIRST_CONTACT("first contact"),
    MEETING_SCHEDULED("meeting scheduled"),
    PROPOSAL("proposal"),
    CLOSED("closed");

    private final String label;

    OpportunityStageEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
