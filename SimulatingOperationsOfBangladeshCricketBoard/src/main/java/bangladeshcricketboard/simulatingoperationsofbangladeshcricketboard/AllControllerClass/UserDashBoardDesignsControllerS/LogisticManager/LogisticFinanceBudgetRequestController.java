package bangladeshcricketboard.simulatingoperationsofbangladeshcricketboard.AllControllerClass.UserDashBoardDesignsControllerS.LogisticManager;

import bangladeshcricketboard.simulatingoperationsofbangladeshcricketboard.NonUserClass.MotherOfAllClasses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Random;

public class LogisticFinanceBudgetRequestController {

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label fileNameLabel;

    @FXML
    private Button chooseFile;

    @FXML
    private Button sendRequest;

    private File SelectedFile;

    @FXML
    void chooseFileOnActionButton(ActionEvent event) {
        SelectedFile = MotherOfAllClasses.fileChooser(event, chooseFile);
        if (SelectedFile != null) {
            fileNameLabel.setText(SelectedFile.getName());
        }
    }

    @FXML
    void sendRequestOnActionButton(ActionEvent event) {
        if (SelectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No File Selected");
            alert.setContentText("Please select a file to send the request");
            alert.showAndWait();
            return;
        } 

        if (descriptionTextArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Description is empty");
            alert.setContentText("Please write a description for the request");
            alert.showAndWait();
            return;
        }

        String description = descriptionTextArea.getText();
        Random random = new Random();
        int requestId = random.nextInt(1000);

        try {
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(SelectedFile));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            reader.close();

            Path dest = Path.of("BangladeshCricketBoard_Group78_IUB_Autumn24\\SimulatingOperationsOfBangladeshCricketBoard\\src\\main\\resources\\AllTextData", "BudgetRequest" + ".txt");
            Path dest1 = Path.of("BangladeshCricketBoard_Group78_IUB_Autumn24\\SimulatingOperationsOfBangladeshCricketBoard\\src\\main\\resources\\AllTextData", "RequestStatus" + ".txt");

            int id = MotherOfAllClasses.getLastID(0, "BangladeshCricketBoard_Group78_IUB_Autumn24\\SimulatingOperationsOfBangladeshCricketBoard\\src\\main\\resources\\AllTextData\\RequestStatus.txt");
            LocalDateTime now = LocalDateTime.now();

            BufferedWriter writer = new BufferedWriter(new FileWriter(dest.toFile(), true));
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(dest1.toFile(), true));
            writer1.write(String.valueOf(id) + "," + description + "," + "Pending" + "," + now.toString() + "\n");
            writer.write(requestId + "," + description + "," + content.toString());
            writer1.close();
            writer.close();
            SelectedFile = null;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Request Sent");
            alert.setHeaderText("Request has been sent");
            alert.setContentText("Your request has been sent successfully. Your request id is " + requestId);
            alert.showAndWait();

        } catch (Exception e) {}
    }

}
