package com.nanfeng.imageframeworkdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final String URL = "http://imgsrc.baidu.com/image/c0%3Dpixel_huitu%2C0%2C0%2C294%2C40/sign=064cf83a8c44ebf8797c6c7fb081b246/0dd7912397dda144c018ad8bb9b7d0a20cf486f2.jpg";
    private static final String GIF_URL = "https://wx1.sinaimg.cn/mw690/e75a115bgy1fl405nawdwg209q058b2b.gif";
    private static  ArrayList<String> urlList = new ArrayList<>();

    ImageView imageView;
    Button button;
    RecyclerView vRecycler;
    SimpleDraweeView draweeView;

    long startTime;

//    static {
//        urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//    urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//    urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//    urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//    urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//    urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//    urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//    urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
//        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
//        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.image);
        vRecycler = findViewById(R.id.v_recycler);
        button = findViewById(R.id.btn_load);
       // draweeView = findViewById(R.id.image_fresco);

        final ListAdapter adapter = new ListAdapter(this,urlList);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // loadByGlide();

                //initFresco();
                //frescoGif();
                //loadByGlide();
                urlList.remove(0);
                adapter.notifyItemRangeChanged(0,urlList.size()-1);
                //loadByImageloader();
            }
        });
        vRecycler.setLayoutManager(new LinearLayoutManager(this));
        vRecycler.setAdapter(adapter);

        urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509574&di=c038b6efaba980772e4e2c1a24c364f0&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.pp3.cn%2Fuploads%2F201502%2F2015022805.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914850151&di=64fea76b57f78df1819d0c1e51066c30&imgtype=0&src=http%3A%2F%2Fpic74.nipic.com%2Ffile%2F20150809%2F21519723_051112912000_2.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516509548&di=1b3215666b4e70c8f43538f14afeefc7&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Da7f379c370cb0a46912f8379021b9348%2F11385343fbf2b21138131e77c18065380cd78ece.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914809836&di=4c3ffe7ba450ea7f50c4799832476c99&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F83025aafa40f4bfb982b2e80094f78f0f636186e.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914815530&di=6cfe78f49ae318db653ce064d7dfc3b4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515914803230&di=13418a3d54cc009bcd9bf69074cabb08&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dea40d0169ecad1c8c4b6f46716460265%2Fdcc451da81cb39db6cf3a76fdb160924ab1830dc.jpg");
        urlList.add("http://img.tuku.cn/file_thumb/201504/m2015040215355591.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516515528&di=f116dcbc737c2dfda93193fbbdb8c85c&imgtype=jpg&er=1&src=http%3A%2F%2Fpica.nipic.com%2F2008-03-15%2F2008315114452599_2.jpg");
        urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=887138385,830579878&fm=27&gp=0.jpg");

        adapter.notifyItemRangeChanged(0,urlList.size()+10);

        //loadByImageloader();
        //loadByGlide();
    }

    private void initFresco() {
        startTime = System.currentTimeMillis();
        ControllerListener controllerListener = new BaseControllerListener(){
            @Override
            public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                long time = System.currentTimeMillis() - startTime;
                Log.e(" frescotime",time + "");
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable Object imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
                Log.e(" frescotime", "onIntermediateImageSet");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Log.e(" frescotime",  "onFailure");
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(Uri.parse(URL))
                .build();
        draweeView.setController(controller);
    }

    private void frescoGif(){
        startTime = System.currentTimeMillis();
        ControllerListener controllerListener = new BaseControllerListener(){
            @Override
            public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                long time = System.currentTimeMillis() - startTime;
                Log.e(" frescotime",time + "");
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable Object imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
                Log.e(" frescotime", "onIntermediateImageSet");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Log.e(" frescotime",  "onFailure");
            }
        };


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse(GIF_URL))
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(controller);
    }

    private void loadByGlide() {

        RequestOptions options = new RequestOptions().dontTransform()
                .skipMemoryCache(false);
        startTime = System.currentTimeMillis();

        Glide.with(this).load(URL).apply(options)
        .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.e("onLoadFailed", e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                long time = System.currentTimeMillis() - startTime;
                Log.e("Glidetime",time + "");
                return false;
            }


//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
//                long time = System.currentTimeMillis() - startTime;
//                Log.e("Glidetime",time + "");
//                return false;
//            }
        }).into(imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //loadByGlide();
        //loadByImageloader();
    }

    private void loadByImageloader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false).cacheOnDisk(false).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader loader=ImageLoader.getInstance();
        loader.init(config);

       // loader.displayImage("http://picm.photophoto.cn/006/018/030/0180300367.jpg",imageView);
        startTime = System.currentTimeMillis();

        loader.displayImage("https://wx1.sinaimg.cn/mw690/e75a115bgy1fl405nawdwg209q058b2b.gif", imageView, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                long time = System.currentTimeMillis() - startTime;
                Log.e(" ImageLoadertime",time + "");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }
}
