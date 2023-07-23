package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.nio.file.Paths;

public class Main {
  public static String basePath = "./public";

  public static void main(String[] args) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(Paths.get("./config.xml").toFile());
      Element config = document.getDocumentElement();

      Mandelbrot.setBasePath(basePath);

      Mandelbrot[] mandelbrots = configParse(config);
      String[] paths = new String[mandelbrots.length];

      for (int i = 0; i < mandelbrots.length; i++) {
        mandelbrots[i].debugOutput();
        mandelbrots[i].draw();
        mandelbrots[i].save();
        paths[i] = mandelbrots[i].getSavedPath();
      }

      String pathsString = String.join(",", paths);
      FileWriter file = new FileWriter(Paths.get(basePath, "paths.txt").toFile());
      file.write(pathsString);
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Mandelbrot[] configParse(Element config) {
    NodeList mandelbrots = config.getElementsByTagName("mandelbrot");
    Mandelbrot[] mandelbrotList = new Mandelbrot[mandelbrots.getLength()];
    for (int i = 0; i < mandelbrots.getLength(); i++) {
      Element mandelbrot = (Element) mandelbrots.item(i);
      int width = Integer.parseInt(mandelbrot.getElementsByTagName("width").item(0).getTextContent());
      int height = Integer.parseInt(mandelbrot.getElementsByTagName("height").item(0).getTextContent());
      double x_min = Double.parseDouble(mandelbrot.getElementsByTagName("x_min").item(0).getTextContent());
      double x_max = Double.parseDouble(mandelbrot.getElementsByTagName("x_max").item(0).getTextContent());
      double y_min = Double.parseDouble(mandelbrot.getElementsByTagName("y_min").item(0).getTextContent());
      double y_max = Double.parseDouble(mandelbrot.getElementsByTagName("y_max").item(0).getTextContent());
      int iteration = Integer.parseInt(mandelbrot.getElementsByTagName("iteration").item(0).getTextContent());
      int incremental_step = Integer.parseInt(mandelbrot.getElementsByTagName("incremental_step").item(0).getTextContent());
      ColorMode color_mode = ColorMode.valueOf(mandelbrot.getElementsByTagName("color_mode").item(0).getTextContent());
      int threshold = Integer.parseInt(mandelbrot.getElementsByTagName("threshold").item(0).getTextContent());
      String output = mandelbrot.getElementsByTagName("output").item(0).getTextContent();
      mandelbrotList[i] = new Mandelbrot(width, height, x_min, x_max, y_min, y_max, iteration, incremental_step, color_mode, threshold, output);
    }
    return mandelbrotList;
  }
}
