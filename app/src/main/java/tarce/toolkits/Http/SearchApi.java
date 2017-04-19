package tarce.toolkits.Http;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tarce.toolkits.Http.resultBean.SearchBean;

/**
 * Created by Daniel.Xu on 2017/4/18.
 */

public interface SearchApi {
    @POST("MyControllerJSON")
    Call<SearchBean> getUnbundList(@Body HashMap hashMap );

}
