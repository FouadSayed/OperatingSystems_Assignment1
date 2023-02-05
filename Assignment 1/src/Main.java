import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static JLabel value6 = new JLabel("0 ms");
    public static JLabel value5 = new JLabel("0");
    public static JLabel value4 = new JLabel("0");
    public static String RESULT_FILE_NAME;


    public static void main(String[] args) {
        JFrame f = new JFrame("Prime Number Generator");

        f.setSize(500, 500);


        JTextField value = new JTextField();
        JLabel l1 = new JLabel("Max Prime Number");
        l1.setBounds(100, 50, 120, 30);
        value.setBounds(230, 50, 140, 30);
        f.add(value);
        f.add(l1);

        JTextField value2 = new JTextField();
        JLabel l12 = new JLabel("Buffer Size:");
        l12.setBounds(100, 90, 80, 30);
        value2.setBounds(230, 90, 140, 30);
        f.add(value2);
        f.add(l12);

        JTextField value3 = new JTextField();
        JLabel l13 = new JLabel("Output File:");
        l13.setBounds(100, 130, 80, 30);
        value3.setBounds(230, 130, 140, 30);
        f.add(value3);
        f.add(l13);


        JDialog d = new JDialog(f, "Error", true);
        d.setLayout(new FlowLayout());
        JButton ll = new JButton("OK");
        ll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });
        d.add(new JLabel("Please enter valid values"));
        d.add(new JLabel("Click button to continue."));
        d.add(ll);

        d.setSize(300, 300);


        JButton b = new JButton("Start Producer");
        b.setBounds(140, 180, 200, 30);
        f.add(b);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent u) {
                if (value.getText().isEmpty() || value2.getText().isEmpty() || value3.getText().isEmpty()) {
                    d.setVisible(true);
                } else {

                    int PRIME_NUMBER_LIMIT = Integer.parseInt(value.getText());
                    int BufferSize = Integer.parseInt(value2.getText());
                    RESULT_FILE_NAME = value3.getText();
                    /* Start the time counter */
                    long start = System.currentTimeMillis();
                    /* Clear the result file from any previous result */
                    Producer producer = new Producer(BufferSize, PRIME_NUMBER_LIMIT, RESULT_FILE_NAME, start);
                    Consumer consumer = new Consumer(producer);
                    producer.start();
                    consumer.start();

                    while (Producer.counter_ <= PRIME_NUMBER_LIMIT ) {

                        value4.setText(String.valueOf(Producer.last_pr));
                        value5.setText(String.valueOf(Producer.counter_primes));
                        // value6.setText(String.valueOf(elapsedTime(start))+" ms");



                    }




                }}});
        JSeparator sep = new JSeparator();
        sep.setBounds(0, 230, 600, 10);
        f.add(sep, BorderLayout.CENTER);



        JLabel l14 = new JLabel("The Largest Prime Number:");
        l14.setBounds(100, 280, 200, 30);
        value4.setBounds(280, 280, 140, 30);
        f.add(value4);
        f.add(l14);


        JLabel l15 = new JLabel("# of Prime Numbers:");
        l15.setBounds(100, 320, 200, 30);
        value5.setBounds(280, 320, 140, 30);
        f.add(value5);
        f.add(l15);


        JLabel l16 = new JLabel("time elapsed:");
        l16.setBounds(100, 360, 200, 30);
        value6.setBounds(280, 360, 200, 30);
        f.add(value6);
        f.add(l16);
        f.add(new JLabel(""));

        f.setSize(500, 500);

        f.setVisible(true);


    }}

//