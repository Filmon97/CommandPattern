package com.filmon.businesslogic;

public class StartFanCommand implements Command {
    private Fan fan;

    public StartFanCommand(Fan fan) {
        this.fan = fan;
    }
    @Override
    public String execute() {
        String temp;
        temp = "Starting Fan...\n";
        return temp + fan.start();
    }
    @Override
    public String undo() {
        String temp;
        temp = "Undoing...\n";
        return temp + fan.undo();
    }
}
