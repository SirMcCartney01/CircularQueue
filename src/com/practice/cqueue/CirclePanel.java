package com.practice.cqueue;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;

import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.ArrayList;

public class CirclePanel extends JPanel {

    private int length;
    private List<Ellipse2D> ellipseList;

    public CirclePanel(int length) {
        super();
        this.setPreferredSize(new Dimension(256, 256));
        this.length = length;
        this.ellipseList = new ArrayList<>(length);
        initPanel(this);
    }

    private void initPanel(JPanel panel) {
        panel.setBackground(Color.WHITE);

        for (int i = 0; i < length; i++)
            ellipseList.add(new Ellipse2D.Double());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g.create();

        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics2D.setColor(Color.BLACK);

        int width = getWidth() / 2, height = getHeight() / 2;
        int min = Math.min(width, height);
        int r = 4 * min / 5;
        int r2 = Math.abs(min - r) / 2;
        graphics2D.drawOval(width - r, height - r, 2 * r, 2 * r);

        graphics2D.setColor(Color.RED);
        for (int i = 0; i < length; i++) {
            double t = 2 * Math.PI * i / length;
            int x = (int) Math.round(width + r * Math.cos(t));
            int y = (int) Math.round(height + r * Math.sin(t));
            graphics2D.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
        }
    }
}
