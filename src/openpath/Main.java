/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author cmlee
 */
public class Main {

	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public void other(String[] args) throws IOException {

		String webdriverPath = "/usr/local/bin/chromedriver";
		String chromePath = "/usr/bin/google-chrome";
		String downloadDir = "/opt/tmp/openpath";

		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(webdriverPath))
				.usingAnyFreePort()
				.build();

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadDir);

		ChromeOptions opts = new ChromeOptions();
		opts.setBinary(new File(chromePath));
		opts.setExperimentalOption("prefs", prefs);
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability(ChromeOptions.CAPABILITY, opts);

		service.start();

		WebDriver drv = new RemoteWebDriver(service.getUrl(), caps);

		OpenPathDriver opDrv = new OpenPathDriver(drv, 
				m -> { 
					System.out.println(">> " + m);
				});

		String opUsername = "caiyuhao0125@hotmail.com";
		String opPassword = "19910125";

		opDrv.download(opUsername, opPassword, Paths.get(downloadDir));

		service.stop();

		System.exit(0);
	}
	
}
