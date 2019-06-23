package com.jpan.wanandroid;

import com.jpan.fragmentpager.FragmentEntry;
import com.jpan.fragmentpager.FragmentViewPager;
import com.jpan.wanandroid.base.BaseActivity;
import com.jpan.wanandroid.block.homepage.HomePageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_viewpager)
    FragmentViewPager mFragmentViewPager;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        List<FragmentEntry> fragmentEntryList = new ArrayList<>();

        FragmentEntry entry = new FragmentEntry();
        entry.setTitle("首页");
        entry.setSelectImage(getResources().getDrawable(R.drawable.ic_home_select));
        entry.setUnSelectImage(getResources().getDrawable(R.drawable.ic_home_unselect));
        entry.setFragment(new HomePageFragment());
        fragmentEntryList.add(entry);

        FragmentEntry entry1 = new FragmentEntry();
        entry1.setTitle("体系");
        entry1.setSelectImage(getResources().getDrawable(R.drawable.ic_system_select));
        entry1.setUnSelectImage(getResources().getDrawable(R.drawable.ic_system_unselect));
        entry1.setFragment(new HomePageFragment());
        fragmentEntryList.add(entry1);

        FragmentEntry entry2 = new FragmentEntry();
        entry2.setTitle("公众号");
        entry2.setSelectImage(getResources().getDrawable(R.drawable.ic_public_account_select));
        entry2.setUnSelectImage(getResources().getDrawable(R.drawable.ic_public_account_unselect));
        entry2.setFragment(new HomePageFragment());
        fragmentEntryList.add(entry2);

        FragmentEntry entry3 = new FragmentEntry();
        entry3.setTitle("项目");
        entry3.setSelectImage(getResources().getDrawable(R.drawable.ic_project_select));
        entry3.setUnSelectImage(getResources().getDrawable(R.drawable.ic_project_unselect));
        entry3.setFragment(new HomePageFragment());
        fragmentEntryList.add(entry3);

        FragmentEntry entry4 = new FragmentEntry();
        entry4.setTitle("我的");
        entry4.setSelectImage(getResources().getDrawable(R.drawable.ic_mine_select));
        entry4.setUnSelectImage(getResources().getDrawable(R.drawable.ic_mine_unselect));
        entry4.setFragment(new HomePageFragment());
        fragmentEntryList.add(entry4);

        mFragmentViewPager.initViewPager(getSupportFragmentManager(), fragmentEntryList);
    }
}
