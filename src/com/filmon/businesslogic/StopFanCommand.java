package com.filmon.businesslogic;

public class StopFanCommand implements Command{
    private Fan fan;

    public StopFanCommand(Fan fan) {
        this.fan = fan;
    }
    @Override
    public String execute() {
        String temp;
        temp = "Stopping Fan...\n";
        return temp + fan.stop();
    }
    @Override
    public String undo() {
        String temp;
        temp = "Undoing...\n";
        return temp + fan.undo();
    }
}
