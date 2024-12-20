package bangladeshcricketboard.simulatingoperationsofbangladeshcricketboard.NonUserClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bangladeshcricketboard.simulatingoperationsofbangladeshcricketboard.BcbMainApplicationClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MotherOfAllClasses {

    private static double xOffSet = 0;
    private static double yOffSet = 0;

    public static void sceneSwithcer(MouseEvent event, String fxmlPath){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BcbMainApplicationClass.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            setStageAndSetupDrag(stage, root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {}

    }public static void sceneSwithcer(ActionEvent event, String fxmlPath){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BcbMainApplicationClass.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            setStageAndSetupDrag(stage, root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {}
    }

    public static void logout(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BcbMainApplicationClass.class.getResource("/logindsigns/SignIn_&_Up_Designs/LoginViewPage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            setStageAndSetupDrag(stage, root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {}
    }
    public static void anchorPaneExpand(AnchorPane ... anchorPanes){
        for (AnchorPane anchorPane : anchorPanes) {
            anchorPane.setPrefWidth(165);
        }
    }

    public static void labelExpand(Label ... labels){
        for (Label label : labels) {
            label.setPrefWidth(165);
        }
    }

    public static void anchorPaneCollapse(AnchorPane ... anchorPanes){
        for (AnchorPane anchorPane : anchorPanes) {
            anchorPane.setPrefWidth(5);
        }
    }

    public static void labelCollapse(Label ... labels){
        for (Label label : labels) {
            label.setPrefWidth(5);
        }
    }

    public static void hideAllLabels(Label ... labels){
        for (Label label : labels) {
            label.setVisible(false);
        }
    }

    public static void showAllLabels(Label ... labels){
        for (Label label : labels) {
            label.setVisible(true);
        }
    }

    public static void mouseEnterEffectExitButton(Button ... buttons){
        for (Button button : buttons) {
            button.setOnMouseEntered(event -> {
                button.setStyle("-fx-background-color: #f21212;");
                button.setCursor(Cursor.HAND);
                button.setTextFill(Color.web("#ffffff"));
            });
            button.setOnMouseExited(event -> {
                button.setTextFill(Color.web("#f21212"));
                button.setStyle("-fx-background-color: #ffffff;");
            });
        }
    }

    public static void mouseEnterEffectMinimizeButton(Button ... buttons){
        for (Button button : buttons) {
            button.setOnMouseEntered(event -> {
                button.setStyle("-fx-background-color: #B0BEC5;");
                button.setCursor(Cursor.HAND);
            });
            button.setOnMouseExited(event -> {
                button.setTextFill(Color.web("#000000"));
                button.setStyle("-fx-background-color: #ffffff;");
            });
        } 
    }

    public static void addHoverEffect(AnchorPane ... anchorPanes) {
        for (AnchorPane anchorPane : anchorPanes) {
            anchorPane.setOnMouseEntered(event -> {
                anchorPane.setStyle("-fx-background-color:  #546E7A;");
                anchorPane.setCursor(Cursor.HAND);
            });
            anchorPane.setOnMouseExited(event -> {
                anchorPane.setStyle("-fx-background-color:  #004D40;");
            });
        }
    }

    public static void setStageAndSetupDrag(Stage stage, Parent root) {
        
        root.setOnMousePressed(event -> {
        xOffSet = event.getSceneX();
        yOffSet = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        });
    }

    public static void minimizeButton(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public static void borderPaneCenterChange(BorderPane borderPane, String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BcbMainApplicationClass.class.getResource(fxmlPath));
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {}
    }

    public static void sendFile(String filePath, String ... content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String string : content) {
                writer.write(string + ",");
            }
            reciver(filePath);
            writer.close();
        } catch (Exception e) {
        }
    }

    public  static void reciver(String filePath) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("New File Recived");
        alert.setHeaderText("A new file has been recived");
        alert.showAndWait();
    }

    // Must add Id on the first
    public static int getLastID(int baseId, String filePath) {
        String id = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                id = data[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (id.equals("")) {
            return baseId + 1;
        } else {
            return Integer.parseInt(id) + 1;
        }
    }
    public static void clearFileContent(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        }
    }

    public static File fileChooser(ActionEvent event, Button fileChooserButton) {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Stage stage = (Stage) fileChooserButton.getScene().getWindow();
        return file.showOpenDialog(stage);
    }

    public static void setBarChart(BarChart<String, Number> barChart, String filePath) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Cost Report");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                series.getData().add(new XYChart.Data<>(data[0], Double.parseDouble(data[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }
}
