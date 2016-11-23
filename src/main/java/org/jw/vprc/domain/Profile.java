package org.jw.vprc.domain;

public enum Profile {
    Publisher("Publisher"),
    AuxiliaryPioneer50Hr("Auxiliary Pioneer 50 Hours"),
    AuxiliaryPioneer30Hr("Auxiliary Pioneer 50 Hours"),
    RegularPioneer("Regular Pioneer"),
    SpecialPioneer("Special Pioneer"),
    Bethelite("Bethelite"),
    CircuitOverseer("Circuit Overseer"),
    ;

    private final String type;

    Profile(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
