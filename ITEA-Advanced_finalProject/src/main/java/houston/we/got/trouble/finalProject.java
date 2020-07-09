package houston.we.got.trouble;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class finalProject extends Application {

	private static final String CSS = "style.css";
	BorderPane root;
	Scene scene;
	Path path;
	final ImagePattern img = new ImagePattern(new Image("/images/blitz.png"));
	final ImagePattern timerBck = new ImagePattern(new Image("/images/TimerFrame.png"));
	int timerCount = 60;
	private final Text timerText = new Text(Integer.toString(timerCount));
	int liferCount = 0;
	private final Text liferText = new Text(Integer.toString(liferCount));
	Media media;
	AudioClip mediaPlayer;

	public void player() {

		Media media = new Media(getClass().getClassLoader().getResource("sounds/lol2.mp3").toString());
		AudioClip mediaPlayer = new AudioClip(media.getSource());
		mediaPlayer.play();
	}

	private void timerCount() {
		if (timerCount > 0) {
			timerCount--;
			timerText.setText(Integer.toString(timerCount));
		}
	}

	private void liferCount() {
		if (liferCount < 99) {
			liferCount++;
			liferText.setText(Integer.toString(liferCount));
		}
	}

	private void randomWay(Path path) {
		switch (new Random().nextInt(3)) {
		case 0:
			path.getElements().add(new CubicCurveTo(200, 5, 200, 5, 600, 50));
			break;
		case 1:
			path.getElements().add(new CubicCurveTo(400, 300, 400, 300, 600, 50));
			break;
		case 2:
			path.getElements().add(new CubicCurveTo(600, 595, 600, 595, 600, 50));
			break;

		}
	}

	public void start(Stage primaryStage) throws Exception{
		try {
			player();
			primaryStage.setTitle("Legends Never Die");
			BorderPane root = new BorderPane();
			root.setId("rootStart");
			Scene scene = new Scene(root, 800, 600);
			Button startB = new Button("JOIN");
			startB.setId("btStart");
			root.setCenter(startB);
			
			
			startB.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					try {
						game(primaryStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			
			
			scene.getStylesheets().add(getClass().getClassLoader().getResource(CSS).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			
			
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	public void game(Stage primaryStage) throws Exception {
		try {
			
			BorderPane root = new BorderPane();
			root.setId("rootGame");
// TIMER START ==================================================			
			Scene scene = new Scene(root, 800, 600);
			timerText.setX(700);
			timerText.setY(540);
			timerText.setId("timerCount");
			timerText.setEffect(new GaussianBlur());
			final Rectangle timerImg = new Rectangle(650, 450, 150, 150);
			timerImg.setFill(timerBck);
			root.getChildren().add(timerImg);
			root.getChildren().add(timerText);
// TIMER END ==================================================
// LIFER START ==================================================			
			liferText.setX(75);
			liferText.setY(540);
			liferText.setId("timerCount");
			liferText.setEffect(new GaussianBlur());
			final Rectangle liferImg = new Rectangle(25, 450, 150, 150);
			liferImg.setFill(timerBck);
			root.getChildren().add(liferImg);
			root.getChildren().add(liferText);
// LIFER END ==================================================		

			EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					if (timerCount > 0) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								
								try {
									System.out.println(Thread.activeCount());
									Hero hero = new Hero();
									System.out.println("MouseEvent:CLICKgenerate");
									hero.getRECT().setFill(hero.getHeroIcon());
									root.getChildren().add(hero.getRECT());

									Path path = new Path();
									path.getElements().add(new MoveTo(200, 550));
									randomWay(path);
									PathTransition pathTransition = new PathTransition();
									pathTransition.setDuration(Duration.seconds(5));
									pathTransition.setPath(path);
									pathTransition.setNode(hero.getRECT());
									pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
									pathTransition.setOnFinished(event1 -> {
										root.getChildren().remove(hero.getRECT());
										liferCount();
									});
									pathTransition.play();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}

				}
			};

			root.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

			Timeline fiveSecondsWonder = new Timeline(
					new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							if (timerCount > 0) {
								timerCount();
							}
							;
							if (timerCount <= 0 & liferCount < 99) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {

										try {
											Rectangle temp = new Rectangle(175, 100, 450, 500);
											temp.setFill(new ImagePattern(new Image("/images/loser.png")));
											root.getChildren().add(temp);
											Media media = new Media(getClass().getClassLoader()
													.getResource("sounds/lose.wav").toString());
											AudioClip mediaPlayer = new AudioClip(media.getSource());
											mediaPlayer.play();
											

											Button retryB = new Button("RETRY");
											retryB.setId("btStart");
											root.setBottom(retryB);
											retryB.setOnAction(new EventHandler<ActionEvent>() {
												
												@Override
												public void handle(ActionEvent event) {
													
													try {
														timerCount=60;
														liferCount=0;
														game(primaryStage);
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
											} );
											
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});

							}
							;
							if (timerCount <= 0 & liferCount >= 99 ) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {

										try {
											Rectangle temp = new Rectangle(25, 50, 800, 600);
											temp.setFill(new ImagePattern(new Image("/images/winner.png")));
											root.getChildren().add(temp);
											Media media = new Media(getClass().getClassLoader()
													.getResource("sounds/win.wav").toString());
											AudioClip mediaPlayer = new AudioClip(media.getSource());
											mediaPlayer.play();
											
											Button retryB = new Button("RETRY");
											retryB.setId("btStart");
											root.setBottom(retryB);
											retryB.setOnAction(new EventHandler<ActionEvent>() {
												
												@Override
												public void handle(ActionEvent event) {
													
													try {
														timerCount=60;
														liferCount=0;
														game(primaryStage);
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
											} );
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});

							}
							;
						}
					}));
			fiveSecondsWonder.setCycleCount(60);
			fiveSecondsWonder.play();

			scene.getStylesheets().add(getClass().getClassLoader().getResource(CSS).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
