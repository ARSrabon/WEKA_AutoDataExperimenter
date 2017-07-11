import com.google.gson.Gson;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by msrabon on 09-Jul-17.
 */
public class AutoExperimenter {
    static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Data file Path: ");
        String file = "data/hypothyroid.arff";
//        file = scanner.next();

        File file1 = new File(file);
        ArffLoader loader = new ArffLoader();
        loader.setSource(file1);
        Instances data = loader.getDataSet();

        Experiment experiment = Experiment.getInstance();

        experiment.setInstances(data);
        experiment.setDataSetName(file1.getName());
        experiment.runKNN(10, 3);
        experiment.runNaiveBayesWith_K_FoldCrossValidation(10);
        experiment.runID3(10);
        experiment.runJ48(10);
        experiment.runCART(10);

        writeToJsonFile();
    }

    public static void writeToJsonFile() {

        ResultHolder resultHolder = ResultHolder.getInstance();
        List<Result> resultList = resultHolder.getResultList();
        try (FileWriter file = new FileWriter("result.json")) {
            file.write(gson.toJson(resultList));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
