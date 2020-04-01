package com.example.rainbow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.NoticeDetailResponse;
import com.example.rainbow.databinding.FragmentNoticeDetailBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;


public class NoticeDetail extends BaseFragment {

    private FragmentNoticeDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice_detail, container, false);
        initView();
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            int id = bundle.getInt("id");
            HttpUtil.getInstance().getNoticeDetailById(id).subscribe(
                    str -> {

                        NoticeDetailResponse noticeDetailResponse = GsonUtil.fromJson(str, NoticeDetailResponse.class);
                        NoticeDetailResponse.DataBean data = noticeDetailResponse.getData();
                        String content = data.getContent();
                        String html = getNewContent(content);
                        binding.setBean(data);
                        binding.content.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

                    }
            );
        }
    }

    public String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Element head = doc.head();
        head.append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\" />");
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            LogUtil.log("11=================element==============" + element.toString());
            element.attr("width", "100%")
                    .attr("height", "auto")
                    .attr("img_width", "100%");

            element.attr("style", null);
            //element.attr("style","width:100%;"+"height:auto;");
            LogUtil.log("22=================element==============" + element.toString());
        }
        String html = doc.toString();
        LogUtil.log("==========AAAAAAAAAAAA==========" + html);
        return html;
    }


    @Override
    public void initView() {


    }

    @Override
    public void initlisten() {

    }
}
