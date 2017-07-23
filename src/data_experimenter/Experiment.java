package data_experimenter;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.SimpleCart;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by msrabon on 10-Jul-17.
 */
public class Experiment {
    private static Experiment ourInstance = new Experiment();
    private Instances instances;
    private Dataset_Info datasetInfo;

    public static Experiment getInstance() {
        return ourInstance;
    }

    public Instances getInstances() {
        return instances;
    }

    public void setInstances(Instances instances) {
        this.instances = instances;
    }

    public Dataset_Info getDatasetInfo() {
        return datasetInfo;
    }

    public void setDatasetInfo(Dataset_Info datasetInfo) {
        this.datasetInfo = datasetInfo;
    }

    private Experiment() {
    }

    public void loadDataSet(File file) throws IOException {
        ArffLoader arffLoader = new ArffLoader();
        CSVLoader csvLoader = new CSVLoader();
        if (file.isFile()) {

            if (file.getName().contains(".arff")) {
                arffLoader.setSource(file);
                instances = arffLoader.getDataSet();
                this.datasetInfo = new Dataset_Info(file.getName(),instances.numInstances(),instances.numAttributes());
            } else if (file.getName().contains(".csv")) {
                csvLoader.setSource(file);
                instances = csvLoader.getDataSet();
                this.datasetInfo = new Dataset_Info(file.getName(),instances.numInstances(),instances.numAttributes());
            }
//            else {
//                System.out.println("ERROR!!!");
//            }
        }
    }

    /**
     * This method will use KNN classifier algorithm to evaluate data.
     *
     * @param n number of cross validation
     * @param k value of K(no. of neighbors)
     * @throws Exception
     */
    public void runKNN(int n, int k) throws Exception {
        Classifier ibk = new IBk(k);
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }

        try {

            ibk.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);

            final long start = System.nanoTime();
            eval.crossValidateModel(ibk, instances, n, new Random(1));
            final long end = System.nanoTime();
            double runTime = (end - start) / 1000000;
//            System.out.println("KNN Run Time: " + (double) runTime);

            Result result = new Result("KNN(" + k + ")", eval.toMatrixString(), eval.weightedTruePositiveRate(), (1 -
                    eval.weightedTruePositiveRate()), eval.weightedPrecision(), eval.weightedRecall(), eval
                                               .weightedTrueNegativeRate(), (eval.weightedTrueNegativeRate() / (eval
                    .weightedTrueNegativeRate() + eval.weightedFalseNegativeRate())), runTime);

            this.datasetInfo.addToResultsList(result);
            ///
        } catch (Exception e) {
            Result result = new Result("KNN(" + k + ")", 0, 0, 0, 0);
            this.datasetInfo.addToResultsList(result);
        }

    }

    /**
     * @param n
     * @throws Exception
     */
    public void runNaiveBayesWith_K_FoldCrossValidation(int n) throws Exception {
        Classifier nb = new NaiveBayes();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }

        try {
            nb.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);

            final long start = System.nanoTime();
            eval.crossValidateModel(nb, instances, n, new Random(1));
            final long end = System.nanoTime();
            double runTime = (end - start) / 1000000;

            Result result = new Result("Naive_Bayes", eval.toMatrixString(), eval.weightedTruePositiveRate(), (1 -
                    eval.weightedTruePositiveRate()), eval.weightedPrecision(), eval.weightedRecall(), eval
                                               .weightedTrueNegativeRate(), (eval.weightedTrueNegativeRate() / (eval
                    .weightedTrueNegativeRate() + eval.weightedFalseNegativeRate())), runTime);

            this.datasetInfo.addToResultsList(result);
            ///
        } catch (Exception e) {
            Result result = new Result("Naive_Bayes", 0, 0, 0, 0);
            this.datasetInfo.addToResultsList(result);
        }
    }

    public void runID3(int n) {
        Classifier id3 = new Id3();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }
        try {
            id3.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);

            final long start = System.nanoTime();
            eval.crossValidateModel(id3, instances, n, new Random(1));
            final long end = System.nanoTime();
            double runTime = (end - start) / 1000000;

            Result result = new Result("ID3", eval.toMatrixString(), eval.weightedTruePositiveRate(), (1 -
                    eval.weightedTruePositiveRate()), eval.weightedPrecision(), eval.weightedRecall(), eval
                                               .weightedTrueNegativeRate(), (eval.weightedTrueNegativeRate() / (eval
                    .weightedTrueNegativeRate() + eval.weightedFalseNegativeRate())), runTime);

            this.datasetInfo.addToResultsList(result);
            ///
        } catch (Exception e) {
            Result result = new Result("ID3", 0, 0, 0, 0);
            this.datasetInfo.addToResultsList(result);
        }
    }


    public void runJ48(int n) throws Exception {
        Classifier j48 = new J48();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }
        try {
            j48.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);

            final long start = System.nanoTime();
            eval.crossValidateModel(j48, instances, n, new Random(1));
            final long end = System.nanoTime();
            double runTime = (end - start) / 1000000;

            Result result = new Result("C4.4/J48", eval.toMatrixString(), eval.weightedTruePositiveRate(), (1 -
                    eval.weightedTruePositiveRate()), eval.weightedPrecision(), eval.weightedRecall(), eval
                                               .weightedTrueNegativeRate(), (eval.weightedTrueNegativeRate() / (eval
                    .weightedTrueNegativeRate() + eval.weightedFalseNegativeRate())), runTime);

            this.datasetInfo.addToResultsList(result);
            ///
        } catch (Exception e) {
            Result result = new Result("C4.5/J48", 0, 0, 0, 0);
            this.datasetInfo.addToResultsList(result);
        }
    }

    public void runCART(int n) throws Exception {
        Classifier cart = new SimpleCart();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }
        try {
            cart.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);

            final long start = System.nanoTime();
            eval.crossValidateModel(cart, instances, n, new Random(1));

            final long end = System.nanoTime();
            double runTime = (end - start) / 1000000;

            Result result = new Result("CART", eval.toMatrixString(), eval.weightedTruePositiveRate(), (1 -
                    eval.weightedTruePositiveRate()), eval.weightedPrecision(), eval.weightedRecall(), eval
                                               .weightedTrueNegativeRate(), (eval.weightedTrueNegativeRate() / (eval
                    .weightedTrueNegativeRate() + eval.weightedFalseNegativeRate())), runTime);

            this.datasetInfo.addToResultsList(result);
            ///
        } catch (Exception e) {
            Result result = new Result("CART", 0, 0, 0, 0);
            this.datasetInfo.addToResultsList(result);
        }
    }

    public void runOneR(int n) {
        Classifier oneR = new OneR();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }

        try {
            oneR.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);

            final long start = System.nanoTime();
            eval.crossValidateModel(oneR, instances, n, new Random(1));
            final long end = System.nanoTime();
            double runTime = (end - start) / 1000000;

            Result result = new Result("OneR", eval.toMatrixString(), eval.weightedTruePositiveRate(), (1 -
                    eval.weightedTruePositiveRate()), eval.weightedPrecision(), eval.weightedRecall(), eval
                                               .weightedTrueNegativeRate(), (eval.weightedTrueNegativeRate() / (eval
                    .weightedTrueNegativeRate() + eval.weightedFalseNegativeRate())), runTime);

            this.datasetInfo.addToResultsList(result);
        } catch (Exception e) {
            Result result = new Result("OneR", 0, 0, 0, 0);
            this.datasetInfo.addToResultsList(result);
        }
    }

    public void runZeroR(int n) {
        Classifier zeroR = new ZeroR();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }

        try {
            zeroR.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);

            final long start = System.nanoTime();
            eval.crossValidateModel(zeroR, instances, n, new Random(1));
            final long end = System.nanoTime();
            double runTime = (end - start) / 1000000;

            Result result = new Result("ZeroR", eval.toMatrixString(), eval.weightedTruePositiveRate(), (1 -
                    eval.weightedTruePositiveRate()), eval.weightedPrecision(), eval.weightedRecall(), eval
                                               .weightedTrueNegativeRate(), (eval.weightedTrueNegativeRate() / (eval
                    .weightedTrueNegativeRate() + eval.weightedFalseNegativeRate())), runTime);

            this.datasetInfo.addToResultsList(result);
        } catch (Exception e) {
            Result result = new Result("ZeroR", 0, 0, 0, 0);
            this.datasetInfo.addToResultsList(result);
        }
    }

}
