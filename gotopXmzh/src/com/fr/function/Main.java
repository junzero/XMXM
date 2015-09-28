package com.fr.function;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Main extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		initComponents();
	}

	private void initComponents() {
		setTitle("国通日戳");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel() {
				private Seal seal = new Seal();

				protected void paintComponent(java.awt.Graphics g) {
					super.paintComponent(g);

					g.translate((getWidth() - seal.getWidth()) / 2,
							(getHeight() - seal.getHeight()) / 2);

					seal.draw((Graphics2D) g,"福建莆田","机要01");
				};
			};
			panel.setBackground(Color.WHITE);
		}
		return panel;
	}
}
