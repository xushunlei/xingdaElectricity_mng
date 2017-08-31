package com.xinda.entity;

public class Proxy {
    private Integer proxyId;

    private String proxyIp;

    private Integer proxyPort;

	public Integer getProxyId() {
		return proxyId;
	}

	public void setProxyId(Integer proxyId) {
		this.proxyId = proxyId;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	@Override
	public String toString() {
		return "Proxy [proxyId=" + proxyId + ", proxyIp=" + proxyIp
				+ ", proxyPort=" + proxyPort + "]";
	}
}