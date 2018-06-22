package site.iway.androidbitmapcache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import site.iway.androidhelpers.BitmapView;
import site.iway.androidhelpers.WindowHelper;

public class MainActivity extends Activity {

    private int mGridItemWidth;

    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setAdapter(mGridViewAdapter);

        int windowWidth = WindowHelper.getScreenWidth(this);
        int paddingLeft = mGridView.getPaddingLeft();
        int paddingRight = mGridView.getPaddingRight();
        mGridItemWidth = (windowWidth - paddingLeft - paddingRight) / 2;
    }

    private BaseAdapter mGridViewAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return 157 * 16;
        }

        @Override
        public String getItem(int position) {
            return "http://home.iway.site:8888/test/images/image%20(" + (position % 157 + 1) + ").jpg?position=" + position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_bitmap_cache, mGridView, false);
                int paddingLeft = convertView.getPaddingLeft();
                int paddingRight = convertView.getPaddingRight();
                int paddingTop = convertView.getPaddingTop();
                int paddingBottom = convertView.getPaddingBottom();
                int imageWidth = mGridItemWidth - paddingLeft - paddingRight;
                int imageHeight = imageWidth * 1200 / 1920;
                convertView.getLayoutParams().width = imageWidth + paddingLeft + paddingRight;
                convertView.getLayoutParams().height = imageHeight + paddingTop + paddingBottom;
            }

            BitmapView bitmapView = (BitmapView) convertView.findViewById(R.id.bitmapView);
            bitmapView.loadFromURLSource(getItem(position));

            return convertView;
        }

    };

}
