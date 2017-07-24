import data_experimenter.Dataset_Info;
import data_experimenter.Experiment;
import data_experimenter.Result;
import data_experimenter.ResultHolder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller {

    File[] listOfFiles;
    List<File> fileList;
    File file;
    File dir;

    String directory;

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

    @FXML
    TextArea txt_console;

    public void selectFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");
        dir = directoryChooser.showDialog(new Stage());
        if (dir != null) {
            if (directory == null) {
                directory = "Dir: " + dir.getPath();
            }
            lbl_directory.setText(directory);
            listOfFiles = dir.listFiles();
            fileList = Arrays.asList(listOfFiles);
        }
    }

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Directory");
        file = fileChooser.showOpenDialog(new Stage());

        if (!(file.getName().contains(".csv") || file.getName().contains(".arff"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!!");
            alert.setTitle("File not supported error.");
            alert.setContentText("Selected File '" + file.getName() + "' is not supported.");
            alert.showAndWait();
            file = null;
        } else if (fileList != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Warning!!!");
            alert.setContentText("You have aleady selected a file directory. Do you want to add this file to the " +
                                         "list" +
                                         " of Test Files?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                fileList.add(file);
            }
        }
        if (file != null) {
            if (directory == null) {
                directory = "Dir: " + file.getPath();
            }
            lbl_directory.setText(directory);
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

    public void runTest() throws Exception {
        if (fileList != null && file != null) {
            switch (alert()) {
                case 1:
                    for (File file : fileList) {
                        experiment.loadDataSet(file);
                        if (radio_all.isSelected()) {
                            autoMate();
                        } else {
                            selectedTest();
                        }
                    }
                    resultHolder.writeToJsonFile();
                    break;
                case 2:
                    experiment.loadDataSet(file);
                    if (radio_all.isSelected()) {
                        autoMate();
                    } else {
                        selectedTest();
                    }
                    resultHolder.writeToJsonFile();
                    break;
                case 3:
                    for (File file : fileList) {
                        experiment.loadDataSet(file);
                        if (radio_all.isSelected()) {
                            autoMate();
                        } else {
                            selectedTest();
                        }
                    }
                    experiment.loadDataSet(file);
                    if (radio_all.isSelected()) {
                        autoMate();
                    } else {
                        selectedTest();
                    }
                    resultHolder.writeToJsonFile();
                    break;
            }
        } else if (fileList != null) {
            for (File file : fileList) {
                experiment.loadDataSet(file);
                if (radio_all.isSelected()) {
                    autoMate();
                } else {
                    selectedTest();
                }
            }
            resultHolder.writeToJsonFile();
        } else if (file != null) {
            experiment.loadDataSet(file);
            if (radio_all.isSelected()) {
                autoMate();
            } else {
                selectedTest();
            }
            resultHolder.writeToJsonFile();
        }
        resultHolder.writeToJsonFile();
    }

    private int alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Please choose which action to take?");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Run Test on files in directory");
        ButtonType buttonTypeTwo = new ButtonType("Run Test on single file");
        ButtonType buttonTypeThree = new ButtonType("Use Both");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            // ... user chose "One"
            return 1;
        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Two"
            return 2;
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
            return 3;
        } else {
            // ... user chose CANCEL or closed the dialog
            return 0;
        }
    }

    public void selectedTest() throws Exception {
        if (chk_zeror.isSelected()) {
            experiment.runZeroR(10);
        }
        if (chk_oner.isSelected()) {
            experiment.runOneR(10);
        }
        if (chk_knn.isSelected()) {
            experiment.runKNN(10, 3);
        }
        if (chk_naivebayes.isSelected()) {
            experiment.runNaiveBayesWith_K_FoldCrossValidation(10);
        }
        if (chk_id3.isSelected()) {
            experiment.runID3(10);
        }
        if (chk_j48.isSelected()) {
            experiment.runJ48(10);
        }
        if (chk_cart.isSelected()) {
            experiment.runJ48(10);
        }
        Dataset_Info dataset_info = experiment.getDatasetInfo();
        resultHolder.addToResultList(dataset_info);
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
        txt_console.appendText("========== Test Results ============\n");
        showResultsToConsole();
    }

    public void showResultsToConsole() {
        for (Dataset_Info datasetInfo : resultHolder.getResultList()) {
            txt_console.appendText("======================================\n");
            txt_console.appendText(datasetInfo.getConsoleString());
            txt_console.appendText("Test Name   Accuracy    Error Rate  Precision   Recall  Specificity     F_Measure   Runtime\n");
            for (Result result : datasetInfo.getResultList()){
                txt_console.appendText(result.getConsoleString());
            }
            txt_console.appendText("======================================\n");
        }

        txt_console.appendText("*********** Testing Complete **************");
    }
}
