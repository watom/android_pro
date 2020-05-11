package com.haitao.www.myformer.structure_design.mvp.mvp01;

public class TestPresenter implements TestContract.Presenter {
    private TestContract.Model model;
    private TestContract.View view;

    public TestPresenter(TestContract.Model model, TestContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getData() {
        view.showData(model.doData());
    }
}
