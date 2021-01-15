package com.example.todolistf.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistf.R;
import com.example.todolistf.data.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecyclerViewTodoList extends RecyclerView.Adapter<RecyclerViewTodoList.MyViewHolder> {
    private static ArrayList<Task> mDataSet;
    private static MyClickListener myClickListener;
    private SimpleDateFormat simpleDateFormat;

    public static void setOnItemClickListener(MyClickListener myClickListener) {
        RecyclerViewTodoList.myClickListener = myClickListener;
    }

    public static ArrayList<Task> getmDataSet() {
        return mDataSet;
    }

    public RecyclerViewTodoList(ArrayList<Task> dataSet) {
        this.mDataSet = dataSet;
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    public interface MyClickListener{
        void onItemClick(int position, View v);
        void onSelected(int position, boolean isChecked);
        void onLongClick(int position, View v);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            holder.tvTitleTask.setText(mDataSet.get(position).getTitle());
            holder.tvDescription.setText(mDataSet.get(position).getDescription());
            holder.tvDate.setText(simpleDateFormat.format(mDataSet.get(position).getDate()));
            holder.cbIsFinished.setChecked(mDataSet.get(position).isFinished());
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        if(mDataSet!=null)
            return mDataSet.size();
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnLongClickListener {
        TextView tvTitleTask, tvDescription, tvDate;
        CheckBox cbIsFinished;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitleTask = itemView.findViewById(R.id.tvTitleTask);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.cbIsFinished = itemView.findViewById(R.id.cbFinisihed);
            cbIsFinished.setOnCheckedChangeListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, v);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = getAdapterPosition();
            myClickListener.onSelected(position, isChecked);
        }



        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            myClickListener.onLongClick(position, v);
            return false;
        }
    }
}
