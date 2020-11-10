package com.haitao.www.myformer.sqLite;

import java.io.Serializable;

public class ListBean implements Serializable {
    /**
     * name : 全部
     * title : 应用123456
     * icon :
     * link :
     * provideUser : 厂商1212
     * resourceId : 90E1344C8FC64FA39A3F956AB294516C
     * grade : 0
     * resourceType : 6
     * isCare : 1
     * ext1 :
     * ext2 : 2
     * ext3 : 0
     * sort : 0
     * sortSNow : 0
     * sortAfter : 0
     */

    private String name;
    private String title;
    private String icon;
    private String link;
    private String provideUser;
    private String resourceId;
    private String grade;
    private String resourceType;
    private String isCare;
    private String ext1;
    private String ext2;
    private String ext3;
    private String sort;
    private String sortSNow;



    private String sortAfter;


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortAfter() {
        return sortAfter;
    }

    public void setSortAfter(String sortAfter) {
        this.sortAfter = sortAfter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProvideUser() {
        return provideUser;
    }

    public void setProvideUser(String provideUser) {
        this.provideUser = provideUser;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getIsCare() {
        return isCare;
    }

    public void setIsCare(String isCare) {
        this.isCare = isCare;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getSortSNow() {
        return sortSNow;
    }

    public void setSortSNow(String sortSNow) {
        this.sortSNow = sortSNow;
    }

    @Override
    public String toString() {
        return "ListBean{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                ", provideUser='" + provideUser + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", grade='" + grade + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", isCare='" + isCare + '\'' +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                ", sort='" + sort + '\'' +
                ", sortSNow='" + sortSNow + '\'' +
                ", sortAfter='" + sortAfter + '\'' +
                '}';
    }
}
