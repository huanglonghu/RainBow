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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clock_in, container, false);
            initCalendar();
            initView();
            initData();
            initlisten();
        }

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
        refreshRecord(dates[0], dates[1]);
    }

    private int page = 1;

    private void refreshRecord(String startDate, String endDate) {
        HttpUtil.getInstance().getClockInRecord(startDate, endDate, page).subscribe(
                str -> {
                    if (page == 1 && datas.size() > 0) {
                        datas.clear();
                    }
                    ClockInRecordResponse clockInRecordResponse = GsonUtil.fromJson(str, ClockInRecordResponse.class);
                    ClockInRecordResponse.DataBean data = clockInRecordResponse.getData();
                    List<ClockInRecordResponse.DataBean.ItemsBean> items = data.getItems();
                    binding.refreshLayout.finishLoadMore();
                    if (items != null && items.size() > 0) {
                        datas.addAll(items);
                        page++;
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false);
                    }
                    clockInListAdapter.notifyDataSetChanged();
                }
        );
    }

    private boolean isInit1;

    private boolean isInit2;

    @Override
    public void initView() {
        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
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
        String[] months = new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), R.layout.spinner_item1, months);
        adapter1.setDropDownViewResource(R.layout.spinner_item1);
        binding.spMonth.setAdapter(adapter1);
        binding.spMonth.setSelection(currentMonth);

        binding.spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (isInit1) {
                    int selectedItemPosition = binding.spYear.getSelectedItemPosition();
                    int year = currentYear - selectedItemPosition;
                    String[] dates = getDates(year, i + 1);
                    refreshRecord(dates[0], dates[1]);
                }
                isInit1 = true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        binding.spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isInit2) {
                    int year = currentYear - i;
                    int month = binding.spMonth.getSelectedItemPosition() + 1;
                    String[] dates = getDates(year, month);
                    refreshRecord(dates[0], dates[1]);
                }
                isInit2 = true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }


    public String[] getDates(int year, int month) {
        String[] dates = new String[2];
        Calendar calendar = Calendar.getInstance();//获取当前时间
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
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
                            String toastStr = getString(R.string.toastStr16);
                            Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                            int year = currentYear - binding.spYear.getSelectedItemPosition();
                            int month = binding.spMonth.getSelectedItemPosition() + 1;
                            String[] dates = getDates(year, month);
                            refreshRecord(dates[0], dates[1]);
                        }
                );
            }
        });


        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                int position1 = binding.spYear.getSelectedItemPosition();
                int position2 = binding.spMonth.getSelectedItemPosition();
                int year = currentYear - position1;
                int month = position2 + 1;
                String[] dates = getDates(year, month);
                refreshRecord(dates[0], dates[1]);

            }
        });


        binding.offWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.getInstance().offWork().subscribe(
                        str -> {
                            String toastStr = getString(R.string.toastStr20);
                            Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        }
                );
            }
        });


    }


}
