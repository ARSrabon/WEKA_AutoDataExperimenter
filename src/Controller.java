import data_experimenter.Dataset_Info;
import data_experimenter.Experiment;
import data_experimenter.ResultHolder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import weka.core.Instances;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller {

    File[] listOfFiles;
    List<File> fileList;
    File file;
    File dir;

    Experiment experiment = Experiment.getInstance();
    ResultHolder resultHolder = ResultHolder.getInstance();

    @FXML
    Label lbl_directory;

    @FXML
    RadioButton radio_all;
    @FXML
    RadioButton radio_selection;

    @FXML
    CheckBox chk_zeror;
    @FXML
    CheckBox chk_oner;
    @FXML
    CheckBox chk_knn;
    @FXML
    CheckBox chk_naivebayes;
    @FXML
    CheckBox chk_id3;
    @FXML
    CheckBox chk_j48;
    @FXML
    CheckBox chk_cart;

    public void selectFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");
        dir = directoryChooser.showDialog(new Stage());
        if (dir != null) {
            lbl_directory.setText("Dir: " + dir.getPath());
            listOfFiles = dir.listFiles();
            fileList = Arrays.asList(listOfFiles);
        }
    }

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Directory");
        file = fileChooser.showOpenDialog(new Stage());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (listOfFiles.length != 0) {
            alert.setTitle("Confirmation");
            alert.setHeaderText("Warning!!!");
            alert.setContentText("You have aleady selected a file directory. Do you want to add this file to the list" +
                                         " of Test Files?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                fileList.add(file);
            }
        }
        if (file != null) {
            lbl_directory.setText("Dir: " + file.getPath());
        }
    }

    public void enableSelection() {
        if (radio_selection.isSelected() == true) {
            radio_all.setSelected(false);
            enable_disable(false);
        }
    }

    public void disableSelection() {
        if (radio_all.isSelected() == true) {
            radio_selection.setSelected(false);
            enable_disable(true);
        }
    }

    public void enable_disable(boolean b) {
        chk_zeror.setDisable(b);
        chk_oner.setDisable(b);
        chk_knn.setDisable(b);
        chk_naivebayes.setDisable(b);
        chk_id3.setDisable(b);
        chk_j48.setDisable(b);
        chk_cart.setDisable(b);
    }

    public void runTest(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();
    }

    public void selectedTest(){

    }

    public void autoMate() throws Exception {
        experiment.runZeroR(10);
        experiment.runOneR(10);
        experiment.runKNN(10, 3);
        experiment.runNaiveBayesWith_K_FoldCrossValidation(10);
        experiment.runID3(10);
        experiment.runJ48(10);
        experiment.runCART(10);
        Dataset_Info dataset_info = experiment.getDatasetInfo();
        resultHolder.addToResultList(dataset_info);
    }
}
