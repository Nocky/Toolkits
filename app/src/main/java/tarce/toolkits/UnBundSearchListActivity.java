package tarce.toolkits;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tarce.toolkits.Http.RetrofitClient;
import tarce.toolkits.Http.SearchApi;
import tarce.toolkits.Http.resultBean.DetailValue;
import tarce.toolkits.Http.resultBean.SearchBean;

/**
 * Created by Daniel.Xu on 2017/4/18.
 */

public class UnBundSearchListActivity extends Activity {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerView ;
    private Retrofit retrofit;
    private SearchApi searchApi;
    private List<DetailValue> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbundsearch_list);
        ButterKnife.inject(this);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        list = (List<DetailValue>)bundle.get("list");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UnBundSearchListActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(UnBundSearchListActivity.this,
                DividerItemDecoration.VERTICAL));
        retrofit = RetrofitClient.getInstance(this);
        searchApi = retrofit.create(SearchApi.class);
        initSearch();
    }

    private void initSearch() {
        MyAdapter myAdapter = new MyAdapter(R.layout.detail_item, list);
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                new AlertDialog.Builder(UnBundSearchListActivity.this)
                        .setMessage("确认解绑？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                        objectObjectHashMap.put("processorId",1008);
                        objectObjectHashMap.put("jobDispatchId",1);
                        objectObjectHashMap.put("actionId",25);
                        String user_id = list.get(position).getUser_id();
                        objectObjectHashMap.put("newData","{user_id: \"" +user_id+"\"}");
                        final Call<SearchBean> unbundList = searchApi.getUnbundList(objectObjectHashMap);
                        unbundList.enqueue(new Callback<SearchBean>() {
                            @Override
                            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                                String returnValue = response.body().getReturnValue();
                                if (returnValue.equals("true")){
                                    Toast.makeText(UnBundSearchListActivity.this,"解绑成功",Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<SearchBean> call, Throwable t) {
                            }
                        });
                    }
                }).create().show();
            }
        });
        recyclerView.setAdapter(myAdapter);

    }


    private  class  MyAdapter extends BaseQuickAdapter<DetailValue,BaseViewHolder>{

        public MyAdapter(int layoutResId, List<DetailValue> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DetailValue item) {
                helper.setText(R.id.name,item.getNickname())
                        .setText(R.id.phone,item.getUser_mobile())
                        .setText(R.id.email,item.getUser_mail())
                        .setText(R.id.mac,item.getLast_sync_device_id());
        }
    }


}
