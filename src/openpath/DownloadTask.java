package openpath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

public class DownloadTask implements Callable<Void> {

	private final String chromeDrv;
	private final String chrome;
	private final Path userList;
	private final Path downloadDir;

	private Action action = msg -> {
	};

	public DownloadTask(String userList, String downloadDir, String chromeDrv, String chrome) {
		this.chromeDrv = chromeDrv;
		this.chrome = chrome;
		this.userList = Paths.get(userList);
		this.downloadDir = Paths.get(downloadDir);
	}

	public void log(Action a) {
		action = a;
	}

	@Override
	public Void call() {
		DriverService service = createDriverService(chromeDrv);
		try {
			service.start();
			WebDriver driver = createWebDriver(downloadDir.toAbsolutePath().toString(), chrome, service);

			Path log = Files.createFile(downloadDir.resolve("openpath_download.log"));

			String key = UUID.randomUUID().toString().substring(0, 8);

			try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(log, StandardOpenOption.CREATE
					, StandardOpenOption.APPEND, StandardOpenOption.WRITE))) {

				OpenPathDriver openPath = new OpenPathDriver(driver, action);

				writer.printf("%1$s start: %2$s\n", key, new Date());

				try {
					for (String line : Files.readAllLines(userList)) {
						String[] terms;
						if (null == (terms = skip(line)))
							continue;
						action.perform(String.format("Processing: %1$s", terms));
						Result result = openPath.download(terms[0], terms[1], downloadDir);

						writer.printf("%1$s\n", result);
					}
				} catch (Throwable t) {
					writer.append("Exception - aborting: " + t.getMessage());
					t.printStackTrace(writer);
				}

				writer.printf("%1$s end: %2$s\n", key, new Date());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			action.perform("Exception: " + ex.getMessage());
			action.perform("Exiting");
		} finally {
			service.stop();
		}

		return (null);
	}

	public DriverService createDriverService(String driverPath) {

		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(driverPath))
				.usingAnyFreePort()
				.build();

		return (service);
	}

	public WebDriver createWebDriver(String downloadPath, String chromePath, DriverService service) 
			throws IOException {
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadPath);

		ChromeOptions opts = new ChromeOptions();
		opts.setBinary(new File(chromePath));
		opts.setExperimentalOption("prefs", prefs);
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability(ChromeOptions.CAPABILITY, opts);

		if (!service.isRunning())
			service.start();

		return (new RemoteWebDriver(service.getUrl(), caps));
	}

	public static String[] skip(String line) {
		if ((null == line) || (line.trim().length() <= 0) || line.trim().startsWith("#"))
			return (null);
		String[] terms = line.split(",");
		if (terms.length < 2)
			return (null);
		return (terms);
	}

}
