package org.chirs.study.concurrency.flavors.actor;

public class Message {

	private String url;
	
	public Message(String url) {
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
