package com.haitao.www.myformer.model.global;

public class FileBean {
    private String icon;
    private String title;
    private String name;
    private String filePath;
    private String fileName;
    private String time;
    private long fileSize;
    private String fileId;
    private int fileType;
    private boolean isChecked;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", time='" + time + '\'' +
                ", fileSize=" + fileSize +
                ", fileId='" + fileId + '\'' +
                ", fileType=" + fileType +
                ", isChecked=" + isChecked +
                '}';
    }
}
