package edu.wpi.cs3733.programname.boundary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

public class FAQ_Popup {
    @FXML
    private AnchorPane mianWindow;

    @FXML
    private TextArea text;

    @FXML
    private Label title;

    //faq shit
    @FXML
    private JFXTextField a1;
    @FXML
    private JFXTextArea a2;
    @FXML
    private JFXTextArea a3;
    @FXML
    private JFXTextArea a4;
    @FXML
    private JFXTextArea a5;
    @FXML
    private JFXTextArea a6;

    public void initFAQ(){
        a1.setEditable(false);
        a2.setEditable(false);
        a3.setEditable(false);
        a4.setEditable(false);
        a5.setEditable(false);
        a6.setEditable(false);
    }

}
