package com.filmon.businesslogic;

import java.util.Stack;

public class Fan {
    private boolean state = false;
    private Stack<Boolean> previousStates = new Stack<Boolean>();

    public String start() {
        previousStates.add(state);
        if(!state) {
            state = true;
            return status();
        }else
            return "Fan is unchanged";

    }

    public String stop() {
        previousStates.add(state);
        if(state) {
            state = false;
            return status();
        }else
            return "Fan is unchanged";

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
            return "Fan Started.";
        }
        return "Fan Stopped.";
    }
}
