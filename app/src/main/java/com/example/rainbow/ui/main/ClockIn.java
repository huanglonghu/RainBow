package com.example.rainbow.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.ClockInRecordResponse;
import com.example.rainbow.databinding.FragmentClockInBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.adapter.ClockInListAdapter;
import com.example.rainbow.util.GsonUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class ClockIn extends BaseFragment {

    private FragmentClockInBinding binding;
    private ArrayList<ClockInRecordResponse.DataBean.ItemsBean> datas;
    private ClockInListAdapter clockInListAdapter;
    private int currentYear;
    private int currentMonth;
    private Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clock_in, container, false);
        initCalendar();
        initView();
        initData();
        initlisten();
        return binding.getRoot();
    }

    private void initCalendar() {
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
    }

    @Override
    public void initData() {
        String[] dates = getDates(currentYear, currentMonth);
        refreshRecord(dates[0], dates[1], 1);
    }

    private void refreshRecord(String startDate, String endDate, int page) {
        HttpUtil.getInstance().getClockInRecord(startDate, endDate, page).subscribe(
                str -> {
                    if (page == 1 && datas.size() > 0) {
                        datas.clear();
                    }
                    ClockInRecordResponse clockInRecordResponse = GsonUtil.fromJson(str, ClockInRecordResponse.class);
                    ClockInRecordResponse.DataBean data = clockInRecordResponse.getData();
                    datas.addAll(data.getItems());
                    clockInListAdapter.notifyDataSetChanged();
                }
        );
    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        clockInListAdapter = new ClockInListAdapter(getContext(), datas, R.layout.lv_item_clockin);
        binding.lvClockIn.setAdapter(clockInListAdapter);

        String[] years = new String[10];
        for (int i = 0; i < 10; i++) {
            years[i] = (currentYear - i) + "年";
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), R.layout.spinner_item1, years);
        adapter2.setDropDownViewResource(R.layout.spinner_item1);
        binding.spYear.setAdapter(adapter2);
        String[] months = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), R.layout.spinner_item1, months);
        adapter1.setDropDownViewResource(R.layout.spinner_item1);
        binding.spMonth.setAdapter(adapter1);
        binding.spMonth.setSelection(currentMonth);

        binding.spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedItemPosition = binding.spYear.getSelectedItemPosition();
                int year = currentYear - selectedItemPosition;
                String[] dates = getDates(year, i + 1);
                refreshRecord(dates[0],dates[1],1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int year = currentYear - i;
                int month = binding.spMonth.getSelectedItemPosition()+1;
                String[] dates = getDates(year, month);
                refreshRecord(dates[0],dates[1],1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public String[] getDates(int year, int month) {
        String[] dates = new String[2];
        Calendar calendar = Calendar.getInstance();//获取当前时间
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dates[1] = format.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);//日期设置为一号，就是第一天了
        dates[0] = format.format(calendar.getTime());
        return dates;
    }


    @Override
    public void initlisten() {

        binding.toWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.getInstance().toWork().subscribe(
                        str -> {
                            Toast.makeText(getContext(), "上班打卡成功", Toast.LENGTH_SHORT).show();
                            //刷新打卡记录
//                            refreshRecord(1);

                        }
                );
            }
        });


        binding.offWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.getInstance().offWork().subscribe(
                        str -> {
                            Toast.makeText(getContext(), "下班打卡成功", Toast.LENGTH_SHORT).show();
                        }
                );
            }
        });


    }


}