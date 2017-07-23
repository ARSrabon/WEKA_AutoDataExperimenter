package data_experimenter;

import java.text.DecimalFormat;

/**
 * Created by msrabon on 10-Jul-17.
 */
public class Result {
    private String TestName;
    private String confusionMatrix;
    private double accuracy;
    private double errorRate;
    private double w_Precision;
    private double recall;
    private double specificity;
    private double f_measure;
    private double runTime;

    public Result() {
    }

    public Result(String testName, String confusionMatrix, double accuracy, double errorRate, double w_Precision,
                  double recall, double specificity, double f_measure, double runTime) {
        TestName = testName;
        this.confusionMatrix = confusionMatrix;
        this.accuracy = Double.valueOf(new DecimalFormat("#.####").format(accuracy * 100.00));
        this.errorRate = Double.valueOf(new DecimalFormat("#.####").format(errorRate * 100.00));
        this.w_Precision = Double.valueOf(new DecimalFormat("#.####").format(w_Precision));
        this.recall = Double.valueOf(new DecimalFormat("#.####").format(recall));
        this.specificity = Double.valueOf(new DecimalFormat("#.####").format(specificity));
        this.f_measure = Double.valueOf(new DecimalFormat("#.####").format(f_measure));
        this.runTime = runTime;
    }

    public Result(String testName, double accuracy, double errorRate, double w_Precision, double recall, double
            specificity, double f_measure, double runTime) {
        TestName = testName;
        this.accuracy = Double.valueOf(new DecimalFormat("#.####").format(accuracy * 100.00));
        this.errorRate = Double.valueOf(new DecimalFormat("#.####").format(errorRate * 100.00));
        this.w_Precision = Double.valueOf(new DecimalFormat("#.####").format(w_Precision));
        this.recall = Double.valueOf(new DecimalFormat("#.####").format(recall));
        this.specificity = Double.valueOf(new DecimalFormat("#.####").format(specificity));
        this.f_measure = Double.valueOf(new DecimalFormat("#.####").format(f_measure));
        this.runTime = runTime;
    }

    public Result(String testName, double accuracy, double errorRate, double w_Precision, double recall, double
            specificity, double f_measure) {
        TestName = testName;
        this.accuracy = Double.valueOf(new DecimalFormat("#.####").format(accuracy * 100.00));
        this.errorRate = Double.valueOf(new DecimalFormat("#.####").format(errorRate * 100.00));
        this.w_Precision = Double.valueOf(new DecimalFormat("#.####").format(w_Precision));
        this.recall = Double.valueOf(new DecimalFormat("#.####").format(recall));
        this.specificity = Double.valueOf(new DecimalFormat("#.####").format(specificity));
        this.f_measure = Double.valueOf(new DecimalFormat("#.####").format(f_measure));
    }

    public Result(String testName, double w_Precision, double recall, double specificity, double
            f_measure) {
        this.TestName = testName;
        this.accuracy = Double.valueOf(new DecimalFormat("#.####").format(w_Precision * 100.00));
        this.errorRate = Double.valueOf(new DecimalFormat("#.####").format(recall * 100.00));
        this.w_Precision = Double.valueOf(new DecimalFormat("#.####").format(w_Precision));
        this.recall = Double.valueOf(new DecimalFormat("#.####").format(recall));
        this.specificity = Double.valueOf(new DecimalFormat("#.####").format(specificity));
        this.f_measure = Double.valueOf(new DecimalFormat("#.####").format(f_measure));
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
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

    public String getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(String confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    public double getRunTime() {
        return runTime;
    }

    public void setRunTime(double runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return getTestName() + " " + getAccuracy() + " " + getErrorRate() + " " + getF_measure() + " " + getRecall()
                + " " + getSpecificity() + " " + getW_Precision() + " " + getRunTime() + "ms ";
    }
}
