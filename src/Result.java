import java.text.DecimalFormat;

/**
 * Created by msrabon on 10-Jul-17.
 */
public class Result {
    private String TestName;
    private String dataSetName;
    private double accuracy;
    private double errorRate;
    private double w_Precision;
    private double recall;
    private double specificity;
    private double f_measure;

    public Result(String testName, String dataSetName, double accuracy, double errorRate, double w_Precision, double
            recall, double specificity, double f_measure) {
        TestName = testName;
        this.dataSetName = dataSetName;
        this.accuracy = accuracy;
        this.errorRate = errorRate;
        this.w_Precision = w_Precision;
        this.recall = recall;
        this.specificity = specificity;
        this.f_measure = f_measure;
    }

    public Result(String testName, double w_Precision, double recall, double specificity, double f_measure) {
        TestName = testName;
        this.accuracy = Double.valueOf(new DecimalFormat("#.####").format(w_Precision * 100));
        this.errorRate = Double.valueOf(new DecimalFormat("#.####").format(recall * 100));
        this.w_Precision = Double.valueOf(new DecimalFormat("#.####").format(w_Precision)); ;
        this.recall = Double.valueOf(new DecimalFormat("#.####").format(recall)); ;
        this.specificity = Double.valueOf(new DecimalFormat("#.####").format(specificity)); ;
        this.f_measure = Double.valueOf(new DecimalFormat("#.####").format(f_measure)); ;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }

    public double getW_Precision() {
        return w_Precision;
    }

    public void setW_Precision(double w_Precision) {
        this.w_Precision = w_Precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getSpecificity() {
        return specificity;
    }

    public void setSpecificity(double specificity) {
        this.specificity = specificity;
    }

    public double getF_measure() {
        return f_measure;
    }

    public void setF_measure(double f_measure) {
        this.f_measure = f_measure;
    }

    @Override
    public String toString() {
        return "Result{" +
                "TestName='" + TestName + '\'' +
                ", accuracy=" + accuracy +
                ", errorRate=" + errorRate +
                ", w_Precision=" + w_Precision +
                ", recall=" + recall +
                ", specificity=" + specificity +
                ", f_measure=" + f_measure +
                '}';
    }

    public String toString_1() {
        return "Result{" +
                "TestName='" + TestName + '\'' +
                ", accuracy=" + accuracy +
                ", errorRate=" + errorRate +
                ", w_Precision=" + w_Precision +
                ", recall=" + recall +
                ", specificity=" + specificity +
                ", f_measure=" + f_measure +
                '}';
    }
}
