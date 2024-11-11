package com.epam.TestAutomation.Project.Selenium.WebFactory;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.ScriptKey;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.CommandPayload;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.remote.ExecuteMethod;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.JsonToWebElementConverter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebDriver.When;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticator;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions;

public class ProxyWebDriver extends RemoteWebDriver {
	RemoteWebDriver driver;
	public ProxyWebDriver(WebDriver driver) {
		this.driver=(RemoteWebDriver)driver;
	
	}
	@Override
	public ScriptKey pin(String script) {
		// TODO Auto-generated method stub
		return driver.pin(script);
	}

	@Override
	public void unpin(ScriptKey key) {
		// TODO Auto-generated method stub
		driver.unpin(key);
	}

	@Override
	public Set<ScriptKey> getPinnedScripts() {
		// TODO Auto-generated method stub
		return driver.getPinnedScripts();
	}

	

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		// TODO Auto-generated method stub
		return driver.executeAsyncScript(script, args);
	}

	
	

	
	@Override
	public Object executeScript(String script, Object... args) {
		// TODO Auto-generated method stub
		RemoteWebDriver driverR=(RemoteWebDriver)driver;
		return driverR.executeScript(script, args);
	}

	@Override
	public Object executeScript(ScriptKey key, Object... args) {
		// TODO Auto-generated method stub
		return driver.executeScript(key, args);
	}

	@Override
	public SessionId getSessionId() {
		// TODO Auto-generated method stub
		return driver.getSessionId();
	}

	

	@Override
	public ErrorHandler getErrorHandler() {
		// TODO Auto-generated method stub
		return driver.getErrorHandler();
	}

	@Override
	public void setErrorHandler(ErrorHandler handler) {
		// TODO Auto-generated method stub
		driver.setErrorHandler(handler);
	}

	@Override
	public CommandExecutor getCommandExecutor() {
		// TODO Auto-generated method stub
		return driver.getCommandExecutor();
	}

	

	@Override
	public Capabilities getCapabilities() {
		// TODO Auto-generated method stub
		return driver.getCapabilities();
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
		// TODO Auto-generated method stub
		return driver.getScreenshotAs(outputType);
	}

	@Override
	public Pdf print(PrintOptions printOptions) throws WebDriverException {
		// TODO Auto-generated method stub
		return driver.print(printOptions);
	}

	@Override
	public List<WebElement> findElements(SearchContext context, BiFunction<String, Object, CommandPayload> findCommand,
			By locator) {
		// TODO Auto-generated method stub
		return driver.findElements(context, findCommand, locator);
	}

	

	

	@Override
	public void setLogLevel(Level level) {
		// TODO Auto-generated method stub
		driver.setLogLevel(level);
	}

	

	@Override
	public void perform(Collection<Sequence> actions) {
		// TODO Auto-generated method stub
		driver.perform(actions);
	}

	@Override
	public void resetInputState() {
		// TODO Auto-generated method stub
		driver.resetInputState();
	}

	@Override
	public Keyboard getKeyboard() {
		// TODO Auto-generated method stub
		return driver.getKeyboard();
	}

	@Override
	public Mouse getMouse() {
		// TODO Auto-generated method stub
		return driver.getMouse();
	}

	@Override
	public VirtualAuthenticator addVirtualAuthenticator(VirtualAuthenticatorOptions options) {
		// TODO Auto-generated method stub
		return driver.addVirtualAuthenticator(options);
	}

	@Override
	public void removeVirtualAuthenticator(VirtualAuthenticator authenticator) {
		// TODO Auto-generated method stub
		driver.removeVirtualAuthenticator(authenticator);
	}

	

	@Override
	public FileDetector getFileDetector() {
		// TODO Auto-generated method stub
		return driver.getFileDetector();
	}

	@Override
	public void setFileDetector(FileDetector detector) {
		// TODO Auto-generated method stub
		driver.setFileDetector(detector);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return driver.toString();
	}

	
	
	@Override
	public void get(String url) {
		// TODO Auto-generated method stub
		driver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		return driver.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return driver.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, Duration.ofSeconds(30))
        .until(driver -> driver.findElement(by));
		return driver.findElement(by);
	}

	@Override
	public String getPageSource() {
		// TODO Auto-generated method stub
		return driver.getPageSource();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		driver.close();
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		driver.quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		// TODO Auto-generated method stub
		return driver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return driver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return driver.switchTo();
	}

	@Override
	public Navigation navigate() {
		// TODO Auto-generated method stub
		return driver.navigate();
	}

	@Override
	public Options manage() {
		// TODO Auto-generated method stub
		return driver.manage();
	}

}
