package com.jpan.wanandroid.block.homepage.entry;

import java.io.Serializable;
import java.util.List;

public class HomeListBean implements Serializable {

    private int curPage;

    private List<HomeData> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<HomeData> getDatas() {
        return datas;
    }

    public void setDatas(List<HomeData> datas) {
        this.datas = datas;
    }
}
