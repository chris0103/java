package org.chirs.study.concurrency.flavors;

import play.libs.ws.ning.NingWSClient;

import com.ning.http.client.AsyncHttpClientConfig;

public abstract class AbstractSearchAgent {

	protected static NingWSClient wsClient = new NingWSClient(new AsyncHttpClientConfig.Builder().build());
}
