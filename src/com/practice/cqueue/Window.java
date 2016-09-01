package com.practice.cqueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.*;

public class Window extends JFrame {

    private CQueue queue;

    public Window() {
        super("Cola Circular");
        initComponents(this);
    }

    private void initComponents(JFrame frame) {
        frame.setSize(new Dimension(450, 450));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        //Validates if the queue dimension is a valid positive integer

        int queueLength = 0;
        boolean error;
        do {
            try{
                queueLength = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la dimension de la Cola"));
                if (queueLength <= 0)
                    error = true;
                else
                    error = false;
            }
            catch(Exception e){
                error = true;
            }
            if(error)
                JOptionPane.showMessageDialog(null,"La dimension tiene que ser un entero positivo","Error!",JOptionPane.ERROR_MESSAGE);
        } while (error);
        queue = new CQueue(queueLength);
        CirclePanel circlePanel = new CirclePanel(queueLength);

        JPanel widgetsPane = new JPanel();
        widgetsPane.setLayout(new FlowLayout());
        JLabel dataLabel = new JLabel("Dato a encolar");
        JTextField dataField = new JTextField(5);
        JButton buttonEnqueue = new JButton("Encolar");
        JButton buttonDequeue = new JButton("Desencolar");



        buttonEnqueue.addActionListener(e -> {
            boolean failure;

            if(dataField.getText().isEmpty()){
                failure = true;
            }
            else {
                try {
                    int dataToQueue = Integer.parseInt(dataField.getText());
                    queue.enqueue(dataToQueue);
                    System.out.println("Dato encolado: " + dataToQueue);
                    failure = false;
                } catch (Exception ex) {
                    failure = true;
                }
                dataField.setText(null);
            }
            if(failure)
                JOptionPane.showMessageDialog(null, "Tiene que agregar un entero!", "Error", JOptionPane.ERROR_MESSAGE);
        });


        buttonDequeue.addActionListener(e -> {
            if(queue.dequeue()!=-1){
                System.out.println("Elemento desencolado "+queue.dequeue());
            }
        });

        widgetsPane.add(dataLabel);
        widgetsPane.add(dataField);
        widgetsPane.add(buttonEnqueue);
        widgetsPane.add(buttonDequeue);

        container.add(circlePanel, BorderLayout.CENTER);
        container.add(widgetsPane, BorderLayout.SOUTH);
    }
}
