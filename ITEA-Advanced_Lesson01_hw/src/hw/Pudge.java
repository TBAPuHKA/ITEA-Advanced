package hw;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Pudge extends javax.swing.JFrame implements ActionListener {
	
	private Locale loc1 = new Locale("en", "EN");
	private Locale loc2 = new Locale("ru", "RU");
	private ResourceBundle curCon = ResourceBundle.getBundle("config");
	private Locale currentLocale = new Locale(curCon.getString("loc1"), curCon.getString("loc2"));
	private Properties prop = new Properties();

	Pudge() {
		super("Pudge");

		jLabel0 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();

		Color clr = new Color(100, 100, 100);

		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Pudge");
		setPreferredSize(new java.awt.Dimension(500, 500));
		setResizable(false);

		jLabel0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pudge.jpg"))); // NOI18N

		jButton1.addActionListener(this);
		jButton1.setBackground(clr);
		jButton2.addActionListener(this);
		jButton2.setBackground(clr);
		jButton3.addActionListener(this);
		jButton3.setBackground(clr);
		jButton4.addActionListener(this);
		jButton4.setBackground(clr);
		jLabel1.setBackground(clr);
		jLabel2.setBackground(clr);
		jLabel3.setBackground(clr);
		changeLoc();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel0, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel0, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(1, 1, 1)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pack();

	}

	public void save() {
		Properties prop = new Properties();

		try (OutputStream out = new FileOutputStream("src/config.properties");) {

			if (currentLocale.equals(loc1)) {
				prop.setProperty("loc1", "en");
				prop.setProperty("loc2", "EN");
				prop.store(out, null);
			}

			if (currentLocale.equals(loc2)) {
				prop.setProperty("loc1", "ru");
				prop.setProperty("loc2", "RU");
				prop.store(out, null);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jButton1) {
			currentLocale = loc2;
			changeLoc();
		}
		if (ae.getSource() == jButton2) {
			currentLocale = loc1;
			changeLoc();
		}
		if (ae.getSource() == jButton3) {
			save();
		}
		if (ae.getSource() == jButton4) {
			currentLocale = new Locale(curCon.getString("loc1"), curCon.getString("loc2"));
			//все равно читает старую запись...
			changeLoc();

		}
	}

	private void changeLoc() {

		ResourceBundle curLoc = ResourceBundle.getBundle("pudge", currentLocale);
		jButton1.setText(curLoc.getString("button1"));
		jButton2.setText(curLoc.getString("button2"));
		jButton3.setText(curLoc.getString("button3"));
		jButton4.setText(curLoc.getString("button4"));
		jLabel1.setText(curLoc.getString("replic1"));
		jLabel2.setText(curLoc.getString("replic2"));
		jLabel3.setText(curLoc.getString("replic3"));
	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Pudge();
			}
		});

	}

	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel0;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

}
