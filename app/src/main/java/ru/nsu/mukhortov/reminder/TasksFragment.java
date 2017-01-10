package ru.nsu.mukhortov.reminder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ivan on 09.01.17.
 */
public class TasksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;

    static TasksFragment newInstance(int page){
        TasksFragment tasksFragment = new TasksFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_PAGE_NUMBER, page);
        tasksFragment.setArguments(bundle);
        return tasksFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tasksfragment, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<String> myDataset = new ArrayList<>();
        myDataset.add("page =" + String.valueOf(pageNumber));
        myDataset.add("page =" + String.valueOf(pageNumber));
        myDataset.add("page =" + String.valueOf(pageNumber));
        myDataset.add("page =" + String.valueOf(pageNumber));
        myDataset.add("page =" + String.valueOf(pageNumber));
        myDataset.add("page =" + String.valueOf(pageNumber));
        myDataset.add("page =" + String.valueOf(pageNumber));
        myDataset.add("page =" + String.valueOf(pageNumber));

        mAdapter = new TaskAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
