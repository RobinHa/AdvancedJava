package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            FastaReader file1 = new FastaReader("exemplary_RNA_aln.fa");

            LinkedList<Sequence> sequences = file1.getSequences();

            BorderPane borderpane = new BorderPane();
            Label label = new Label("Your alignment:");

            String[] cbNames = new String[]{"Show Headers", "Show Sequences", "Show Numbering"};
            ArrayList<CheckBox> cbs = new ArrayList<CheckBox>();
            Text alignment = new Text();
            for (int i = 0; i < cbNames.length; i++) cbs.add(new CheckBox(cbNames[i]));

            String[] buttonNames = new String[]{"Select all", "Clear selection", "Apply"};
            ArrayList<Button> buttons = new ArrayList<Button>();
            for (int i = 0; i < buttonNames.length; i++) {
                buttons.add(new Button(buttonNames[i]));
            }

            VBox options = new VBox(5);
            cbs.forEach((CheckBox cb) -> options.getChildren().add(cb));
            HBox controls = new HBox(5);
            buttons.forEach((Button b) -> controls.getChildren().add(b));

            borderpane.setTop(label);
            borderpane.setRight(options);
            borderpane.setBottom(controls);

            buttons.get(0).setOnAction(event -> cbs.forEach((CheckBox elem) -> elem.setSelected(true)));
            buttons.get(1).setOnAction(event -> cbs.forEach((CheckBox elem) -> elem.setSelected(false)));
            buttons.get(2).setOnAction(event -> borderpane.setCenter(CommandLine.getSequenceAlignment(cbs.get(0).isSelected(),
                    cbs.get(1).isSelected(), cbs.get(2).isSelected(), sequences)));
            Scene scene = new Scene(borderpane, 950, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
