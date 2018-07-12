package com.example.jasperxiao.slidingconflicttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MRecyclerView rv;
    private MRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    private void initViews(){
        adapter = new MRvAdapter(this,loadDatas());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void findViews(){
        rv = findViewById(R.id.recycler_view);
    }

    private ElementInfo loadDatas(){
        List<Element> elementList = new ArrayList<>();
        List<List<Element>> elementListList = new ArrayList<>();

        // 加载elementList数据
        for(int i = 0;i < 80;i++){
            Element element = new Element(i + "");
            elementList.add(element);
        }

        // 加载elementListList数据
        for(int i = 0;i < 20;i++) {
            List<Element> tempList = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                Element element = new Element(i + "-" + j + "th");
                tempList.add(element);
            }
            elementListList.add(tempList);
        }

        return new ElementInfo(elementList,elementListList);
    }
}
