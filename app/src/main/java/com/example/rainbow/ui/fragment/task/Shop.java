package com.example.rainbow.ui.fragment.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.ShopFaultResponse;
import com.example.rainbow.bean.ShopProfitLossResponse;
import com.example.rainbow.catche.Loader.RxImageLoader;
import com.example.rainbow.databinding.FragmentShopBinding;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.ClickSureListener;
import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.ui.adapter.ShopFaultListAdapter;
import com.example.rainbow.ui.adapter.ShopPageAdapter;
import com.example.rainbow.ui.adapter.ShopProfitLossAdapter;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.ui.widget.TipDialog;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.ImagUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

public class Shop extends BaseFragment {

    private FragmentShopBinding binding;
    private int id;
    private int shopId;
    private ArrayList<ListView> views;
    private Task task;
    private ShopProfitLossResponse.DataBean data;
    private List<ShopProfitLossResponse.DataBean.MachineProfitLossBean> datas1;
    private List<ShopFaultResponse.DataBean.MachineFaultBean> datas2;
    private ShopProfitLossAdapter shopProfitLossAdapter;
    private ShopFaultListAdapter shopFaultListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false);
            binding.setPresenter(Presenter.getInstance());
            initView();
            initlisten();
        }
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        if (datas1.size() > 0) {
            datas1.clear();
            shopProfitLossAdapter.clearView();
        }
        if (datas2.size() > 0) {
            datas2.clear();
            shopFaultListAdapter.clearView();
        }
        HttpUtil.getInstance().getShopDetail(id, shopId).subscribe(
                str -> {
                    handleData(str);
                }
        );
    }

    private void handleData(String str) {
        ShopProfitLossResponse shopDetailResponse = GsonUtil.fromJson(str, ShopProfitLossResponse.class);
        data = shopDetailResponse.getData();
        String image = data.getImage();
        String url = ImagUtil.handleUrl(image);
        if (!TextUtils.isEmpty(url)) {

            RxImageLoader.with(getContext()).load(url).into(binding.iv, 1);
        }
        List<ShopProfitLossResponse.DataBean.MachineProfitLossBean> machineProfitLoss = data.getMachineProfitLoss();
        if (machineProfitLoss != null && machineProfitLoss.size() > 0) {
            for (int i = 0; i < machineProfitLoss.size(); i++) {
                ShopProfitLossResponse.DataBean.MachineProfitLossBean machineProfitLossBean = machineProfitLoss.get(i);
                if (machineProfitLossBean.isIsFault()) {
                    ShopFaultResponse.DataBean.MachineFaultBean bean = new ShopFaultResponse.DataBean.MachineFaultBean();
                    bean.setId(machineProfitLossBean.getId());
                    bean.setIsFault(true);
                    bean.setMachineName(machineProfitLossBean.getMachineName());
                    datas2.add(bean);
                    shopFaultListAdapter.notifyDataSetChanged();
                }
            }
            datas1.addAll(machineProfitLoss);
            shopProfitLossAdapter.notifyDataSetChanged();
        }
        binding.setData(data);
    }


    @Override
    public void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        shopId = bundle.getInt("businessId");
        views = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            ListView listView = new ListView(getContext());
            int finalI = i;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                    if (finalI == 0) {
                        ShopProfitLossResponse.DataBean.MachineProfitLossBean machineProfitLossBean = datas1.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("machineName", machineProfitLossBean.getMachineName());
                        bundle.putInt("machineId", machineProfitLossBean.getId());
                        bundle.putInt("shopId", shopId);
                        bundle.putInt("jobId", id);
                        bundle.putBoolean("isRepair", false);
                        bundle.putBoolean("isShopSign", data.isIsSignIn());
                        Machine machine = new Machine();
                        machine.setArguments(bundle);
                        task.step2Task("machine", machine, " > " + machineProfitLossBean.getMachineName());
                    } else {
                        ShopFaultResponse.DataBean.MachineFaultBean bean = datas2.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("machineName", bean.getMachineName());
                        bundle.putInt("machineId", bean.getId());
                        bundle.putInt("id", shopId);
                        bundle.putBoolean("isShopSign", data.isIsSignIn());
                        Machine2 machine = new Machine2();
                        machine.setArguments(bundle);
                        task.step2Task("machine2", machine, " > " + bean.getMachineName());
                    }
                }
            });
            views.add(listView);
        }
        datas1 = new ArrayList<>();
        datas2 = new ArrayList<>();
        shopProfitLossAdapter = new ShopProfitLossAdapter(getContext(), datas1, R.layout.lv_item_shop_profitloss);
        views.get(0).setAdapter(shopProfitLossAdapter);
        shopFaultListAdapter = new ShopFaultListAdapter(getContext(), datas2, R.layout.lv_item_shop_fault);
        views.get(1).setAdapter(shopFaultListAdapter);
        String[] titles = getResources().getStringArray(R.array.shopItemTitle);
        ShopPageAdapter shopPageAdapter = new ShopPageAdapter(views, titles);
        binding.vp.setAdapter(shopPageAdapter);
        binding.tab.setViewPager(binding.vp);
    }

    @Override
    public void initlisten() {


        binding.jsyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipStr = getString(R.string.tip);
                TipDialog tipDialog = new TipDialog(getContext(), tipStr, new ClickSureListener() {
                    @Override
                    public void clickSure() {
                        HttpUtil.getInstance().shopRepeatCommit(shopId, id).subscribe(
                                str -> {

                                }
                        );
                    }
                });
                tipDialog.show();
            }
        });

        binding.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coordinate = data.getCoordinate();
                if (!TextUtils.isEmpty(coordinate)) {
                    String[] split = coordinate.split(",");
                    double la = Double.parseDouble(split[0]);
                    double lon = Double.parseDouble(split[1]);
                    Presenter.getInstance().startNaviGoogle(la, lon);
                }
            }
        });

        binding.toSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signed signed = new Signed();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("shopName", data.getShopName());
                bundle.putInt("shopId", shopId);
                signed.setArguments(bundle);
                String title = getString(R.string.sign);
                task.step2Task("sign", signed, " > " + title);
            }
        });

        binding.shopSettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopSettle shopSettle = new ShopSettle();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putInt("shopId", shopId);
                bundle.putBoolean("isShopSign", data.isIsSignIn());
                shopSettle.setArguments(bundle);
                String title = getString(R.string.shopSettle);
                task.step2Task("shopSettle", shopSettle, " > " + title);
            }
        });

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goCamera();
            }
        });

    }


    public void toggle(String name, BaseFragment fragment, String title) {
        task.step2Task(name, fragment, title);
    }


    //激活相机操作
    private void goCamera() {
        File cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //第二个参数为 包名.fileprovider
            uri = FileProvider.getUriForFile(getActivity(), "com.example.rainbow.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        new ActivityResultHandler.Builder().requestCode(ActivityResultHandler.REQUEST_TAKE_PHOTO).hadlerStrategy(new HandlerStrategy() {
            @Override
            public void onActivityResult() {

            }
        }).intent(intent).activity((AppCompatActivity) getActivity()).build().startActivityForResult();

    }


}
