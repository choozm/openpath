package openpath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class OpenPathDriver {

    // private static Logger logger =
    // Logger.getLogger(OpenPathDriver.class.getName());

	public static int TIMEOUT = 15;

	private final WebDriver driver;
	private final Action action;

	public OpenPathDriver(WebDriver drv, Action a) {
		driver = drv;
		action = a;
	}

	public Result download(String username, String password, Path downloadDir) {

		Result result = new Result();
		result.setUser(username);

		action.perform(username + ": start");

        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		driver.get("https://openpaths.cc/");

        // Set username
		WebElement element = wait.until(driver -> {
			return (driver.findElement(By.id("username")));
		});
		element.sendKeys(username);

        // Set pasword
		element = driver.findElement(By.id("password"));
		element.sendKeys(password);

        // Login
		element = driver.findElement(By.id("submit_btn"));
		element.click();

		try {
			element = wait.until(d -> {
				return (d.findElement(By.linkText("CSV")));
			});
		} catch (Throwable ex) {
            // Assume that we have failed to login if there is an exception
			action.perform(username + ": cannot login\n\t" + ex.getMessage());
			result.setSuccess(false);
			result.setMsg(ex.getMessage());
			return (result);
		}

        // Write the username and csv filename pair before download
        try {
            FileWriter fw = new FileWriter(downloadDir.resolve(DownloadTask.USERNAME_FILE).toString(), true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(String.format("%s,%s", getFileName(element.getAttribute("href")), username));
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Download the file
		element.click();

		Path downloadFile = downloadDir.resolve(getFileName(element.getAttribute("href")));
		waitForFileDownload(downloadFile);

		action.perform(username + ": complete");

        // Make sure we're out
		while (true) {
			element = driver.findElement(By.linkText("Logout"));
			element.click();
			
			try {
				element = wait.until(driver -> {
					return (driver.findElement(By.id("username")));
				});
            } catch (Throwable t) {
                continue;
            }
			break;
		}

		result.setSuccess(true);
		result.setMsg("Download complete for " + username);
		result.setDownloadFile(downloadFile);
		return (result);
	}

	private String getFileName(String href) {
		String[] terms = href.split("/");
		return (terms[terms.length - 1]);
	}

	private void waitForFileDownload(Path downloadFile) {

		File f = downloadFile.toFile();
		long last = -1L;
		long current = 0;
		int count = 0;

		while (count < 5) {
			last = current;
			current = f.length();
			if (current == last)
				count++;
			else
				count = 0;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) { 
				ex.printStackTrace();
			}
		}
	}
}
