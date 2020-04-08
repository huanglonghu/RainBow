package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.LineSettleResponse;
import com.example.rainbow.bean.RouteSettleBody;
import com.example.rainbow.databinding.FragmentSettleAccountsBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.util.GsonUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class LineSettle extends BaseFragment {

    private FragmentSettleAccountsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settle_accounts, container, false);
        initData();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {

        int id = getArguments().getInt("id");
        HttpUtil.getInstance().getRouteWinlostDetail(id).subscribe(
                str -> {
                    LineSettleResponse lineSettleResponse = GsonUtil.fromJson(str, LineSettleResponse.class);
                    LineSettleResponse.DataBean data = lineSettleResponse.getData();
                    binding.setData(data);
                }
        );

    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {


        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.signArea.clear();
            }
        });

        RouteSettleBody routeSettleBody = new RouteSettleBody();

        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HttpUtil.getInstance().routeSettle(routeSettleBody).subscribe(
                        str -> {

                        }
                );

            }
        });

    }
}
