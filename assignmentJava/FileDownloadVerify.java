package assignmentJava;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import org.openqa.selenium.WebDriver;

public class FileDownloadVerify extends Jan2011ColVerify {

	WebDriver driver = null;
	boolean fileExist = false;

	private File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	private String getDownLoadPath() throws InterruptedException {

		String downloadPath = System.getenv("USERPROFILE") + "\\Downloads"; // getting download path for current
																			// workspace
		Thread.sleep(10000);
		File file = getLatestFilefromDir(downloadPath);
		String fileName = file.getName();
		String filePath = downloadPath + "\\" + fileName;
		return filePath;

	}

	public void verifyCsvFileHas10INDrows() throws InterruptedException {

		String filePath = getDownLoadPath();
		int indCount = 0;
		String expText = "IND";
		try {
			if (!filePath.isEmpty() || filePath != null) {
				File csvFile = new File(filePath);
				if (csvFile.exists()) {
					System.out.println("[logger]:Verifying the IND records in the downloaded csv file");
					FileReader fileRead = new FileReader(csvFile);
					LineNumberReader lineNumRead = new LineNumberReader(fileRead);

					while (lineNumRead.readLine() != null && !lineNumRead.readLine().isEmpty()) {
						if (!lineNumRead.readLine().isEmpty() && lineNumRead.readLine() != null) {
							if (lineNumRead.readLine().contains(expText.toUpperCase())) {
								indCount++;
							}
						}
					}

					lineNumRead.close();
					fileRead.close();
					csvFile.delete();
				} else {
					System.out.println("[logger]:File you are looking for does not exist!");
				}

			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		if (indCount >= 10) {
			System.out.printf(
					"[logger]: Successfully verified! Downloaded csv file contains atleast 10 rows with IND string. \nFile contains exact: %s times IND string. \n",
					Integer.toString(indCount));
		} else {
			System.out.println("[logger]:Verification failed! Downloaded csv file is not containing 10 rows with IND string");
		}

	}

}
