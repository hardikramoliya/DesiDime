package com.android.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpPostRequest implements Runnable {

	private final static int TIMEOUT = 10000;

	private String urlstring;
	private int action;
	private HttpCallback cb;
	private HashMap<String, String> params = null;

	public HttpPostRequest(String url, HashMap<String, String> params,
			int action, HttpCallback cb) {

		this.urlstring = url;
		this.cb = cb;
		this.action = action;
		this.params = params;
	}

	@Override
	public void run() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(urlstring);
		InputStream inputStream = null;
		String rawResponse = null;

		try {

			if (params != null) {

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						params.size());
				Set<String> set = params.keySet();

				for (Iterator<String> iterator = set.iterator(); iterator
						.hasNext();) {

					String paramName = iterator.next();
					nameValuePairs.add(new BasicNameValuePair(paramName, params
							.get(paramName)));

				}

				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			}

			HttpParams httpParameters = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);

			inputStream = response.getEntity().getContent();

			byte[] responseData = new byte[1000];
			int length = 0;

			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

			while ((length = inputStream.read(responseData)) != -1) {
				arrayOutputStream.write(responseData, 0, length);
			}

			rawResponse = new String(arrayOutputStream.toByteArray());

			arrayOutputStream.close();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (rawResponse != null) {
			cb.onResponse(rawResponse.toString(), action);
		} else
			cb.onResponse("NO", action);

	}

}
