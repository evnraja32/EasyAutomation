package examples;

import org.testng.annotations.Test;

import elements.Browser;

public class LaunchBrowserTest {

	@Test(description = "Launch chrome browser")
	public void f() {

		Browser browser = new Browser("chrome");
		browser.closeBrowser();

	}
}
