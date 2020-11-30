package com.haitao.www.myformer.model.global;

import com.haitao.www.myformer.ui.ui_common.component.filepicker.mfilepicker.FileType;

import java.io.Serializable;

public class SimpleBean implements Serializable {
    private int imageResId;
    private String imageText;
    private FileType fileType;
    private int tag;

    public SimpleBean(int imageResId, String imageText, FileType fileType, int tag) {
        this.imageResId = imageResId;
        this.imageText = imageText;
        this.fileType = fileType;
        this.tag = tag;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "imageResId=" + imageResId +
                ", imageText='" + imageText + '\'' +
                ", fileType=" + fileType +
                ", tag=" + tag +
                '}';
    }
}
