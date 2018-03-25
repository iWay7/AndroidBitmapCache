package site.iway.androidbitmapcache;

import android.app.Application;

import site.iway.androidhelpers.BitmapCache;
import site.iway.androidhelpers.DeviceHelper;

/**
 * Created by iWay on 2018/3/25.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BitmapCache.setIsDebugMode(BuildConfig.DEBUG);
        BitmapCache.setLoaderCount(2);
        BitmapCache.setMaxRAMUsage(DeviceHelper.getHeapGrowthLimit(this) / 3);
        BitmapCache.setMaxRAMUsageForSingleBitmap(2 * 1024 * 1024);
        BitmapCache.setLoaderThreadPriority(Thread.NORM_PRIORITY);
        BitmapCache.setUrlConnectTimeout(20 * 1000);
        BitmapCache.setUrlReadTimeout(20 * 1000);
        BitmapCache.setUrlRetryCount(1);
        BitmapCache.setDownloadDirectoryByContext(this, "BmpCache");
        BitmapCache.initialize(this);
    }
}
