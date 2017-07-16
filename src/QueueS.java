import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sidahmed on 22/03/17.
 */
public class QueueS {
    private LinkedList<PCB> a=new LinkedList<>();
    public QueueS() {}
    public QueueS(LinkedList<PCB> a){
              while(!a.isEmpty())
                this.a.addFirst(a.pollLast());
            }
    public LinkedList<PCB> getA()
    {
        return a;
    }
    public void enQueue(PCB p)
    {
        a.addFirst(p);
    }
    public void enQueue(QueueS s)
    {
        while(!s.isEmpty())
            this.enQueue(s.deQueue());

    }
    public void enQueue(LinkedList<PCB> p)
    {
        while(!p.isEmpty())
            a.addFirst(p.pollLast());
    }
    public PCB deQueue()
    {
        return a.pollLast();
    }
    public Boolean removeAll()
    {
        while(!a.isEmpty())
            a.removeFirst();
        return a.isEmpty();
    }
    public void sortByShortestJob()
    {
        ArrayList<PCB> pcbs=new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            pcbs.add(a.get(i));
        }
        for(int i=0 ; i<pcbs.size()-1 ; i++)
        {
            for(int j=i+1 ; j<pcbs.size() ;j++)
            {
                PCB j1 = pcbs.get(i);
                PCB j2 = pcbs.get(j);
                if(j2.isShort(j1))
                {
                    pcbs.set(i, j2);
                    pcbs.set(j, j1);
                }
            }
        }
        this.removeAll();
        for (int i = 0; i < pcbs.size(); i++) {
            this.enQueue(pcbs.get(i));
        }
    }

    public void sortByFirstArrive()
    {
        ArrayList<PCB> mainlist=new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            mainlist.add(a.get(i));
        }
        for(int i=0 ; i<mainlist.size()-1 ; i++)
        {
            for(int j=i+1 ; j<mainlist.size() ;j++)
            {
                PCB j1 = mainlist.get(i);
                PCB j2 = mainlist.get(j);
                if(j2.isFirstArrived(j1))
                {
                    mainlist.set(i, j2);
                    mainlist.set(j, j1);
                }
            }
        }
        this.removeAll();
        for (int i = 0; i < mainlist.size(); i++) {
            this.enQueue(mainlist.get(i));
        }

    }
    public Boolean isHere(PCB a)
    {
        for (int i = 0; i < this.a.size(); i++)
            if(this.a.get(i)==a)
                return true;

        return false;
    }
    public Boolean removeAllInHereWhereExistIn(QueueS s)
    {
        for (int i = 0; i < this.a.size(); i++) {
            if(s.isHere(this.a.get(i)))
                this.a.remove(i);
                 return true;
        }
        return false;

    }
    public String getIds()
    {
        String s="";
        for (int i = a.size() - 1; i >= 0; i--) {
            s+=a.get(i).getProcessID()+" ";
        }
        return s;
    }
    public Boolean isEmpty()
    {
        return this.a.isEmpty();
    }
    public QueueS getAvailableJobsByShortestAt(int t)
    {
        QueueS ret=new QueueS();
        this.sortByFirstArrive();

        for (int i = this.a.size() - 1; i >= 0; i--) {
            if(this.a.get(i).getArrivalTime() == t)
            {
                ret.enQueue(this.a.get(i));
                this.a.remove(i);
            }

        }
        ret.sortByShortestJob();
       return ret;
    }
    public boolean isThereJobAvailableAt(int t)
    {
        for (int i = this.a.size() - 1; i >= 0; i--) {
            if(this.a.get(i).getArrivalTime() <= t)
                return true;
        }
        return false;
    }
    public void addLast(PCB b)
    {
        this.a.addLast(b);
    }




}
