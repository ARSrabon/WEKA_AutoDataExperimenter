import com.google.gson.Gson;
import data_experimenter.AttributeFilter;
import data_experimenter.Dataset_Info;
import data_experimenter.Experiment;
import data_experimenter.ResultHolder;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by msrabon on 09-Jul-17.
 */
public class AutoExperimenter extends Application {
    static Gson gson = new Gson();
    static Experiment experiment = Experiment.getInstance();
    static AttributeFilter attributeFilter = AttributeFilter.getInstance();
    static ResultHolder resultHolder = ResultHolder.getInstance();



    public static void main(String[] args) throws Exception {
        launch(args);
//        ArffLoader arffLoader = new ArffLoader();
//        CSVLoader csvLoader = new CSVLoader();
//        Instances data;
//
//        File folder = new File("data/");
//        File[] listOfFiles = folder.listFiles();
//
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//
//                if (file.getName().contains(".arff")) {
//                    arffLoader.setSource(file);
//                    data = arffLoader.getDataSet();
//                    if (data.classIndex() == -1) {
//                        data.setClassIndex(data.numAttributes() - 1);
//                    }
//                    autoMate(data, file.getName());
//                } else if (file.getName().contains(".csv")) {
//                    csvLoader.setSource(file);
//                    data = csvLoader.getDataSet();
//                    if (data.classIndex() == -1) {
//                        data.setClassIndex(data.numAttributes() - 1);
//                    }
//                    autoMate(data, file.getName());
//                } else {
//                    System.out.println("ERROR!!!");
//                }
//
//            }
//        }
//
//        writeToJsonFile();
    }

    public static void autoMate(Instances data, String name) throws Exception {
        experiment.setInstances(data);
        experiment.setDatasetInfo(new Dataset_Info(name, data.numInstances(), data.numAttributes()));
        experiment.runZeroR(10);
        experiment.runOneR(10);
        experiment.runKNN(10, 3);
        experiment.runNaiveBayesWith_K_FoldCrossValidation(10);
        experiment.runID3(10);
        experiment.runJ48(10);
        experiment.runCART(10);
        Dataset_Info dataset_info = experiment.getDatasetInfo();
        resultHolder.addToResultList(dataset_info);
        System.out.println("************************************************************************************");
        System.out.println(dataset_info);
        dataset_info.viewResultList();
        System.out.println("####################################################################################");
    }

    public static void writeToJsonFile() {

        ResultHolder resultHolder = ResultHolder.getInstance();
        List<Dataset_Info> resultList = resultHolder.getResultList();
        try (FileWriter file = new FileWriter("result_"+ (new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())).toString() +".json")) {
            file.write(gson.toJson(resultList));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("auto_experimenter_gui.fxml"));
        primaryStage.setTitle("Auto Data Experimenter");
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();
    }

}
