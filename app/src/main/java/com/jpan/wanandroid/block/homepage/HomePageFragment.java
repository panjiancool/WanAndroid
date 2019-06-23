package com.jpan.wanandroid.block.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jpan.wanandroid.R;
import com.jpan.wanandroid.base.BaseFragment;
import com.jpan.wanandroid.block.homepage.entry.HomeData;
import com.jpan.wanandroid.block.homepage.entry.HomeListBean;
import com.jpan.wanandroid.http.schedules.SchedulerProvider;
import com.jpan.wanandroid.utils.ArrayUtils;

import java.util.List;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.text_content)
    TextView mTextContent;

    private HomePresenter presenter = new HomePresenter(SchedulerProvider.getInstance(), this);
    private StringBuilder builder = new StringBuilder();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.getHomeList(true);
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_honmepage;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public boolean getHomeListSuccess(HomeListBean homeListBean, boolean isRefresh) {
        List<HomeData> homeDataList = homeListBean.getDatas();
        if (!ArrayUtils.isEmpty(homeDataList)) {
            // TODO just for test code start
            for (HomeData homeData : homeDataList) {
                builder.append(getText("ApkLink", homeData.getApkLink()))
                        .append(getText("Author", homeData.getAuthor()))
                        .append(getText("ChapterName", homeData.getChapterName()))
                        .append(getText("Desc", homeData.getDesc()))
                        .append(getText("EnvelopePic", homeData.getEnvelopePic()))
                        .append(getText("Link", homeData.getLink()))
                        .append(getText("NiceDate", homeData.getNiceDate()))
                        .append(getText("Origin", homeData.getOrigin()))
                        .append(getText("SuperChapterName", homeData.getSuperChapterName()))
                        .append(getText("Id", homeData.getId()))
                        .append(getText("ChapterId", homeData.getChapterId()))
                        .append(getText("CourseId", homeData.getCourseId()))
                        .append(getText("UserId", homeData.getUserId()))
                        .append(getText("SuperChapterId", homeData.getSuperChapterId()))
                        .append(getText("Title", homeData.getTitle()))
                        .append(getText("Type", homeData.getType()))
                        .append("--------------------------------------------------------");
                mTextContent.setText(builder.toString());
            }
            // TODO just for test code end
        }
        Toast.makeText(getActivity(), "Success!!", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean getHomeListFail(String msg, boolean isRefresh) {
        return false;
    }

    private String getText(String name, String text) {
        if (TextUtils.isEmpty(text)) return "";
        return name + ": " + text + "\r\n";
    }

    private String getText(String name, int text) {
        return name + ": " + text + "\r\n";
    }
}
