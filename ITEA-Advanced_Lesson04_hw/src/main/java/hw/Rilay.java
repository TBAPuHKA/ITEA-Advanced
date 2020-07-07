package hw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UTFDataFormatException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rilay extends JFrame {

	private JLabel jl1, jl2;
	private TextArea ta1, ta2;

	Rilay() {
		super("Once upon a time");
		setLayout(new GridLayout(2, 2));

		URL url = getClass().getClassLoader().getResource("2.gif");

		jl1 = new JLabel(new ImageIcon("1.gif"));
		add(jl1);
		jl2 = new JLabel(new ImageIcon(url));
		add(jl2);
		ta1 = new TextArea();
		add(ta1);
		ta1.disable();
		ta2 = new TextArea();
		add(ta2);
		ta2.disable();

		try (Scanner in = new Scanner(new FileReader("1.txt"));) {
			in.useDelimiter("\n");
			while (in.hasNext()) {
				ta1.append(in.next() + "\n");
			}
		} catch (FileNotFoundException r) {
			r.printStackTrace();
		}
		try (Scanner in = new Scanner(getClass().getClassLoader().getResourceAsStream("2.txt"))) {
			in.useDelimiter("\n");
			while (in.hasNext()) {
				ta2.append(in.next() + "\n");
			}
		}

		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(new Dimension(1000, 1000));
		setResizable(true);
	}

}
