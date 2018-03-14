package dropbox;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;

/*
 * IF YOU ARE GETTING ERRORS IN ECLIPSE HERE:
 * Go here: https://github.com/dropbox/dropbox-sdk-java/releases/tag/v3.0.6
 * Download all the .jar files to a folder on your desktop.
 * Go here: https://stackoverflow.com/questions/44319563/noclassdeffounderror-while-using-dropbox-core-api
 * Download jackson-core-x.x.x linked in the comments.
 * THAT JACKSON BULLSHIT WAS NOT EVEN IN THE DOCUMENTATION YET
 * YOU CANNOT RUN THE API WITHOUT! WTF!?
 * WHAT KIND OF An ASSHOLE WROTE THE DOCUMENTATION FOR THIS!?
 * In Eclipse, right click on JRE System Library on the left.
 * Go to Build Path and click on Configure Build Path...
 * Click on Get External JARS...
 * Find the .jar files that you download and add them to the project.
 * The errors should be gone now.
 */

public class Test {
	private static final String ACCESS_TOKEN = "";

	public static void main(String[] args) throws DbxApiException, DbxException, IOException {
		// Create Dropbox client
		DbxRequestConfig config = DbxRequestConfig.newBuilder("uCalendar/1.0").build();
		DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

		// Get current account info
		FullAccount account = client.users().getCurrentAccount();
		System.out.println(account.getName().getDisplayName());
		
		// Upload "test.txt" to Dropbox
		try (InputStream in = new FileInputStream("C:\\Users\\user\\Desktop\\test.txt")) {
		    FileMetadata metadata = client.files().uploadBuilder("/test.txt")
		        .uploadAndFinish(in);
		}
	}
}
