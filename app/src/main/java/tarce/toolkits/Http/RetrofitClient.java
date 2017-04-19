package tarce.toolkits.Http;


import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Daniel.Xu on 2016/12/15.
 */

public class RetrofitClient {
    private  static  Retrofit retrofit;

   private RetrofitClient(Context context) {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(new OKHttpFactory(context).getOkHttpClient())

                //baseUrl
                .baseUrl("http://linkloving.com/linkloving_server-watch/")
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static Retrofit getInstance(Context context) {
            new RetrofitClient(context);
        return retrofit;
    }
}
