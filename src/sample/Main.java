package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        int prefWidth = 380;
        int vGap = 6;
        int hGap = 4;
        Insets insets = new Insets(8, 8, 8, 8);

        FlowPane pane = new FlowPane();
        pane.setPadding(insets);

        Label inputFieldName = new Label("Your DNA input field:");
        Label outputFieldName = new Label("Your output field:");
        inputFieldName.setTextFill(Color.rgb(15,15,200));
        outputFieldName.setTextFill(Color.rgb(150,0,30));

        TextArea input = new TextArea();
        input.setPrefRowCount(10);
        input.setPrefColumnCount(80);
        input.setFont(Font.font("Monospaced", 12));
        input.setWrapText(true);
        input.setPrefWidth(prefWidth);

        TextArea output = new TextArea();
        output.setPrefRowCount(10);
        output.setPrefColumnCount(80);
        output.setFont(Font.font("Monospaced", 12));
        output.setWrapText(true);
        output.setEditable(false);
        output.setPrefWidth(prefWidth);

        FlowPane controlls = new FlowPane(4., 4.);
        controlls.setPadding(insets);
        controlls.setPrefWrapLength(prefWidth);

        /**
         * Now follows a long implementation of Buttons and their functionalities
         */
        Button filter = new Button("Filter");
        filter.setOnAction(e -> {
            input.setText(DNAsequence.makeLegitSequence(input.getText()));
        });

        Button flip = new Button("Flip");
        flip.setOnAction(e -> {
            String temp = input.getText();
            input.setText(output.getText());
            output.setText(temp);
        });

        Button upper = new Button("upper case");
        upper.setOnAction(e -> {
            input.setText(input.getText().toUpperCase());
            output.setText(output.getText().toUpperCase());
        });

        Button lower = new Button("lower case");
        lower.setOnAction(e -> {
            input.setText(input.getText().toLowerCase());
            output.setText(output.getText().toLowerCase());
        });

        Button toRNA = new Button("to RNA");
        toRNA.setOnAction(e -> {
            DNAsequence sequence = new DNAsequence(input.getText());
            sequence.makeRNAfromDNA();
            output.setText(sequence.getStringSequence());
        });

        Button reverse = new Button("reverse");
        reverse.setOnAction(e -> {
            DNAsequence sequence = new DNAsequence(input.getText());
            sequence.reverseSequence();
            output.setText(sequence.getStringSequence());
        });

        Button complementary = new Button("complement");
        complementary.setOnAction(e -> {
            DNAsequence sequence = new DNAsequence(input.getText());
            sequence.makeComplementary(sequence.isRnaBoolean());
            output.setText(sequence.getStringSequence());
        });

        Button revComp = new Button("rev. complem.");
        revComp.setOnAction(e -> {
            DNAsequence sequence = new DNAsequence(input.getText());
            sequence.makeComplementary(sequence.isRnaBoolean());
            sequence.reverseSequence();
            output.setText(sequence.getStringSequence());
        });

        Button contentGC = new Button("GC content");
        contentGC.setOnAction(e -> {
            DNAsequence sequence = new DNAsequence(input.getText());
            output.setText("The CG Content of the sequence is: " +
                    String.valueOf(sequence.getGCContent()) + "%");
        });

        Button length = new Button("length");
        length.setOnAction(e -> {
            output.setText(input.getText() +
                    "\nLenght of your Sequence: " +
                    input.getText().length() + " Nucleotides");
        });

        Button clear = new Button("clear");
        clear.setOnAction(e -> {
            input.setText(null);
            output.setText(null);
        });

        ArrayList<Button> buttonList = makeButtonArrayList(filter, flip, upper, lower, toRNA, reverse, complementary,
                revComp, contentGC, length, clear);
        buttonList.forEach((Button b) -> b.setPrefWidth(89));


        //TODO implement silder
        Slider slider = new Slider();
        slider.setPrefWidth(prefWidth - (controlls.getVgap() * 2));
        slider.setMax(80);
        slider.setMin(1);
        slider.adjustValue(80);
        slider.increment();
        slider.setMinorTickCount(4);
        slider.setSnapToTicks(true);
        slider.setShowTickLabels(true);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int newV = newValue.intValue();
                output.setPrefColumnCount(newV);
            }
        });

        controlls.getChildren().addAll(buttonList);
        controlls.getChildren().add(slider);

        pane.getChildren().addAll(inputFieldName, input, outputFieldName, output, controlls);
        Scene scene = new Scene(pane, 400, 500);

        primaryStage.setTitle("DNA Manipulator");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ArrayList<Button> makeButtonArrayList(Button... buttons) {
        ArrayList<Button> buttonList = new ArrayList<Button>();
        for (int i = 0; i < buttons.length; i++) {
            buttonList.add(buttons[i]);
        }
        return buttonList;
    }

    private static void flip(TextArea one, TextArea two) {
        String temp = one.getText();
        one.setText(two.getText());
        two.setText(temp);
    }
}
