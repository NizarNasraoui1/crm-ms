package contact_management.enumeration;

public enum ContactSearchFields {
    FIRST_NAME("firstName","First Name"),
    LAST_NAME("lastName","LastName");

    private final String label;
    private final String name;

    ContactSearchFields(String name,String label) {
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
