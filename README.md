# AndroidBitmapCache
Android 轻量图片缓存框架。

### 本应用的示例

![image](https://github.com/iWay7/AndroidBitmapCache/blob/master/sample.gif)   

### 简单的集成方式

#### 第一步：在你的项目 build.gradle 添加 maven 库：
```
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
```

#### 第二步：在你的模块 build.gradle 添加依赖库：
```
dependencies {
    ...
    api 'com.github.iWay7:AndroidHelpers:1.0.4'
}
```

#### 第三步：在 Application 的 onCreate 方法中初始化：
```
BitmapCache.setIsDebugMode(BuildConfig.DEBUG); // 设置是否为调试模式，调试模式将在 Logcat 输出信息
BitmapCache.setLoaderCount(2); // 设置加载器的数量，数量越多意味着可同时加载的图片越多
BitmapCache.setMaxRAMUsage(DeviceHelper.getHeapGrowthLimit(this) / 3); // 设置最大的内存使用量
BitmapCache.setMaxRAMUsageForSingleBitmap(2 * 1024 * 1024); // 设置单张图片最大的内存使用量
BitmapCache.setLoaderThreadPriority(Thread.NORM_PRIORITY); // 设置加载器线程优先级
BitmapCache.setUrlConnectTimeout(20 * 1000); // 设置网络连接超时
BitmapCache.setUrlReadTimeout(20 * 1000); // 设置网络读取超时
BitmapCache.setUrlRetryCount(1); // 设置网络连接失败重试次数
BitmapCache.setDownloadDirectoryByContext(this, "BmpCache"); // 设置缓存目录的名称
BitmapCache.initialize(this); // 执行初始化
```

#### 第四步：添加 INTERNET 权限，如果没有的话：
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
    
    // 滤镜的处理过程
    @Override
    public Bitmap filter(Bitmap bitmap) {
        return BitmapHelper.blur(bitmap, mBlurRadius);
    }
    
    // 滤镜的名称，注意，如果滤镜有内部参数，则必须在名称中做区分
    @Override
    public String toString() {
        return "blur-" + mBlurRadius;
    }
};

// 然后在设置来源的时候，带上该滤镜
bitmapView.loadFromURLSource(..., mBitmapFilter);
```
