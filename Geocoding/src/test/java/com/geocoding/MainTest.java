package com.geocoding;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

	@Test
	public void testTriangle() throws IOException {
		String InputFileTriangle = this.getClass().getClassLoader().getResource("input-triangle.txt").getPath();
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output-triangle-test.txt")), true));
		String[] args = { InputFileTriangle, System.getProperty("APIKey") };
		Main.main(args);
		File file1 = new File("output-triangle-test.txt");
		File file2 = new File(this.getClass().getClassLoader().getResource("output-triangle.txt").getPath());
		Assert.assertTrue(FileUtils.contentEquals(file1, file2));
	}
	@Test
	public void testOctagon() throws IOException {
		String InputFileOctagon = this.getClass().getClassLoader().getResource("input-octagon.txt").getPath();
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output-octagon-test.txt")), true));
		String[] args2 = { InputFileOctagon, System.getProperty("APIKey") };
		Main.main(args2);
		File file3 = new File("output-octagon-test.txt");
		File file4 = new File(this.getClass().getClassLoader().getResource("output-octagon.txt").getPath());
		Assert.assertTrue(FileUtils.contentEquals(file3, file4));
	}
	@Test
	public void testEquator() throws IOException {
		String InputFileOctagon = this.getClass().getClassLoader().getResource("input-equator.txt").getPath();
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output-equator-test.txt")), true));
		String[] args3 = { InputFileOctagon, System.getProperty("APIKey") };
		Main.main(args3);
		File file5 = new File("output-equator-test.txt");
		File file6 = new File(this.getClass().getClassLoader().getResource("output-equator.txt").getPath());
		Assert.assertTrue(FileUtils.contentEquals(file5, file6));
	}
}
