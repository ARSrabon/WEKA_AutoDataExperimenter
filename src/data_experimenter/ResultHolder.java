package data_experimenter;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by msrabon on 10-Jul-17.
 */
public class ResultHolder {
    private static ResultHolder ourInstance = new ResultHolder();
    private List<Dataset_Info> resultList = new ArrayList<>();
    private Gson gson = new Gson();

    public static ResultHolder getInstance() {
        return ourInstance;
    }

    private ResultHolder() {
    }

    public List<Dataset_Info> getResultList() {
        return resultList;
    }

    public void addToResultList(Dataset_Info datasetInfo) {
        resultList.add(datasetInfo);
    }

    public void setResultList(List<Dataset_Info> resultList) {
        this.resultList = resultList;
    }

    public void writeToJsonFile() {

        ResultHolder resultHolder = ResultHolder.getInstance();
        List<Dataset_Info> resultList = resultHolder.getResultList();
        try (FileWriter file = new FileWriter("result_"+ (new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())).toString() +".json")) {
            file.write(gson.toJson(resultList));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
