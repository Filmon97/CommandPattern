package com.filmon.businesslogic;

public class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }
    @Override
    public String execute() {
        String temp;
        temp = "Turning off Light...\n";
        return temp + light.turnOff();
    }
    @Override
    public String undo() {
        String temp;
        temp = "Undoing...\n";
        return temp + light.undo();
    }
}
