package com.target.myretail.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("target")
public class TargetResourceMap {

	public TargetResourceMap(){};
	
	private String uri;
	private String params;
	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	
}
