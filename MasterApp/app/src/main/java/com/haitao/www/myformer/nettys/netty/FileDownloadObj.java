package com.haitao.www.myformer.nettys.netty;

/**
 * Created by quliang on 2017/9/19.
 */

public class FileDownloadObj {

    private String attachmentId;
    private String sessionId;
    private String filePath;
    private String fileType;
    private boolean isHd2;
    private boolean isEnd;

    public FileDownloadObj(String attachmentId, String sessionId, String filePath, String fileType, boolean isHd2) {
        this.attachmentId = attachmentId;
        this.sessionId = sessionId;
        this.filePath = filePath;
        this.fileType = fileType;
        this.isHd2 = isHd2;
    }


    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public FileDownloadObj() {
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public boolean isHd2() {
        return isHd2;
    }

    public void setHd2(boolean hd2) {
        isHd2 = hd2;
    }
}
