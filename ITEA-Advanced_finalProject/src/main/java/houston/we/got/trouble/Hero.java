package houston.we.got.trouble;

import java.net.URL;
import java.util.Random;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Hero {
	private Rectangle RECT = new Rectangle(600, 100, 100, 100);
	private ImagePattern heroIcon;
	private String[] icons = {"blitz","ahri","gnar","jinx","leesin","lulu","riven","sona","teemo","vi","zed","ziggs"};
	private Random random = new Random();

	public Hero() {
		this.heroIcon=generateIcon();

	}

	private ImagePattern generateIcon() {
		String generate = icons[random.nextInt(12)];
		ImagePattern icon = new ImagePattern(new Image("/images/"+generate+".png"));
		return icon;
	}

	public ImagePattern getHeroIcon() {
		return heroIcon;
	}

	public Rectangle getRECT() {
		return RECT;
	}
	
	
}
