package dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

/*
 * IF YOU ARE GETTING ERRORS IN ECLIPSE HERE:
 * Go here: https://github.com/dropbox/dropbox-sdk-java/releases/tag/v3.0.6
 * Download all the .jar files to a folder on your desktop.
 * In Eclipse, right click on JRE System Library on the left.
 * Go to Build Path and click on Configure Build Path...
 * Click on Get External JARS...
 * Find the .jar files that you download and add them to the project.
 * The errors should be gone now.
 */

public class Test {
	private static final String ACCESS_TOKEN = "<ACCESS TOKEN>";
	
	public static void main(String[] args) {
        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("uCalendar/1.0").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
	}
}
