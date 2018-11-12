package com.example.mysecondapplication;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyExpandableListActivity extends ExpandableListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_expandable_list_main);
        List<HashMap<String, String>> groups = new ArrayList<HashMap<String, String>>();
        String[] group_names = {"我的好友","初中同学","高中同学","大学同学","黑名单"};
        groups = this.addParentItems(groups, group_names);
        List<List<HashMap<String, String>>> group_list = new ArrayList<List<HashMap<String, String>>>();
        List<HashMap<String, String>> lists = new ArrayList<HashMap<String, String>>();
        lists = this.addChildsItems( lists, "小华");
        lists = this.addChildsItems( lists, "小立");
        lists = this.addChildsItems( lists, "小张");
        lists = this.addChildsItems( lists, "小进");

        List<HashMap<String, String>> lists2 = new ArrayList<HashMap<String, String>>();
        lists2 = this.addChildsItems( lists2, "CINDY");
        lists2 = this.addChildsItems( lists2, "ANNA");
        lists2 = this.addChildsItems( lists2, "TOM");
        lists2 = this.addChildsItems( lists2, "TINA");

        List<HashMap<String, String>> lists3 = new ArrayList<HashMap<String, String>>();
        List<HashMap<String, String>> lists4 = new ArrayList<HashMap<String, String>>();
        List<HashMap<String, String>> lists5 = new ArrayList<HashMap<String, String>>();

        group_list.add(lists);
        group_list.add(lists2);
        group_list.add(lists3);
        group_list.add(lists4);
        group_list.add(lists5);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, groups, R.layout.expandable_list_parent,
                new String[]{"groups"}, new int[]{R.id.groups}, group_list, R.layout.expandable_list_child, new String[]{"users"},
                new int[]{R.id.user_name});
        setListAdapter(adapter);
    }

    public List<HashMap<String, String>> addChildsItems(List<HashMap<String, String>> child_list, String item) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("users", item);
        child_list.add(map);
        return child_list;
    }


    public List<HashMap<String, String>> addParentItems(List<HashMap<String, String>> parent_groups, String[] parent_group_names) {

        for(int i=0;i<parent_group_names.length;i++){
            HashMap<String, String> groups = new HashMap<String, String>();
            groups.put("groups", parent_group_names[i]);
            parent_groups.add(groups);
        }

        return parent_groups;
    }

}
