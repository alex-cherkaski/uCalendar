package dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

public class DropboxClient {
	private static final String ACCESS_TOKEN = "";
	private DbxRequestConfig config = null;
	DbxClientV2 client = null;
	FullAccount account = null;

	public DropboxClient() {
		// Create Dropbox client
		config = new DbxRequestConfig("dropbox/java-tutorial");
		client = new DbxClientV2(config, ACCESS_TOKEN);

		try {
			FullAccount account = client.users().getCurrentAccount();
			System.out.println(account.getName().getDisplayName());
		}
		catch (DbxException dbxe)
		{
			dbxe.printStackTrace();
		}
	}
}
