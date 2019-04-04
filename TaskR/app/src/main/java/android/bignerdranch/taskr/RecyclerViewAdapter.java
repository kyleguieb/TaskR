package android.bignerdranch.taskr;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();

    public RecyclerViewAdapter(ArrayList<String> mTitles, ArrayList<String> mDates, ArrayList<String> mTimes) {
        this.mTitles = mTitles;
        this.mDates = mDates;
        this.mTimes = mTimes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tasks_title.setText(mTitles.get(position));
        holder.tasks_date.setText(mDates.get(position));
        holder.tasks_time.setText(mTimes.get(position));

    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tasks_title;
        TextView tasks_date;
        TextView tasks_time;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tasks_title = itemView.findViewById(R.id.task_title);
            tasks_date = itemView.findViewById(R.id.task_date);
            tasks_time = itemView.findViewById(R.id.task_time);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
