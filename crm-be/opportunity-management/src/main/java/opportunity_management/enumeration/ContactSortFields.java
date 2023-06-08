package opportunity_management.enumeration;

public enum ContactSortFields {
    MODIFY_DATE("modifyDate","Last Modification"),
    FIRST_NAME("firstName","First Name"),
    LAST_NAME("lastName","LastName");

    private String label;
    private String name;

    ContactSortFields(String name,String label) {
        this.label = label;
        this.name= name;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }
}
