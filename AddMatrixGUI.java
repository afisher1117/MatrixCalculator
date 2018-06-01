import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class AddMatrixGUI{
	
	private JTextArea matrixA;
	private JTextArea matrixB;
	private JTextArea matrixC;
	public Operations ops = new Operations();
	
	public AddMatrixGUI() {
		
        JFrame frame = new JFrame("Matrix Calculator");
        Component stuff = this.initComponents();
        frame.getContentPane().add(stuff, BorderLayout.CENTER);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		frame.pack();
		frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
	
	private Component initComponents() {
		
		JTextArea matrixA = new JTextArea();
		JTextArea matrixB = new JTextArea();
		JTextArea matrixC = new JTextArea();
		
		JPanel matrixPanel = new JPanel();
		matrixPanel.setLayout(new BoxLayout(matrixPanel, BoxLayout.X_AXIS));
		matrixPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		matrixPanel.add(matrixPane("Matrix A", matrixA));
		matrixPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		matrixPanel.add(matrixPane("Matrix B", matrixB));
		matrixPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		matrixPanel.add(matrixPane("Matrix C", matrixC));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		buttonPane.setLayout(new GridLayout(4, 1));
		JButton addButton = new JButton("A + B = C");
		JButton subtractButton = new JButton("A - B = C");
		JButton multiplyButton = new JButton("A * B = C");
		JButton instructionsButton = new JButton("Press for instructions");
		buttonPane.add(addButton);
		buttonPane.add(subtractButton);
		buttonPane.add(multiplyButton);
		buttonPane.add(instructionsButton);
		
		JLabel infoLabel = new JLabel("<html><p align=\"center\">Adding and subtracting matrices require matrices of the same size.<br>"
						+ "Multiplying Matrices can be done as long as the matrices are in the format (N x M) (M x P)<br> If the columns of matrix A dont match the"
						+ "rows of matrix B, they cannot be multiplied.<br> Also, if you wish to multiply the matrix by a scalar, enter the scalar into matrix A"
						+ "and enter the matrix you wish to multiply into matrix B.</p></html>");
		infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ops.displayMatrix(ops.addMatrix(ops.getMatrix(matrixA), ops.getMatrix(matrixB)), matrixC);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		subtractButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ops.displayMatrix(ops.subtractMatrix(ops.getMatrix(matrixA), ops.getMatrix(matrixB)), matrixC);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		multiplyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(ops.checkScalar(matrixA)){
						ops.displayMatrix(ops.scalarMultiply(matrixA, ops.getMatrix(matrixB)), matrixC);
					}
					else {
						ops.displayMatrix(ops.multiplyMatrix(ops.getMatrix(matrixA), ops.getMatrix(matrixB)), matrixC);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		instructionsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(instructionsButton
						, infoLabel
						, "Instructions"
						, JOptionPane.INFORMATION_MESSAGE, null);
				
			}
		});
		
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(matrixPanel);
		pane.add(buttonPane);

		JPanel fpane = new JPanel();
		fpane.setLayout(new BorderLayout());
		fpane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		fpane.add("Center", pane);

		return fpane;
	}	
    
	
	private JPanel matrixPane(String str, JTextArea matrix) {
		JScrollPane scrollPane = new JScrollPane(matrix);
		int size = 150;

		scrollPane.setPreferredSize(new Dimension(size, size));
		JLabel label = new JLabel(str);
		label.setLabelFor(scrollPane);

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(label);
		pane.add(scrollPane);

		return pane;
	}
	
	public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddMatrixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMatrixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMatrixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMatrixGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new AddMatrixGUI(); 
            }
        });
    }

	public JTextArea getMatrixA() {
		return matrixA;
	}

	public void setMatrixA(JTextArea matrixA) {
		this.matrixA = matrixA;
	}

	public JTextArea getMatrixB() {
		return matrixB;
	}

	public void setMatrixB(JTextArea matrixB) {
		this.matrixB = matrixB;
	}

	public JTextArea getMatrixC() {
		return matrixC;
	}

	public void setMatrixC(JTextArea matrixC) {
		this.matrixC = matrixC;
	}
}
