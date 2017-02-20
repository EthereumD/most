package com.example.jambo.bitbuy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private TabLayout mTabs;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.pager_tabs);
        mTabs.addTab(mTabs.newTab().setText("五金"));
        mTabs.addTab(mTabs.newTab().setText("3C"));
        mTabs.addTab(mTabs.newTab().setText("生活用品"));

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
    }
    class MyPagerAdapter extends PagerAdapter {
        private int pageCount = 3;
        @Override
        public int getCount() {
            return pageCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return obj == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "MyPage " + (position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getLayoutInflater().inflate(R.layout.my_pager_items, container, false);
            container.addView(view);
            TextView title = (TextView) view.findViewById(R.id.textView5);
            //CheckBox chb = (CheckBox)view.findViewById(R.id.checkBox2);
            TextView item1 = (TextView) view.findViewById(R.id.textView8);
            TextView item2 = (TextView) view.findViewById(R.id.textView9);
            //title.setText("" + (position ));
            if (position == 0 ){
                item1.setText("鐵絲");
                item2.setText("電鑽");
                title.setText(""+position);
            }
            else if (position == 1 ){
                item1.setText("電腦");
                item2.setText("手機");
                title.setText(""+position);

            }
            else if (position == 2 ){
                item1.setText("牛奶");
                item2.setText("麵包");
                title.setText(""+position);

            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
