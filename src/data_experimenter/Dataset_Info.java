package data_experimenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msrabon on 21-Jul-17.
 */
public class Dataset_Info {
    private String datasetName;
    private int no_Instances;
    private int no_Attributes;
//    private String fileLocation;
//    private String timeStamp;
    private List<Result> resultList;

    public Dataset_Info() {
    }

    public Dataset_Info(String datasetName, int no_Instances, int no_Attributes) {
        this.datasetName = datasetName;
        this.no_Instances = no_Instances;
        this.no_Attributes = no_Attributes;
        this.resultList = new ArrayList<>();
    }

    public Dataset_Info(String datasetName) {
        this.datasetName = datasetName;
//        this.fileLocation = fileLocation;
//        this.timeStamp = timeStamp;
        this.resultList = new ArrayList<>();
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

//    public String getFileLocation() {
//        return fileLocation;
//    }
//
//    public void setFileLocation(String fileLocation) {
//        this.fileLocation = fileLocation;
//    }
//
//    public String getTimeStamp() {
//        return timeStamp;
//    }
//
//    public void setTimeStamp(String timeStamp) {
//        this.timeStamp = timeStamp;
//    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public void viewResultList(){
        for (Result result: resultList){
            System.out.println(result.toString());
        }
    }

    public void addToResultsList(Result result) {
        this.resultList.add(result);
    }

    public int getNo_Instances() {
        return no_Instances;
    }

    public void setNo_Instances(int no_Instances) {
        this.no_Instances = no_Instances;
    }

    public int getNo_Attributes() {
        return no_Attributes;
    }

    public void setNo_Attributes(int no_Attributes) {
        this.no_Attributes = no_Attributes;
    }

    @Override
    public String toString() {
        return getDatasetName()+ " \nAttributes: " + getNo_Attributes() +" \nInstances: " + getNo_Instances();
    }
}
