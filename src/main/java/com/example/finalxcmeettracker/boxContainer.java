package com.example.finalxcmeettracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class boxContainer extends Parent {
    public double time;
    public HBox container;
    public Text boxLabel;
    public TextField boxTimeSlot;
    public Button boxAdd;

    public boxContainer(double time) {
        container = new HBox(5);
        boxLabel = new Text("ID for time of " + time);
        boxTimeSlot = new TextField("");
        boxAdd = new Button("Add");
        container.getChildren().addAll(boxLabel, boxTimeSlot, boxAdd);
        getChildren().add(container);
        boxAdd.setOnAction(new AddButtonListener());

    }

    public class AddButtonListener implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event ){
            Button source = (Button) event.getSource();
            VBox vBox = (VBox) source.getParent().getParent().getParent();
            boxContainer boxContainer = (boxContainer) source.getParent().getParent();
            vBox.getChildren().remove(boxContainer);
        }
    }
}