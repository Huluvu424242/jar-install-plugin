package com.github.funthomas424242.maven.plugins.jarinstall;

public class DownloadArtifact {

	protected String url;
	
	protected String groupId;
	
	protected String artifactId;
	
	protected String version;
	
	protected String scope;
	
	protected String type;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
	public String toString(){
		final StringBuffer buf=new StringBuffer();
		buf.append('[');
		buf.append(groupId);
		buf.append(',');
		buf.append(artifactId);
		buf.append(',');
		buf.append(version);
		buf.append(',');
		buf.append(url);
		buf.append(']');
		return buf.toString();
	}
}
