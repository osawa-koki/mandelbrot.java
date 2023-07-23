package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  public static void main(String[] args) {
    // Press Opt+Enter with your caret at the highlighted text to see how
    // IntelliJ IDEA suggests fixing it.
    System.out.printf("Hello and welcome!");

    // 100x100の青色の画像を作成する。
    int width = 100;
    int height = 100;
    int color = 0x0000ff;
    Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics graphics = image.getGraphics();
    graphics.setColor(new Color(color));
    graphics.fillRect(0, 0, width, height);
    try {
      ImageIO.write((RenderedImage) image, "png", new File("./public/blue.png"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
