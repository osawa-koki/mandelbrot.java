package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

enum ColorMode {
  RED,
  GREEN,
  BLUE
}

public class Mandelbrot {
  static String base_path = "";
  int width;
  int height;
  double x_min;
  double x_max;
  double y_min;
  double y_max;
  int iteration;
  int incremental_step;
  ColorMode color_mode;
  int threshold;
  String output;
  Image image;

  public static void setBasePath(String base_path) {
    Mandelbrot.base_path = base_path;
  }

  public Mandelbrot(int width, int height, double x_min, double x_max, double y_min, double y_max, int iteration, int incremental_step, ColorMode color_mode, int threshold, String output) {
    this.width = width;
    this.height = height;
    this.x_min = x_min;
    this.x_max = x_max;
    this.y_min = y_min;
    this.y_max = y_max;
    this.iteration = iteration;
    this.incremental_step = incremental_step;
    this.color_mode = color_mode;
    this.threshold = threshold;
    this.output = output;
    this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  public void debugOutput() {
    System.out.println("width: " + width);
    System.out.println("height: " + height);
    System.out.println("x_min: " + x_min);
    System.out.println("x_max: " + x_max);
    System.out.println("y_min: " + y_min);
    System.out.println("y_max: " + y_max);
    System.out.println("iteration: " + iteration);
    System.out.println("incremental_step: " + incremental_step);
    System.out.println("color_mode: " + color_mode);
    System.out.println("threshold: " + threshold);
    System.out.println("base_path: " + base_path);
    System.out.println("output: " + output);
  }

  public void draw() {
    Graphics graphics = image.getGraphics();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double zx = x_min + (x_max - x_min) * x / width;
        double zy = y_min + (y_max - y_min) * y / height;
        Complex z = new Complex(0, 0);
        int i = 0;
        int _color;
        while (z.abs() < threshold && i < iteration) {
          z = z.mul(z).add(new Complex(zx, zy));
          i++;
        }
        if (i == iteration) {
          _color = Rgb2Int(0, 0, 0);
        } else {
          switch (color_mode) {
            case RED -> _color = Rgb2Int(255, 255 - i * incremental_step, 255 - i * incremental_step);
            case GREEN -> _color = Rgb2Int(255 - i * incremental_step, 255, 255 - i * incremental_step);
            case BLUE -> _color = Rgb2Int(255 - i * incremental_step, 255 - i * incremental_step, 255);
            default -> _color = Rgb2Int(0, 0, 0);
          }
        }
        graphics.setColor(new Color(_color));
        graphics.fillRect(x, y, 1, 1);
      }
    }
  }

  public void save() {
    try {
      ImageIO.write((BufferedImage) image, "png", Paths.get(base_path, output).toFile());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getSavedPath() {
    return Paths.get(base_path, output).toString();
  }

  private int Rgb2Int(int r, int g, int b) {
    r = r % 255;
    g = g % 255;
    b = b % 255;
    return (r << 16) + (g << 8) + b;
  }
}
