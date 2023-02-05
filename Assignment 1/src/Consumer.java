public class Consumer extends Thread{
    private Producer producer;
    public Consumer(Producer producer){
        this.producer = producer;
    }
    @Override
    public void run() {

        while (Producer.counter_!= Producer.max_pr_num ){
            try {
                producer.consume();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

}
