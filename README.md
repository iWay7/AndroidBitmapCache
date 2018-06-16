# AndroidBitmapCache
Android 轻量图片缓存框架。

### 本应用的示例

![image](https://github.com/iWay7/AndroidBitmapCache/blob/master/sample.gif)   

### 本示例基于 AndroidHelpers 库，访问 https://github.com/iWay7/AndroidHelpers 添加依赖。

#### 第一步：在 Application 的 onCreate 方法中初始化：
```
BitmapCache.setIsDebugMode(BuildConfig.DEBUG); // 设置是否为调试模式，调试模式将在 Logcat 输出信息
BitmapCache.setContext(this); // 设置文件下载目录
BitmapCache.setLoaderCount(2); // 设置加载器的数量，数量越多意味着可同时加载的图片越多
BitmapCache.setLoaderThreadPriority(Thread.NORM_PRIORITY); // 设置加载器线程优先级
BitmapCache.setMaxRAMUsageForSingleBitmap(8 * 1024 * 1024); // 设置单张图片最大的内存使用量
BitmapCache.setMaxRamUsageOfAllBitmaps(DeviceHelper.getHeapGrowthLimit(this) / 2); // 设置最大的内存使用量
BitmapCache.setDownloaderClass(HttpFileDownloader.class); // 设置文件下载器
BitmapCache.setDownloadDirectory("BitmapCache"); // 设置文件下载缓存目录名称或自定义的目录
BitmapCache.initialize(); // 执行初始化
```

#### 第二步：添加 INTERNET 权限，如果没有的话：
```
<uses-permission android:name="android.permission.INTERNET" />
```

#### 然后就可以开心的使用啦：
##### 类似于 ImageView 在布局文件中声明一个 BitmapView 视图：
```
<site.iway.androidhelpers.BitmapView
    android:id="@+id/bitmapView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:scaleType="centerCrop" />
```

##### 然后给这个 BitmapView 设置一个来源：
```
BitmapView bitmapView = (BitmapView) findViewById(R.id.bitmapView);
bitmapView.loadFromURLSource(...);
```

##### 目前支持的 xml 配置选项：
```
app:backDrawable View 背景色之上的 Drawable
app:emptyDrawable View 内容为空时的 Drawable
app:errorDrawable View 内容加载错误时的 Drawable
app:finishAnimation View 内容加载完成时播放的动画
app:foreDrawable View 前景色之下的 Drawable
app:roundCornerRadius 对于加载的内容，采用圆角滤镜，必须设置 useDefaultFilter="true"
app:scaleType 对于加载的内容，设置一种缩放模式，参考 ImageView
app:useDefaultFilter 设置是否使用默认的滤镜
```

##### 目前支持的内容来源选项：
```
bitmapView.loadFromURLSource(...); // 从 URL 地址加载
bitmapView.loadFromAssetSource(...); // 从 Asset 加载
bitmapView.loadFromFileSource(...);  // 从文件加载
bitmapView.loadFromResourceSource(...); // 从资源加载
```

##### 支持自定义滤镜，例如可以对图片进行高斯模糊：
```
// 首先声明一个滤镜
private BitmapFilter mBitmapFilter = new BitmapFilter() {
    private int mBlurRadius = 10;
    
    // 滤镜的识别码，注意，如果滤镜有内部参数，则必须在名称中做区分
    @Override
    public String id() {
        return "blur-" + mBlurRadius;
    }
    
    // 滤镜的处理过程
    @Override
    public Bitmap filter(Bitmap bitmap) {
        return BitmapHelper.blur(bitmap, mBlurRadius);
    }
};

// 然后在设置来源的时候，带上该滤镜
bitmapView.loadFromURLSource(..., mBitmapFilter);
```
