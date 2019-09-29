package com.filmon;

import com.filmon.businesslogic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class App {
    private JPanel panelMain;

    private JButton buttonBed;
    private JButton buttonKitchen;
    private JButton buttonLiving;

    private JComboBox<COMMAND> comboBoxBed;
    private JComboBox<COMMAND> comboBoxKitchen;
    private JComboBox<COMMAND> comboBoxLiving;

    private JButton buttonUndo;
    private JButton buttonReset;

    private JTextArea textAreaOutput;
    private JTextArea textAreaStatus;

    private Stack<SmartHomeRemote> shrs;
    private Light bedLight;
    private Fan bedFan;
    private Light kitchenLight;
    private Fan kitchenFan;
    private Light livingLight;
    private Fan livingFan;

    private SmartHomeRemote bedRemote;
    private SmartHomeRemote kitchenRemote;
    private SmartHomeRemote livingRemote;
    private enum COMMAND {
        StartLight,
        StopLight,
        StartFan,
        StopFan
    }
    public App() {
        reset();

        buttonBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textAreaOutput.setText(bedRemote.buttonPressed());
                shrs.add(bedRemote);
                update();
            }
        });
        buttonKitchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textAreaOutput.setText(kitchenRemote.buttonPressed());
                shrs.add(kitchenRemote);
                update();
            }
        });
        buttonLiving.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textAreaOutput.setText(livingRemote.buttonPressed());
                shrs.add(livingRemote);
                update();
            }
        });

        comboBoxBed.addItem(COMMAND.StartLight);
        comboBoxBed.addItem(COMMAND.StopLight);
        comboBoxBed.addItem(COMMAND.StartFan);
        comboBoxBed.addItem(COMMAND.StopFan);

        comboBoxKitchen.addItem(COMMAND.StartLight);
        comboBoxKitchen.addItem(COMMAND.StopLight);
        comboBoxKitchen.addItem(COMMAND.StartFan);
        comboBoxKitchen.addItem(COMMAND.StopFan);

        comboBoxLiving.addItem(COMMAND.StartLight);
        comboBoxLiving.addItem(COMMAND.StopLight);
        comboBoxLiving.addItem(COMMAND.StartFan);
        comboBoxLiving.addItem(COMMAND.StopFan);

        comboBoxBed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command command;
                switch (comboBoxBed.getSelectedIndex()){
                    case 0:
                        command = new TurnOnLightCommand(bedLight);
                        break;
                    case 1:
                        command = new TurnOffLightCommand(bedLight);
                        break;
                    case 2:
                        command = new StartFanCommand(bedFan);
                        break;
                    default:
                    case 3:
                        command = new StopFanCommand(bedFan);
                        break;
                }
                bedRemote.setCommand(command);
            }
        });
        comboBoxKitchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command command;
                switch (comboBoxKitchen.getSelectedIndex()){
                    case 0:
                        command = new TurnOnLightCommand(kitchenLight);
                        break;
                    case 1:
                        command = new TurnOffLightCommand(kitchenLight);
                        break;
                    case 2:
                        command = new StartFanCommand(kitchenFan);
                        break;
                    default:
                    case 3:
                        command = new StopFanCommand(kitchenFan);
                        break;
                }
                kitchenRemote.setCommand(command);
            }
        });
        comboBoxLiving.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Command command;
                switch (comboBoxLiving.getSelectedIndex()){
                    case 0:
                        command = new TurnOnLightCommand(livingLight);
                        break;
                    case 1:
                        command = new TurnOffLightCommand(livingLight);
                        break;
                    case 2:
                        command = new StartFanCommand(livingFan);
                        break;
                    default:
                    case 3:
                        command = new StopFanCommand(livingFan);
                        break;
                }
                livingRemote.setCommand(command);
            }
        });
        comboBoxBed.setSelectedItem(COMMAND.StartLight);
        comboBoxKitchen.setSelectedItem(COMMAND.StartLight);
        comboBoxLiving.setSelectedItem(COMMAND.StartLight);

        textAreaOutput.setEnabled(false);
        textAreaStatus.setEnabled(false);
        buttonUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SmartHomeRemote undoRemote;
                if (!shrs.isEmpty()){
                    undoRemote = shrs.pop();
                    textAreaOutput.setText(undoRemote.undoPressed());
                    update();
                }
            }
        });
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset();
                comboBoxBed.setSelectedItem(COMMAND.StartLight);
                comboBoxKitchen.setSelectedItem(COMMAND.StartLight);
                comboBoxLiving.setSelectedItem(COMMAND.StartLight);
            }
        });
    }
    private void reset(){
        shrs = new Stack<>();
        bedLight = new Light();
        kitchenLight = new Light();
        livingLight = new Light();

        bedFan = new Fan();
        kitchenFan = new Fan();
        livingFan = new Fan();

        bedRemote = new SmartHomeRemote();
        kitchenRemote = new SmartHomeRemote();
        livingRemote = new SmartHomeRemote();

        textAreaOutput.setText("");
        textAreaStatus.setText("");
    }
    private void update(){
        textAreaStatus.setText(
                "Bedroom: \n" +
                "Light:"+bedLight.status()+"\n"+
                "Fan:"+bedFan.status()+"\n\n"+
                "Kitchen: \n" +
                "Light:"+kitchenLight.status()+"\n"+
                "Fan:"+kitchenFan.status()+"\n\n"+
                "Living: \n" +
                "Light:"+livingLight.status()+"\n"+
                "Fan:"+livingFan.status()+"\n"
                );
    }
    public static void main(String[] args) {

        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
