import java.util.ArrayList;

/**
 * Created by sidahmed on 22/03/17.
 */
public class PCB {
   private Boolean terminated=false;
   private int ProcessID,arrivalTime,executionTime,stillTime,processColor;
    public PCB(){}

    public PCB(int processID,int processColor, int arrivalTime, int executionTime) {
        this.ProcessID = processID;
        this.processColor=processColor;
        this.arrivalTime = arrivalTime;
        this.executionTime = executionTime;
        this.stillTime=executionTime;
    }

    @Override
    public String toString() {
        return "PCB{" +
                "terminated=" + terminated +
                ", ProcessID=" + ProcessID +
                ", arrivalTime=" + arrivalTime +
                ", executionTime=" + executionTime +
                ", stillTime=" + stillTime +
                ", processColor=" + processColor +
                '}';
    }

    public Boolean getTerminated() {
        return terminated;
    }

    public void setTerminated(Boolean terminated) {
        this.terminated = terminated;
    }

    public int getProcessID() {
        return ProcessID;
    }

    public void setProcessID(int processID) {
        ProcessID = processID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getStillTime() {
        return stillTime;
    }

    public void setStillTime(int stillTime) {
        this.stillTime = stillTime;
    }
    public Boolean setTerminated()
    {
        terminated=true;
        return terminated;
    }
    public Boolean isShort(PCB p)
    {
        return this.executionTime<=p.getExecutionTime();
    }
    public void executeForOneTime()
    {
        stillTime--;
        if(stillTime==0)
            setTerminated();

    }
    public Boolean isFirstArrived(PCB p)
    {
        return this.arrivalTime<=p.getArrivalTime();
    }


}
