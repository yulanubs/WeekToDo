package top.wuhaojie.week.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import top.wuhaojie.week.R;
import top.wuhaojie.week.entities.TaskDetailEntity;

/**
 * Created by wuhaojie on 2016/11/29 21:35.
 */

public class TaskAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<TaskDetailEntity> mList;

    public TaskAdapter(Context context, List<TaskDetailEntity> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.task_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h = (Holder) holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private static class Holder extends RecyclerView.ViewHolder {

        TextView mTitle;

        public Holder(View itemView) {
            super(itemView);
        }
    }


}