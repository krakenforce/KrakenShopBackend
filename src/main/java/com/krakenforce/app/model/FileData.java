package com.krakenforce.app.model;

public class FileData {
	private String filename;
    private String url;
    
    

    public FileData() {
	
	}

	public FileData(String filename, String url) {
		this.filename = filename;
		this.url = url;
	}

	public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
