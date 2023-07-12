package Rules;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RulesDialog {
    public static void show() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Space Invaders Rules");

        VBox dialogBox = new VBox();
        dialogBox.setAlignment(Pos.CENTER);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText("Game Rules:\n" +
                "- Move ship using the LEFT and RIGHT arrow keys\n" +
                "- Press SPACEBAR to shoot laser\n" +
                "- Destroy all aliens to win the game\n");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
            dialogStage.close();
        });

        dialogBox.getChildren().addAll(textArea, startButton);

        Scene dialogScene = new Scene(dialogBox, 400, 300);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }
}
