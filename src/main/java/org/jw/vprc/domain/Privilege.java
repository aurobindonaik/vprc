package org.jw.vprc.domain;

public enum Privilege {
    UnbaptisedPublisher("Unbaptised Publisher"),
    Publisher("Publisher"),
    MinisterialServant("Ministerial Servant"),
    Elder("Elder"),
    ;
    private final String type;

    Privilege(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
