package com.practice.cqueue;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

        int queueLength;
        do {
            queueLength = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la dimension de la Cola"));
            if (queueLength <= 0)
                JOptionPane.showMessageDialog(null, "Error: La logitud debe ser mayor a cero", "Error",
                        JOptionPane.ERROR_MESSAGE);
        } while (queueLength <= 0);

        queue = new CQueue(queueLength);
        CirclePanel circlePanel = new CirclePanel(queueLength);

        JPanel widgetsPane = new JPanel();
        widgetsPane.setLayout(new FlowLayout());
        JLabel dataLabel = new JLabel("Dato a encolar");
        JTextField dataField = new JTextField(5);
        JButton buttonEnqueue = new JButton("Encolar");
        JButton buttonDequeue = new JButton("Desencolar");

        buttonEnqueue.addActionListener(e -> {
            int dataToQueue = Integer.parseInt(dataField.getText());
            queue.enqueue(dataToQueue);

            dataField.setText(null);
            System.out.println("Dato encolado: " + dataToQueue);
        });

        buttonDequeue.addActionListener(e -> System.out.println("Dato desencolado: " + queue.dequeue()));

        widgetsPane.add(dataLabel);
        widgetsPane.add(dataField);
        widgetsPane.add(buttonEnqueue);
        widgetsPane.add(buttonDequeue);

        container.add(circlePanel, BorderLayout.CENTER);
        container.add(widgetsPane, BorderLayout.SOUTH);
    }
}
