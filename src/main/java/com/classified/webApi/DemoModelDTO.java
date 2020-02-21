package com.classified.webApi;

public class DemoModelDTO {
    private String demoText;

    public DemoModelDTO(){}

    public DemoModelDTO(String demoText) {
        this.demoText = demoText;
    }

    public String getDemoText() {
        return demoText;
    }

    public void setDemoText(String demoText) {
        this.demoText = demoText;
    }
}
