
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

    import java.util.ArrayList;

    public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{

            BorderPane borderpane = new BorderPane();
            Label label = new Label("Your alignment:");


            String[] cbNames = new String[]{"Show Headers", "Show Sequences", "Show Numbering"};
            ArrayList<CheckBox> cbs = new ArrayList<CheckBox>();
            ArrayList<Text> textToShow = new ArrayList<Text>();
            for(int i=0; i<cbNames.length; i++){
                cbs.add(new CheckBox(cbNames[i]));
                textToShow.add(new Text(cbNames[i]));
            }

            String[] buttonNames = new String[]{"Select all","Clear selection","Apply"};
            ArrayList<Button> buttons = new ArrayList<Button>();
            for(int i=0; i<buttonNames.length; i++){
                buttons.add(new Button(buttonNames[i]));
            }

            VBox options = new VBox(5);
            cbs.forEach((CheckBox cb) -> options.getChildren().add(cb));
            HBox controllPannel = new HBox(5);
            buttons.forEach((Button b) -> controllPannel.getChildren().add(b));



            borderpane.setTop(label);
            borderpane.setRight(options);
            borderpane.setBottom(controllPannel);
            buttons.get(0).setOnAction(event -> cbs.forEach((CheckBox elem) -> elem.setSelected(true)));
            buttons.get(1).setOnAction(event -> cbs.forEach((CheckBox elem) -> elem.setSelected(false)));


            Scene scene = new Scene(borderpane, 700, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        }



    public static void main(String[] args) {
        System.out.print("Assignment 1 - Robin Harmening");

        FastaReader file1 = new FastaReader(args[0]);
        int sequenceLength = file1.getSequences().get(1).getNuclSequence().length + 1;
        CommandLine.printSequenceAlignment(1, sequenceLength, file1.getSequences());

    }
}
