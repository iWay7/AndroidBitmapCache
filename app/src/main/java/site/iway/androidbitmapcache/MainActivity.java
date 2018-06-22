package site.iway.androidbitmapcache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import site.iway.androidhelpers.BitmapView;
import site.iway.androidhelpers.UnitHelper;
import site.iway.androidhelpers.WindowHelper;

public class MainActivity extends Activity {

    private int mGridSideLength;

    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridSideLength = WindowHelper.getScreenWidth(this) / 2 - UnitHelper.dipToPxInt(5) * 2;

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setAdapter(mGridViewAdapter);
    }

    private BaseAdapter mGridViewAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return 157 * 8;
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
                convertView.getLayoutParams().width = mGridSideLength;
                convertView.getLayoutParams().height = (mGridSideLength - UnitHelper.dipToPxInt(5) * 2) * 1200 / 1920 + 20;
            }

            BitmapView bitmapView = (BitmapView) convertView.findViewById(R.id.bitmapView);
            bitmapView.loadFromURLSource(getItem(position));

            return convertView;
        }

    };

}
