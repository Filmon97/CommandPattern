package com.filmon.businesslogic;

public class SmartHomeRemote {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public String buttonPressed() {
        return command.execute();
    }
    public String undoPressed() {
        return command.undo();
    }
}
