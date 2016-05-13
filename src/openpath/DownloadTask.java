package openpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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
    private final String LOG_FILE = "openpath_download.log";
    private final String ALLINONE_FILE = "all-in-one.csv";
    static final String USERNAME_FILE = "username.csv";

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

            Path log = downloadDir.resolve(LOG_FILE);
            Files.deleteIfExists(log);
            Files.deleteIfExists(downloadDir.resolve(USERNAME_FILE));

			String key = UUID.randomUUID().toString().substring(0, 8);

            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(log, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND, StandardOpenOption.WRITE))) {

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

        combineCSV();
		return (null);
	}

	public DriverService createDriverService(String driverPath) {

        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverPath))
                .usingAnyFreePort().build();

		return (service);
	}

    public WebDriver createWebDriver(String downloadPath, String chromePath, DriverService service) throws IOException {
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

    private void combineCSV() {
        String OUTPUT_FOLDER = downloadDir.toString();

        FilenameFilter csvFileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.lastIndexOf('.') > 0) {
                    // get last index for '.' char
                    int lastIndex = name.lastIndexOf('.');

                    // get extension
                    String str = name.substring(lastIndex);

                    // match extension and keyword
                    if (str.equals(".csv") && name.startsWith("openpaths")) {
                        return true;
                    }
                }
                return false;
            }
        };

        File dir = new File(OUTPUT_FOLDER);
        Path path;
        Reader reader, usernameReader;
        CSVParser p;
        Iterable<CSVRecord> records, usernameRecords;
        HashMap<String, String> usernameMap = new HashMap<String, String>();

        try {
            // Read the username and csv filename pair into HashMap
            path = Paths.get(OUTPUT_FOLDER, USERNAME_FILE);
            usernameReader = new FileReader(path.toString());
            usernameRecords = new CSVParser(usernameReader, CSVFormat.EXCEL.withAllowMissingColumnNames());
            for (CSVRecord record : usernameRecords) {
                usernameMap.put(record.get(0), record.get(1));
            }

            // Open the all in one file
            path = Paths.get(OUTPUT_FOLDER, ALLINONE_FILE);
            File allInOne = new File(path.toString());
            PrintWriter pw = new PrintWriter(allInOne);

            boolean firstRecord = true;
            for (String f : dir.list(csvFileFilter)) {
                // Open one OpenPaths csv file at a time
                path = Paths.get(OUTPUT_FOLDER, f);
                reader = new FileReader(path.toString());
                p = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
                records = p;

                // Write the header only once
                if (firstRecord) {
                    pw.print("email,");
                    for (String colName : p.getHeaderMap().keySet()) {
                        pw.print(colName + ",");
                    }
                    pw.println();
                    firstRecord = false;
                }

                // Write each row from OpenPaths csv to the all-in-one csv
                for (CSVRecord record : records) {
                    StringBuilder row = new StringBuilder();
                    row.append(usernameMap.get(f));
                    row.append(",");
                    row.append(record.get("lat"));
                    row.append(",");
                    row.append(record.get("lon"));
                    row.append(",");
                    row.append(record.get("alt"));
                    row.append(",");
                    row.append(record.get("date"));
                    row.append(",");
                    row.append('"' + record.get("device") + '"');
                    row.append(",");
                    row.append(record.get("os"));
                    row.append(",");
                    row.append(record.get("version"));
                    pw.println(row.toString());
                }
                reader.close();
            }
            usernameReader.close();
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
