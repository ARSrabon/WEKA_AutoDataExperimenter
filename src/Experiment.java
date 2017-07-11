import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.SimpleCart;
import weka.core.Instances;

import java.util.List;
import java.util.Random;

/**
 * Created by msrabon on 10-Jul-17.
 */
public class Experiment {
    private static Experiment ourInstance = new Experiment();
    private Instances instances;
    private String dataSetName;
    public ResultHolder resultHolder = ResultHolder.getInstance();

    public static Experiment getInstance() {
        return ourInstance;
    }

    public Instances getInstances() {
        return instances;
    }

    public void setInstances(Instances instances) {
        this.instances = instances;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }

    private Experiment() {
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
        ibk.buildClassifier(instances);

        Evaluation eval = new Evaluation(instances);
        eval.crossValidateModel(ibk, instances, n, new Random(1));

        Result result = new Result("KNN", eval.weightedPrecision(), eval.weightedRecall(), eval
                .weightedTrueNegativeRate(), eval.weightedFMeasure());

        resultHolder.addToResults(result);
        System.out.println(result);

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
        nb.buildClassifier(instances);

        Evaluation eval = new Evaluation(instances);
        eval.crossValidateModel(nb, instances, n, new Random(1));

        Result result = new Result("Naive Bayes", eval.weightedPrecision(), eval.weightedRecall(), eval
                .weightedTrueNegativeRate(), eval.weightedFMeasure());

        resultHolder.addToResults(result);
        System.out.println(result);
    }

    public void runID3(int n) {
        Classifier id3 = new Id3();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }

        try{
            id3.buildClassifier(instances);

            Evaluation eval = new Evaluation(instances);
            eval.crossValidateModel(id3, instances, n, new Random(1));

            Result result = new Result("ID3", eval.weightedPrecision(), eval.weightedRecall(), eval
                    .weightedTrueNegativeRate(), eval.weightedFMeasure());

            resultHolder.addToResults(result);
            System.out.println(result);
        }catch (Exception e){
            Result result = new Result("ID3",0, 0,0, 0);
            resultHolder.addToResults(result);
            System.out.println(result);
        }
    }


    public void runJ48(int n) throws Exception {
        Classifier j48 = new J48();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }
        j48.buildClassifier(instances);

        Evaluation eval = new Evaluation(instances);
        eval.crossValidateModel(j48, instances, n, new Random(1));

        Result result = new Result("C4.5/J48", eval.weightedPrecision(), eval.weightedRecall(), eval
                .weightedTrueNegativeRate(), eval.weightedFMeasure());

        resultHolder.addToResults(result);
        System.out.println(result);
    }

    public void runCART(int n) throws Exception {
        Classifier cart = new SimpleCart();
        if (instances.classIndex() == -1) {
            instances.setClassIndex(instances.numAttributes() - 1);
        }
        cart.buildClassifier(instances);

        Evaluation eval = new Evaluation(instances);
        eval.crossValidateModel(cart, instances, n, new Random(1));

        Result result = new Result("CART", eval.weightedPrecision(), eval.weightedRecall(), eval
                .weightedTrueNegativeRate(), eval.weightedFMeasure());

        resultHolder.addToResults(result);
        System.out.println(result);
    }


}
