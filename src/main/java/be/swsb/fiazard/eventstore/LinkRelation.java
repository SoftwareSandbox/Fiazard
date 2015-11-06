package be.swsb.fiazard.eventstore;

public enum LinkRelation {
    FIRST("first"),
    LAST("last"),
    SELF("self"),
    PREVIOUS("previous"),
    NEXT("next"),
    ALTERNATE("alternate");

    private String value;

    LinkRelation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
