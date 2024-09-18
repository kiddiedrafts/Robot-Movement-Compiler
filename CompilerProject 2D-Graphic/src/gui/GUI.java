package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private final int lx;
    private final int ly;
    private Button [][] buttons;
    private ArrayList<Point> points = new ArrayList<>();
    private int pointer = 0;
    public GUI(int lx, int ly, int ux, int uy, int x, int y, int[][] array) {
        super();
        this.lx = lx;
        this.ly = ly;
        int cols = ux - lx + 1;
        int rows = uy - ly + 1;
        this.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(rows,cols));
        buttons = new Button[rows][cols];
        for (int i = rows - 1; i >= 0; i--){
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new Button((lx + j) + "," + (ly + i));
                buttons[i][j].setSize(50,50);
                buttons[i][j].setBackground(Color.white);
                for (int k = 0; k < array.length; k++) {
                    if (array[k][0] == lx + j && array[k][1] == ly + i){
                        buttons[i][j].setBackground(Color.BLACK);
                    }
                }
                topPanel.add(buttons[i][j]);
            }
        }
        add(topPanel,BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2));

        JButton left = new JButton("Previous");
        JButton right = new JButton("Next");
        left.addActionListener(I-> go(-1));
        right.addActionListener(I->go(+1));
        bottomPanel.add(left);
        bottomPanel.add(right);

        add(bottomPanel,BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void go(int num) {
        if (pointer + num < 0 || pointer + num >= points.size())
            return;
        Point current = points.get(pointer);
        buttons[current.y-ly][current.x-lx].setBackground(Color.white);

        pointer += num;
        current = points.get(pointer);

        buttons[current.y - ly][current.x - lx].setBackground(Color.yellow);

    }

    public void addPoint(int x, int y) {
        Point point = new Point(x, y);
        if(points.size() == 0 || !points.get(points.size() - 1).equals(point))
            points.add(point);
        if(points.size() == 1){
            go(0);
        }
    }
}
