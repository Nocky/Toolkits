package tarce.toolkits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tarce.toolkits.Http.resultBean.DetailValue;
import tarce.toolkits.Http.RetrofitClient;
import tarce.toolkits.Http.SearchApi;
import tarce.toolkits.Http.resultBean.SearchBean;


/**
 * Created by Daniel.Xu on 2017/4/18.
 */

public class UnBundActivity extends Activity{
    private Retrofit retrofit;
    private SearchApi searchApi;
    @InjectView(R.id.editTextMac)
    EditText editTextMac ;
    @InjectView(R.id.editTextPhone)
    EditText editTextPhone ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbund);
        ButterKnife.inject(this);
        retrofit = RetrofitClient.getInstance(this);
        searchApi = retrofit.create(SearchApi.class);
    }

    @OnClick(R.id.search_button)
    void setSearchButton(View view){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("processorId",1018);
        objectObjectHashMap.put("jobDispatchId",6);
        objectObjectHashMap.put("actionId",5);
        String info = null ;
        String macString = editTextMac.getText().toString();

        if (macString!=null&&macString.length()>0){
            String substring = macString.substring(0, 2);
            String substring1 = macString.substring(2, 4);
            info = substring+":"+substring1;
        }
        String phoneString = editTextPhone.getText().toString();
        if (phoneString!=null&&phoneString.length()>0){
            info = phoneString ;
        }
//        HashMap<Object, Object> objectObjectHashMap1 = new HashMap<>();
//        objectObjectHashMap1.put("condition",info);
//        objectObjectHashMap.put("newData",objectObjectHashMap1);
        objectObjectHashMap.put("newData","{condition: \"" +info+"\"}");
        Call<SearchBean> unbundList = searchApi.getUnbundList(objectObjectHashMap);
        unbundList.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                String returnValue = response.body().getReturnValue();
                List<DetailValue> detailValues = JSON.parseArray(returnValue, DetailValue.class);
                Intent intent = new Intent(UnBundActivity.this, UnBundSearchListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) detailValues);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {

            }
        });


    }


}
