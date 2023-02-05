import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread{
    private static int bufferSize;
    public static int max_pr_num;
    public static String file_name;
    public static int last_pr;
    public static int counter_primes=0;
    static int counter_=1;
    public List<Integer> primes=new ArrayList<>();
    static String Final_Time;
    long start;
    public Producer(int bufferSize,int max_pr_num,String file_name,long start){
        this.bufferSize=bufferSize;
        this.max_pr_num=max_pr_num;
        this.file_name=file_name;
        this.start=start;
    }
    public void run(){
        try{
            appendToFile("prime numbers:",true);
            while (counter_<=max_pr_num){

                produce();
            }
            Final_Time=CalculateTime(start);
            Main.value6.setText(String.valueOf(Final_Time));
            // gui add time to scree
            appendToFile(Final_Time,true);
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    public static boolean appendToFile(String _text, boolean _append) {
        BufferedWriter out = null;
        boolean fileAppended = true;
        try {
            FileWriter fstream = new FileWriter(file_name, _append);
            out = new BufferedWriter(fstream);
            if (_append)
                _text += "\n";
            out.write(_text);
        } catch (IOException e) {
            fileAppended = false;
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    fileAppended = false;
                    e.printStackTrace();
                }
            }
        }
        return fileAppended;
    }

    public static String CalculateTime(long start){
        long end=System.currentTimeMillis();
        float ExecutionTime=(end-start)/1000f;
        return ExecutionTime+" seconds";
    }
    private int GetPrimeNumbers() {
        for(++counter_;counter_<max_pr_num;counter_++){
            for(int i=2;i<counter_;i++){
                if (counter_%i == 0){
                    return -1;
                }
            }
            return counter_;
        }
        return -1;
    }
    private synchronized void produce () throws InterruptedException {
        while(primes.size()==bufferSize){
            System.out.println("Producer is waiting");
            wait();
        }
        int prime=GetPrimeNumbers();
        if(prime!=-1){
            appendToFile(String.valueOf(prime),true);
            primes.add(prime);
            counter_primes++;
            if(prime>last_pr){
                last_pr=prime;
            }
            System.out.println("Producer produce: "+ prime);
            if(primes.size()==bufferSize){
                System.out.println("Producer is waiting");
                wait();

            }
        }
        notify();
    }
    public synchronized void consume() throws InterruptedException {
        if(primes.size()==0){
            System.out.println("Consumer is waiting");
            wait();
        }else{

            int removed_prime=primes.remove(0);
            System.out.println("Consumer Remove prime number: " + removed_prime);
        }

        notify();
    }



}