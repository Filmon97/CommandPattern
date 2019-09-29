package com.filmon.businesslogic;

import java.util.Stack;

public class Light {
    private boolean state = false;
    private Stack<Boolean> previousStates = new Stack<Boolean>();

    public String turnOn() {
        previousStates.add(state);
        if(!state) {
            state = true;
            return status();
        }else
            return "Light is unchanged";
    }

    public String turnOff() {
        previousStates.add(state);
        if(state) {
            state = false;
            return status();
        }else
            return "Light is unchanged";
    }

    public String undo(){
        boolean ps;
        if(!previousStates.isEmpty()) {
            ps = previousStates.pop();
            if (ps == state) {
                return "Undoing nothing";
            } else {
                state = ps;
                return status();
            }
        }
        return "Undoing nothing";
    }
    public String status(){
        if(state){
            return "Light is on.";
        }
        return "Light is off.";
    }
}
