/**
 * Created by sidahmed on 22/03/17.
 */
public class Result {
    private PCB result=new PCB();
    private int t;
    public Result(){}

    public Result(PCB result, int t) {
        this.result = result;
        this.t = t;
    }

    public PCB getResult() {
        return result;
    }

    public void setA(PCB a) {
        this.result = a;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}
