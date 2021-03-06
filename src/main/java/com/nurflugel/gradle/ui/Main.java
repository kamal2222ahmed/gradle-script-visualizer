package com.nurflugel.gradle.ui;

import static com.nurflugel.util.Util.VERSION;

import javafx.application.Application;

import static javafx.fxml.FXMLLoader.load;

import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.net.URL;

import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

public class Main extends Application
{
  public static final String TITLE_TEXT = "Gradle Script Visualizer v";

  /** @param  args  the command line arguments */
  public static void main(String... args)
  {
    try
    {
      launch(Main.class, (String[]) null);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void start(Stage primaryStage)
  {
    try
    {  // UI related stuff

      URL        resource = Main.class.getResource("GradleVisualizerUi.fxml");
      AnchorPane page     = load(resource);
      Scene      scene    = new Scene(page);

      primaryStage.setScene(scene);
      primaryStage.setTitle(TITLE_TEXT + VERSION);
      primaryStage.show();
    }
    catch (Exception ex)
    {
      getLogger(Main.class.getName()).log(SEVERE, null, ex);
    }
  }
}
