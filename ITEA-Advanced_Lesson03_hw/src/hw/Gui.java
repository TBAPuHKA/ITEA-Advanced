package hw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Character.Subset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.*;

public class Gui extends JFrame implements ActionListener {
	private JTextArea jta;
	private JTextField jtf;
	private Path path = Paths.get(".");
	private Stream<Path> fileList;
	// private Stream<Path> filelist;
	private String border = "==========";

	public Gui() {
		super("Legends Never Die");
		setLayout(new BorderLayout());

		jta = new JTextArea();
		jta.setBackground(new Color(0, 0, 0));
		jta.setForeground(new Color(255, 255, 255));
		jta.setBorder(BorderFactory.createTitledBorder("Console"));
		jta.disable();

		jtf = new JTextField();
		jtf.setBackground(new Color(0, 0, 0));
		jtf.setForeground(new Color(255, 255, 255));
		jtf.addActionListener(this);

		add(jta);
		add(new JScrollPane(jta));
		add(jtf, BorderLayout.SOUTH);

		setSize(400, 800);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		jta.append("use [help] for help" + "\n");

		path = path.toAbsolutePath();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jtf) {

			jta.append(jtf.getText() + "\n");

			if (jtf.getText().startsWith("cd ") | jtf.getText().startsWith("copy ")
					| jtf.getText().contentEquals("cd..") | jtf.getText().contentEquals("dir")
					| jtf.getText().contentEquals("help") | jtf.getText().contentEquals("unicorns")) {

				if (jtf.getText().startsWith("cd ")) {
					String temp = jtf.getText().substring(3);
					Path tempPath = Paths.get(path.toAbsolutePath().toString() + "\\" + temp);
					if (Files.exists(tempPath)) {
						if (Files.isDirectory(tempPath)) {
							path = Paths.get(path.toAbsolutePath() + "/" + temp);
							jta.append(border + "\n");
							jta.append(path.toAbsolutePath().toString() + "\n");
							jta.append(border + "\n");
						} else {
							jta.append(border + "\n");
							jta.append(temp + " contents:" + "\n");
							Scanner in;
							try {
								in = new Scanner(new FileReader(path.toAbsolutePath().toString() + "/" + temp));
								in.useDelimiter("\n");
								while (in.hasNext()) {
									jta.append(in.next() + "\n");
								}
							} catch (FileNotFoundException r) {
								r.printStackTrace();
							}
							jta.append(border + "\n");
						}
					} else {
						jta.append(border + "\n");
						jta.append("Path is not founded" + "\n");
						jta.append(border + "\n");
					}
				}
				if (jtf.getText().startsWith("copy ")) {
					Path tempIn = Paths.get(
							path.toAbsolutePath() + "\\" + jtf.getText().substring(5, jtf.getText().indexOf(" ", 5)));
					Path tempOut = Paths.get(path.toAbsolutePath() + "\\"
							+ jtf.getText().substring((jtf.getText().indexOf(" ", 5)) + 1));

					try {
						Files.copy(tempIn, tempOut, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (jtf.getText().contentEquals("dir")) {
					path = path.toAbsolutePath();
					jta.append(path.toAbsolutePath() + "\n");

					jta.append(border + "\n");
					try {
						fileList = Files.list(path);
						fileList.forEach(p -> {
							jta.append(p.getFileName() + "\n");
						});
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jta.append(border + "\n");
				}

				if (jtf.getText().contentEquals("cd..")) {

					if (path.toAbsolutePath().getParent() != null) {
						path = path.getParent();
						// files = file.listFiles();
						jta.append(border + "\n");
						jta.append(path.toAbsolutePath().toString() + "\n");
						jta.append(border + "\n");

					}
				}
				if (jtf.getText().contentEquals("help")) {
					jta.append("[dir] for watch file list in directory" + "\n" + "[cd..] for change directory" + "\n"
							+ "[cd ]+[DirectoryName] for change directory" + "\n"
							+ "[cd ]+[FileName] for read text file" + "\n"
							+ "[copy ]+[FileName ]+[FileName] for copy files" + "\n"
							+ "[unicorns] for remove dota2 & install DotaAllStars with wc3tft" + "\n");

					jta.append(border + "\n");
				}
				if (jtf.getText().contentEquals("unicorns")) {
					jta.append("we will release this function in next update" + "\n");
				}

			} else {
				jta.append("wrong syntax, comrade!" + "\n");
			}

			jtf.setText("");

		}

	}

}
