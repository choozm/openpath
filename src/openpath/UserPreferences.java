package openpath;

import java.util.HashMap;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class UserPreferences {

	public static final String CHROME_DRIVER = "chrome.driver";
	public static final String CHROME_BROWSER = "chrome.browser";

	public static void save(String drv, String browser) {
		Preferences prefs = Preferences.userNodeForPackage(OpenPathDriver.class);
		prefs.put(CHROME_DRIVER, drv);
		prefs.put(CHROME_BROWSER, browser);
		try {
			prefs.sync();
		} catch (BackingStoreException ex) {
			ex.printStackTrace();
		}
	}

	public static Map<String, String> load() {
		Map<String, String> result = new HashMap<>();
		Preferences prefs = Preferences.userNodeForPackage(OpenPathDriver.class);
		result.put(CHROME_DRIVER, 
				prefs.get(CHROME_DRIVER, "/usr/local/bin/chromedriver"));
		result.put(CHROME_BROWSER, 
				prefs.get(CHROME_BROWSER, "/usr/bin/google-chrome"));
		return (result);
	}
	
}
