package android.bignerdranch.taskr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> titles, ArrayList<String> dates) {
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
        //pos = position;

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // if clicked on then should go to view task... IT WORKS! There is better way apparently

//                first, we have to specify which task in the recycler view we are selecting via the task name
//                Intent intent = TaskView.newIntent(mContext, mTitles.get(position),
//                                                    MainActivity.getTask(mTitles.get(position)).getmDescription(),
//                                                    MainActivity.getTask(mTitles.get(position)).getmDateAndTimeDue());
//                mContext.startActivity(intent);
//
//                mContext.startActivity(new Intent(mContext, TaskView.class));

                //TO BE REMOVED - Just a test
                Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();

                // Dialog - Task Viewing
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                View mView = inflater.inflate(R.layout.dialog_task, null);

                EditText mTitle = (EditText) mView.findViewById(R.id.dialogTitle);
                EditText mDate = (EditText) mView.findViewById(R.id.dialogDate);
                EditText mTime = (EditText) mView.findViewById(R.id.dialogTime);
                EditText mDescription = (EditText) mView.findViewById(R.id.dialogDescription);

                Button mEdit = (Button) mView.findViewById(R.id.editButton);
                Button mDelete = (Button) mView.findViewById(R.id.deleteButton);

                mEdit.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                    Intent intent = TaskView.newIntent(mContext, mTitles.get(position),
                            MainActivity.getTask(mTitles.get(position)).getmDescription(),
                            MainActivity.getTask(mTitles.get(position)).getmDateAndTimeDue());
                    mContext.startActivity(intent);

                    }
                });

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mDelete.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        MainActivity.deleteTask(mTitles.get(position));
                        dialog.dismiss();
                        mContext.startActivity(new Intent(mContext, MainActivity.class)); //TODO: Needs to be fixed
                    }
                });
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


// Back in time version TODO: So much to fix