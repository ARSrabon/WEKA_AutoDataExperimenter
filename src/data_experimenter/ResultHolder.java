package data_experimenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msrabon on 10-Jul-17.
 */
public class ResultHolder {
    private static ResultHolder ourInstance = new ResultHolder();
    private List<Dataset_Info> resultList = new ArrayList<>();

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
}
