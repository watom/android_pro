package com.haitao.www.myformer.logic.LogicTest;

import java.util.List;

/**
 * Created by Administrator on 2018/8/20 0020.
 */

public class GsonBean {

    /**
     * data : {"returnCode":"0000","requestCode":"JS12547","returnMsg":"查询成功","rteLists":[{"rteId":"1001","rteName":"汇款账号名称","rteNo":"28906578878889"},{"rteId":"1002","rteName":"汇款账号名称","rteNo":"22222222"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * returnCode : 0000
         * requestCode : JS12547
         * returnMsg : 查询成功
         * rteLists : [{"rteId":"1001","rteName":"汇款账号名称","rteNo":"28906578878889"},{"rteId":"1002","rteName":"汇款账号名称","rteNo":"22222222"}]
         */

        private String returnCode;
        private String requestCode;
        private String returnMsg;
        private List<RteListsBean> rteLists;

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public String getReturnMsg() {
            return returnMsg;
        }

        public void setReturnMsg(String returnMsg) {
            this.returnMsg = returnMsg;
        }

        public List<RteListsBean> getRteLists() {
            return rteLists;
        }

        public void setRteLists(List<RteListsBean> rteLists) {
            this.rteLists = rteLists;
        }

        public static class RteListsBean {
            /**
             * rteId : 1001
             * rteName : 汇款账号名称
             * rteNo : 28906578878889
             */

            private String rteId;
            private String rteName;
            private String rteNo;

            public String getRteId() {
                return rteId;
            }

            public void setRteId(String rteId) {
                this.rteId = rteId;
            }

            public String getRteName() {
                return rteName;
            }

            public void setRteName(String rteName) {
                this.rteName = rteName;
            }

            public String getRteNo() {
                return rteNo;
            }

            public void setRteNo(String rteNo) {
                this.rteNo = rteNo;
            }
        }
    }
}
