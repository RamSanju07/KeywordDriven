package com.testRunner;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;
import com.keyword.engine.*;
public class LoginTest {

	public KeywordEngine KeywordEngine; 
	
	@Test
	public void fbLogin() throws EncryptedDocumentException, IOException {

		KeywordEngine = new KeywordEngine();
		KeywordEngine.startExecution("Sheet1");
	}
}
