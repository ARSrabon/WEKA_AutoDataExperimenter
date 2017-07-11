import java.util.ArrayList;
import java.util.List;

/**
 * Created by msrabon on 10-Jul-17.
 */
public class ResultHolder {
    private static ResultHolder ourInstance = new ResultHolder();
    private List<Result> resultList = new ArrayList<>();

    public static ResultHolder getInstance() {
        return ourInstance;
    }

    private ResultHolder() {
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void addToResults(Result result){
        resultList.add(result);
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }
}
