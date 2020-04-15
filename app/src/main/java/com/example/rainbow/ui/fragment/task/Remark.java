package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.RemarkBody;
import com.example.rainbow.databinding.FragmentRemarkBinding;
import com.example.rainbow.net.HttpUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class Remark extends BaseFragment {

    private FragmentRemarkBinding binding;
    private int shopId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_remark, container, false);
        initlisten();
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        Bundle arguments = getArguments();
        String remark = arguments.getString("remark");
        binding.remark.setText(remark);
        shopId = arguments.getInt("shopId");
    }

    @Override
    public void initView() {
    }

    @Override
    public void initlisten() {

        binding.addRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = binding.etRemark.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    String toastStr = getString(R.string.toastStr43);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                RemarkBody remarkBody = new RemarkBody();
                remarkBody.setRemarks(s);
                remarkBody.setShopId(shopId);
                HttpUtil.getInstance().updateRemark(remarkBody).subscribe(
                        str -> {
                            String toastStr = getString(R.string.toastStr44);
                            Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        }

                );
            }
        });

    }
}
