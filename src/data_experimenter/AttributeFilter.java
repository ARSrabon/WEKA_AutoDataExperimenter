package data_experimenter;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

import java.io.File;

public class AttributeFilter {
    private static AttributeFilter ourInstance = new AttributeFilter();

    public static AttributeFilter getInstance() {
        return ourInstance;
    }

    private AttributeFilter() {
    }

    private Instances instances;

    public Instances getInstances() {
        return instances;
    }

    public void setInstances(Instances instances) {
        this.instances = instances;
    }

    public void runMetaFilter() throws Exception {
        AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
        CfsSubsetEval eval = new CfsSubsetEval();
        GreedyStepwise search = new GreedyStepwise();
        search.setSearchBackwards(true);
        J48 base = new J48();
        classifier.setClassifier(base);
        classifier.setEvaluator(eval);
        classifier.setSearch(search);
        // 10-fold cross-validation
        Evaluation evaluation = new Evaluation(instances);
        evaluation.crossValidateModel(classifier, instances, 10, new Debug.Random(1));
        System.out.println(evaluation.toSummaryString());
    }

    public void runFilter(String name) throws Exception {
        AttributeSelection filter = new AttributeSelection();  // package weka.filters.supervised.attribute!
        CfsSubsetEval eval = new CfsSubsetEval();
        GreedyStepwise search = new GreedyStepwise();
        search.setSearchBackwards(true);
        filter.setEvaluator(eval);
        filter.setSearch(search);
        filter.setInputFormat(instances);
        // generate new data
        Instances newData = Filter.useFilter(instances, filter);

        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File(name+"_filterd.arff"));
        saver.setDestination(new File("data/cpu.arff"));
        saver.writeBatch();
    }

}
