package com.filmon.businesslogic;

public class TurnOnLightCommand implements Command{
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }
    @Override
    public String execute() {
        String temp;
        temp = "Turning on Light...\n";
        return temp + light.turnOn();
    }
    @Override
    public String undo() {
        String temp;
        temp = "Undoing...\n";
        return temp + light.undo();
    }
}
