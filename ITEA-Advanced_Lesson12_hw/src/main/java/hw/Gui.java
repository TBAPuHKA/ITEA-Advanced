package hw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class Gui extends Application {
	private static final String CSSFILE = "application.css";
	private TextArea jta;
	private TextField jtf;
	private File file = new File(".");
	private File[] files = file.listFiles();
	private String border = "==========";

	@Override
	public void start(Stage primaryStage) {
		try {

			primaryStage.setTitle("Legends Never Die");
			BorderPane root = new BorderPane();

			jta = new TextArea();
			root.setCenter(jta);
			jta.setDisable(true);

			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(jta);
			scrollPane.setFitToWidth(true);

			jtf = new TextField();
			root.setBottom(jtf);
			jtf.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					jta.appendText(jtf.getText() + "\n");

					if (jtf.getText().startsWith("cd ") | jtf.getText().startsWith("copy ")
							| jtf.getText().contentEquals("cd..") | jtf.getText().contentEquals("dir")
							| jtf.getText().contentEquals("help") | jtf.getText().contentEquals("unicorns")) {

						if (jtf.getText().startsWith("cd ")) {
							String temp = jtf.getText().substring(3);
							File tempFile = new File(file.getAbsolutePath() + "\\" + temp);
							if (tempFile.exists()) {
								if (tempFile.isDirectory()) {
									file = new File(file.getAbsolutePath() + "/" + temp);
									jta.appendText(file.getAbsoluteFile().toString() + "\n");
									files = file.listFiles();
								} else {
									jta.appendText(temp +" contents:"+"\n");
									Scanner in;
									try {
										in = new Scanner(new FileReader(file.getAbsolutePath() + "/" + temp));
										in.useDelimiter("\n");
										while (in.hasNext()) {
											jta.appendText(in.next() + "\n");
										}
									} catch (FileNotFoundException r) {
										r.printStackTrace();
									}
								}
							} else {
								jta.appendText(border + "\n");
								jta.appendText("Path is not founded" + "\n");
								jta.appendText(border + "\n");
							}
						}
						if (jtf.getText().startsWith("copy ")) {
							String tempIn = jtf.getText().substring(5, jtf.getText().indexOf(" ", 5));
							String tempOut = jtf.getText().substring((jtf.getText().indexOf(" ", 5)) + 1);

							try (InputStream in = new FileInputStream(file.getAbsolutePath() + "\\" + tempIn);
									OutputStream out = new FileOutputStream(file.getAbsolutePath() + "\\" + tempOut);) {
								int c;
								while ((c = in.read()) != -1) {
									out.write(c);
								}
							} catch (IOException r) {
								jta.appendText(border + "\n");
								jta.appendText("File is not founded" + "\n");
								jta.appendText(border + "\n");
							}

						}

						if (jtf.getText().contentEquals("dir")) {
							jta.appendText(file.getAbsoluteFile().toString() + "\n");
							files = file.listFiles();
							jta.appendText(border + "\n");
							jta.appendText("Files list is:" + "\n");
							for (File f : files) {
								jta.appendText(f.getName().toString() + "\n");
							}
							jta.appendText(border + "\n");
						}
						if (jtf.getText().contentEquals("cd..")) {

							if (file.getAbsoluteFile().getParentFile() != null) {
								file = file.getParentFile();
								files = file.listFiles();
								jta.appendText(border + "\n");
								jta.appendText(file.getAbsoluteFile().toString() + "\n");
								jta.appendText(border + "\n");

							}
						}
						if (jtf.getText().contentEquals("help")) {
							jta.appendText("[dir] for watch file list in directory" + "\n" + "[cd..] for change directory" + "\n"
									+ "[cd ]+[DirectoryName] for change directory" + "\n"
									+ "[cd ]+[FileName] for read text file" + "\n"
									+ "[copy ]+[FileName ]+[FileName] for copy files" + "\n"
									+ "[unicorns] for remove dota2 & install DotaAllStars with wc3tft" + "\n");

							jta.appendText(border + "\n");
						}
						if (jtf.getText().contentEquals("unicorns")) {
							jta.appendText("we will release this function in next update" + "\n");
						}

					} else {
						jta.appendText("wrong syntax, comrade!" + "\n");
					}

					jtf.setText("");


					
				}
			});
			

			Scene scene = new Scene(root, 400, 800);
			scene.getStylesheets().add(getClass().getClassLoader().getResource(CSSFILE).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(true);
			
			jta.appendText("use [help] for help" + "\n");
			
			file = file.getAbsoluteFile().getParentFile();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
