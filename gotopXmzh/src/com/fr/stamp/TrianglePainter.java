package com.fr.stamp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TrianglePainter extends JPanel {
    private Triangle triangle;

    public TrianglePainter() {
        triangle = new Triangle(new Point(20, 20), new Point(100, 100), new Point(10, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        triangle.draw(g2d);
        
        g2d.translate(150, 150);
        triangle.fill(g2d);
    }

    private static void createGuiAndShow() {
        JFrame frame = new JFrame("三角形");
        frame.getContentPane().add(new TrianglePainter());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGuiAndShow();
            }
        });
    }
}

// 定义一个三角形类
class Triangle {
    private Point p1;
    private Point p2;
    private Point p3;

    private GeneralPath path;

    // 使用三个点构建一个三角形
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.path = buildPath();
    }

    // 绘制三角形边
    public void draw(Graphics2D g2d) {
        g2d.draw(path);
    }

    // 填充三角形
    public void fill(Graphics2D g2d) {
        g2d.fill(path);
    }

    // 创建三角形外形的路径
    private GeneralPath buildPath() {
        path = new GeneralPath();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.closePath();

        return path;
    }
}