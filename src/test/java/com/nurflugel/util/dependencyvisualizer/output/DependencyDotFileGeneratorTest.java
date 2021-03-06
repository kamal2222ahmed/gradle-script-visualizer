package com.nurflugel.util.dependencyvisualizer.output;

import com.nurflugel.util.dependencyvisualizer.parser.GradleDependencyParser;
import com.nurflugel.util.gradlescriptvisualizer.domain.Os;
import com.nurflugel.util.gradlescriptvisualizer.ui.GradleScriptPreferences;
import static com.nurflugel.util.test.TestResources.getFilePath;

import static org.apache.commons.io.FileUtils.readLines;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import java.util.List;

/** Created with IntelliJ IDEA. User: douglas_bullard Date: 10/3/12 Time: 13:03 To change this template use File | Settings | File Templates. */
@Test(groups = "unit")
public class DependencyDotFileGeneratorTest
{
  public void testCreateOutput() throws Exception {}

  public void testWriteOutput() throws Exception {}

  public void testReplaceBadChars() throws Exception {}

  public void testReadFile1() throws IOException, NoConfigurationsFoundException
  {
    String                  filePath    = getFilePath("gradle/dependencies/Das_dependencies.txt");
    List<String>            lines       = readLines(new File(filePath));
    GradleDependencyParser  parser      = new GradleDependencyParser();
    GradleScriptPreferences preferences = new GradleScriptPreferences();

    preferences.setShouldJustUseCompileConfig(true);

    String                     outputFileName = "das_dibble.dot";
    DependencyDotFileGenerator generator      = new MockDependencyDotFileGenerator();

    generator.createDotFileFromLines(parser, preferences, outputFileName, lines.toArray(new String[lines.size()]), Os.findOs(), null);
  }

  @Test(groups = "unit")
  public void testNoTransitives() throws Exception, NoConfigurationsFoundException
  {
    File file = new File(getFilePath("/Users/douglas_bullard/Documents/JavaStuff/Google_Code/gradle-script-visualizer/trunk/build/resources/test/gradle/dependencies/build_NoTransitives.gradle"));

    // File file = new File(getFilePath("./build/resources/test/gradle/dependencies/build_NoTransitives.gradle"));
    DependencyDotFileGenerator generator   = new MockDependencyDotFileGenerator();
    GradleScriptPreferences    preferences = new GradleScriptPreferences();

    preferences.setShouldJustUseCompileConfig(true);

    File output = generator.createOutputForFile(file, new GradleDependencyParser(), preferences,
                                                "/Users/douglas_bullard/Documents/JavaStuff/Google_Code/gradle-script-visualizer/trunk/build/resources/test/gradle/dependencies/build_NoTransitives.dot",
                                                Os.findOs());

    // compare files with reference
    List<String> outputLines    = readLines(output);
    List<String> referenceLines = readLines(new File("/Users/douglas_bullard/Documents/JavaStuff/Google_Code/gradle-script-visualizer/trunk/build/resources/test/gradle/dependencies/reference_build_NoTransitives.dot"));

    assertEquals(outputLines, referenceLines, "Should have the same output");
  }

  @Test(groups = "long")
  public void testCreateOutputForFile() throws Exception, NoConfigurationsFoundException
  {
    File file = new File("/Users/douglas_bullard/Documents/JavaStuff/Google_Code/gradle-script-visualizer/trunk/build.gradle");  // todo parametrize
    DependencyDotFileGenerator generator = new MockDependencyDotFileGenerator();
    GradleScriptPreferences    preferences = new GradleScriptPreferences();

    preferences.setShouldJustUseCompileConfig(true);
    generator.createOutputForFile(file, new GradleDependencyParser(), preferences, "dibble.dot", Os.findOs());
  }
}
