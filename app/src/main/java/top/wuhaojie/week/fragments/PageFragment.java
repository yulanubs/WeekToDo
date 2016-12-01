package top.wuhaojie.week.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.wuhaojie.week.R;
import top.wuhaojie.week.adpter.TaskAdapter;
import top.wuhaojie.week.entities.TaskDetailEntity;

/**
 * Created by wuhaojie on 2016/11/29 21:05.
 */

public class PageFragment extends Fragment {

    public static final String TAG = "PageFragment";
    @BindView(R.id.rv)
    RecyclerView mRv;

    private List<TaskDetailEntity> mList = new ArrayList<>();
    private TaskAdapter mAdapter;

    public PageFragment() {
        Log.d("PageFragment", "constructor()");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.page_fragment, container, false);
        ButterKnife.bind(this, view);

        initViews();

        return view;
    }



    private void initViews() {
        mAdapter = new TaskAdapter(getActivity(), mList);
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void insertTask(TaskDetailEntity task) {
        if (!mList.contains(task))
            mList.add(task);
    }

    private OnPageFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPageFragmentInteractionListener) {
            mListener = (OnPageFragmentInteractionListener) context;
        } else {
            Log.e(TAG, "context is not instanceof OnPageFragmentInteractionListener");
        }
    }

    public interface OnPageFragmentInteractionListener {
    }

}
