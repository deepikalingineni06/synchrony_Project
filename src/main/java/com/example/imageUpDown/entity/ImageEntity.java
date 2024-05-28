package com.example.imageUpDown.entity;

import jakarta.persistence.*;

@Entity
public class ImageEntity {

    public ImageEntity() {
		super();
	}

	public ImageEntity(Long id, String fileName, String filePath, String imgType, String deleteHashCode, Long userId) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.imgType = imgType;
		this.deleteHashCode = deleteHashCode;
		this.userId = userId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="file_name")
    private String fileName;
    
    @Column(name="file_path")
    private String filePath;
    
    @Column(name="img_type")
    private String imgType;
    
    @Column(name="delete_hash_code")
    private String deleteHashCode;
    
    @Column(name="user_id")
    private Long userId;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDeleteHashCode() {
		return deleteHashCode;
	}

	public void setDeleteHashCode(String deleteHashCode) {
		this.deleteHashCode = deleteHashCode;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	@Override
	public String toString() {
		return "ImageEntity [id=" + id + ", fileName=" + fileName + ", filePath=" + filePath + ", imgType=" + imgType
				+ ", deleteHashCode=" + deleteHashCode + ", userId=" + userId + "]";
	}
	
		
}