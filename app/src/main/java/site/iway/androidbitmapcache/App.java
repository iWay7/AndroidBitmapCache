package site.iway.androidbitmapcache;

import android.app.Application;

import site.iway.androidhelpers.BitmapCache;
import site.iway.androidhelpers.DeviceHelper;
import site.iway.javahelpers.HttpFileDownloader;

/**
 * Created by iWay on 2018/3/25.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BitmapCache.setContext(this);
        BitmapCache.setIsDebugMode(BuildConfig.DEBUG);
        BitmapCache.setLoaderCount(2);
        BitmapCache.setLoaderThreadPriority(Thread.NORM_PRIORITY);
        BitmapCache.setMaxRAMUsageForSingleBitmap(8 * 1024 * 1024);
        BitmapCache.setMaxRamUsageOfAllBitmaps(DeviceHelper.getHeapGrowthLimit(this) / 2);
        BitmapCache.setDownloaderClass(HttpFileDownloader.class);
        BitmapCache.setDownloadDirectory("BitmapCache");
        BitmapCache.initialize();
    }
}
