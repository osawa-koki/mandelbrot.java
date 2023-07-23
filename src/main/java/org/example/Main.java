package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.nio.file.Paths;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  public static void main(String[] args) {
    // Press Opt+Enter with your caret at the highlighted text to see how
    // IntelliJ IDEA suggests fixing it.
    System.out.println("Hello and welcome!");

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(Paths.get("./config.xml").toFile());
      Element config = document.getDocumentElement();
      int width = Integer.parseInt(config.getElementsByTagName("width").item(0).getTextContent());
      int height = Integer.parseInt(config.getElementsByTagName("height").item(0).getTextContent());
      double x_min = Double.parseDouble(config.getElementsByTagName("x_min").item(0).getTextContent());
      double x_max = Double.parseDouble(config.getElementsByTagName("x_max").item(0).getTextContent());
      double y_min = Double.parseDouble(config.getElementsByTagName("y_min").item(0).getTextContent());
      double y_max = Double.parseDouble(config.getElementsByTagName("y_max").item(0).getTextContent());
      int iteration = Integer.parseInt(config.getElementsByTagName("iteration").item(0).getTextContent());
      int incremental_step = Integer.parseInt(config.getElementsByTagName("incremental_step").item(0).getTextContent());
      int threshold = Integer.parseInt(config.getElementsByTagName("threshold").item(0).getTextContent());
      String output = config.getElementsByTagName("output").item(0).getTextContent();

      Mandelbrot.setBasePath("./public");

      Mandelbrot mandelbrot = new Mandelbrot(width, height, x_min, x_max, y_min, y_max, iteration, incremental_step, threshold, output);
      mandelbrot.debugOutput();
      mandelbrot.draw();
      mandelbrot.save();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
