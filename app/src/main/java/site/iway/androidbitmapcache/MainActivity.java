package site.iway.androidbitmapcache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import site.iway.androidhelpers.BitmapSource;
import site.iway.androidhelpers.BitmapView;
import site.iway.androidhelpers.WindowHelper;

public class MainActivity extends Activity {

    private int mScreenWidth;
    private int mGridSideLength;

    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScreenWidth = WindowHelper.getScreenWidth(this);
        mGridSideLength = mScreenWidth / 2;

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setNumColumns(2);
        mGridView.setAdapter(mGridViewAdapter);
    }

    private BaseAdapter mGridViewAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return 157;
        }

        @Override
        public BitmapSource getItem(int position) {
            String url = "http://home.iway.site:8888/test/images/image%20(" + (position + 1) + ").jpg";
            return new BitmapSource(BitmapSource.TYPE_URL, url, null);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_bitmap_cache, mGridView, false);
                convertView.getLayoutParams().width = mGridSideLength;
                convertView.getLayoutParams().height = (mGridSideLength - 20) * 1200 / 1920 + 20;
            }

            BitmapView bitmapView = (BitmapView) convertView.findViewById(R.id.bitmapView);
            bitmapView.loadFromSource(getItem(position));

            return convertView;
        }

    };

}
