package org.chirs.study.concurrency.flavors.actor;

import org.w3c.dom.Document;

import play.libs.F.Promise;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.libs.ws.ning.NingWSClient;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;

public class Test {

	public static void main(String[] args) {
		String url = "http://www.baidu.com/s?wd=StarWars";
		AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
		NingWSClient wsClient = new NingWSClient(builder.build());
		WSRequestHolder request = wsClient.url(url);
		Promise<WSResponse> promisedResponse = request.get();
		WSResponse response = promisedResponse.get(3 * 1000);
		
		System.out.println(response.getStatusText());
		((AsyncHttpClient) wsClient.getUnderlying()).close();
		
	}
}
