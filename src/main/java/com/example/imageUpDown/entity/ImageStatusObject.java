package com.example.imageUpDown.entity;

public class ImageStatusObject {

    public ImageStatusObject() {
		super();
	}

	public ImageStatusObject(String id, String created, String finished, String status, LinkStatusObject uploaded) {
		super();
		this.id = id;
		this.created = created;
		this.finished = finished;
		this.status = status;
		this.uploaded = uploaded;
	}

	String id;
    String created;
    String finished;
    String status;
    LinkStatusObject uploaded;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LinkStatusObject getUploaded() {
        return uploaded;
    }

    public void setUploaded(LinkStatusObject uploaded) {
        this.uploaded = uploaded;
    }

	@Override
	public String toString() {
		return "ImageStatusObject [id=" + id + ", created=" + created + ", finished=" + finished + ", status=" + status
				+ ", uploaded=" + uploaded + "]";
	}
}
