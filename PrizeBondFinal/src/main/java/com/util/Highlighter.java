package com.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlighter {

	public static void getcolor(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver; //typecast polymorphism
		js.executeScript("arguments[0].setAttribute('style', 'background: light orange; border: 6px solid red;');", element);
	}

	public static void getcolor(WebDriver driver, WebElement element, String color) {// OOPs ==> Polymorphism ==> Compile time
																				// poly
		JavascriptExecutor js = (JavascriptExecutor) driver; // type casting polymorphism
		js.executeScript("arguments[0].setAttribute('style', 'background: light orange; border: 6px solid " + color + ";');",
				element);
	}	
	public static void getcolor(WebDriver driver, WebElement element, String bgcolor, String fgcolor) {// OOPs ==> Polymorphism ==> Compile time
		// poly
		JavascriptExecutor js = (JavascriptExecutor) driver; // type casting polymorphism
		js.executeScript("arguments[0].setAttribute('style', 'background: "+bgcolor+"; border: 6px solid " + fgcolor + ";');",element);
	}
}
