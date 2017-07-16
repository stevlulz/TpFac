import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sidahmed on 22/03/17.
 */
public class Scheduler implements Algorithms {
    QueueS ready=new QueueS();
    QueueS running=new QueueS();
    LinkedList<Result> result =new LinkedList<>();

    public Scheduler(QueueS s)
    {
        while(!s.isEmpty())
            ready.enQueue(s.deQueue());
    }
    public void printResult()
    {
        for (int i = result.size() - 1; i >= 0; i--) {
            if(result.get(i).getResult() == null)
                System.out.println(-1);
            else
                System.out.println(result.get(i).getResult().getProcessID());
        }
    }


    @Override
    public void firstComeFirstServerd() {
        int t=1;
        ready.sortByFirstArrive();
        this.running.enQueue(ready.getA());
        PCB a=new PCB();

        while(!this.running.isEmpty())
        {
            a=this.running.deQueue();
            while(t<=a.getArrivalTime())
            {
                this.result.addFirst(new Result(null,t++));

            }

            while(! a.getTerminated())
            {
                this.result.addFirst(new Result(a,t++));
                a.executeForOneTime();
            }
        }

    }

    @Override
    public void shortJobFirst() {
        QueueS temp=new QueueS();
        int t=0;
        PCB a=new PCB();
        while(!ready.isEmpty())
        {
            this.running.enQueue(this.ready.getAvailableJobsByShortestAt(t++));
        }
        t=1;

        while(!this.running.isEmpty())
        {
            a=this.running.deQueue();
            while(t<=a.getArrivalTime())
            {
                this.result.addFirst(new Result(null,t++));

            }

            while(! a.getTerminated())
            {
                this.result.addFirst(new Result(a,t++));
                a.executeForOneTime();
            }

        }




    }

    @Override
    public void roundRobin(int q) {
        int t=1;
        ready.sortByFirstArrive();
        this.running.enQueue(ready.getA());
        PCB a=new PCB();
        while(!this.running.isEmpty())
        {
           a=running.deQueue();

            while(t<=a.getArrivalTime())
            {
                this.result.addFirst(new Result(null,t++));
            }
            for(int i=1;i<=q && !a.getTerminated();++i)
            {
                this.result.addFirst(new Result(a,t++));
                a.executeForOneTime();
            }

            if(!a.getTerminated())
            {
                if(this.running.isThereJobAvailableAt(t))
                this.running.enQueue(a);
                else
                    this.running.addLast(a);
            }


        }


    }
}
