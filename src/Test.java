/**
 * Created by sidahmed on 22/03/17.
 */
public class Test {


    public static void main(String args[] )
    {
        /* PCB(processID,color,arrivalTime,executionTime)   */
         PCB a = new PCB(1,0,0,6);// 5  4  3
         PCB b = new PCB(5,0,12,2);// 1   0  0
         PCB c = new PCB(4,0,0,3);// 2  1   0
        QueueS s = new QueueS();
        s.enQueue(a);
        s.enQueue(b);
        s.enQueue(c);

        Scheduler dd = new Scheduler(s);
        dd.firstComeFirstServerd();
        //dd.shortJobFirst();
       // dd.roundRobin(2);
        dd.printResult();



    }
}
