package android.bignerdranch.taskr;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<UUID> mIds = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<UUID> ids, ArrayList<String> titles, ArrayList<String> dates) {
        mIds = ids;
        mTitles = titles;
        mDates = dates;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tasks_title.setText(mTitles.get(position));
        holder.tasks_date.setText(mDates.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // if clicked on then should go to view task... IT WORKS! There is better way apparently

                //first, we have to specify which task in the recycler view we are selecting via the task name
                //CHANGE TO UUID
                Intent intent = TaskView.newIntent(mContext, mIds.get(position));
                mContext.startActivity(intent);

                //mContext.startActivity(new Intent(mContext, TaskView.class));

                //TO BE REMOVED - Just a test
                Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tasks_title;
        TextView tasks_date;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tasks_title = itemView.findViewById(R.id.task_title);
            tasks_date = itemView.findViewById(R.id.task_date);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
