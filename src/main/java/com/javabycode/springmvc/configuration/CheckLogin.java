package com.javabycode.springmvc.configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class CheckLogin {

	public static void main(String[] args) {
		CheckLogin CheckLogin = new CheckLogin();
		// CheckLogin.login();
		//CheckLogin.googleLogin();
		//CheckLogin.trackOrder();
	}

	public int login(String username, String password) {
		int userId = 0;
		try {
			URL url = new URL("https://authenticatemeapi.azurewebsites.net/api/User");
			String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			InputStream content = (InputStream) connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			userId = Integer.parseInt(in.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}

	public void googleLogin() {

		try {
			URL url = new URL(
					"https://authenticatemeapi.azurewebsites.net/api/Account/ExternalLogin?provider=Google&response_type=token&client_id=self&redirect_uri=https%3A%2F%2Fecommerceclient.azurewebsites.net%2FHome%2FAbout&state=G25krKY5vTjV8oL5zFRTikB8FRPQdMeE1c5XDl3jxpg1");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);

			InputStream content = (InputStream) connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

}
