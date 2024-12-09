package bangladeshcricketboard.simulatingoperationsofbangladeshcricketboard;

import java.io.IOException;

import bangladeshcricketboard.simulatingoperationsofbangladeshcricketboard.NonUserClass.MotherOfAllClasses;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BcbMainApplicationClass extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader fxmlLoader = new FXMLLoader(BcbMainApplicationClass.class.getResource("/user_dashboard_designs/FinanceOfficerDashBoard/FinanceOfficerDashBordDesign.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        MotherOfAllClasses.setStageAndSetupDrag(stage, root);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}