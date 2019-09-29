package com.filmon.businesslogic;

public interface Command {
    public String execute();
    public String undo();
}
