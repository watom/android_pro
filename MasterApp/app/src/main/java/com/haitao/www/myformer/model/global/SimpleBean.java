package com.haitao.www.myformer.model.global;

public class SimpleBean {
    private int imageResId;
    private String imageText;
    private int tag;

    public SimpleBean(int imageResId, String imageText, int tag) {
        this.imageResId = imageResId;
        this.imageText = imageText;
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

    @Override
    public String toString() {
        return "SimpleBean{" +
                "imageResId=" + imageResId +
                ", imageText='" + imageText + '\'' +
                ", tag=" + tag +
                '}';
    }
}
