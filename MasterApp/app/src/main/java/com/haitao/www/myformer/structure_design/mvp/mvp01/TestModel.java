package com.haitao.www.myformer.structure_design.mvp.mvp01;

public class TestModel implements TestContract.Model {
    private TestModel(){}
    private static final TestModel model = new TestModel();

    public static TestModel getInstance() {
        return model;
    }

    @Override
    public String doData() {
        return "处理后的数据";
    }
}
