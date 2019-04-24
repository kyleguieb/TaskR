package android.bignerdranch.taskr;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class OtherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mThemes = new ArrayList<>();
    private ArrayList<String> mLevels = new ArrayList<>();
    private ArrayList<Integer> mColors = new ArrayList<>();
    private Context mContext;

    public OtherAdapter(Context context, ArrayList<String> themes, ArrayList<String> levels, ArrayList<Integer> colors) {
        mContext = context;
        mThemes = themes;
        mLevels = levels;
        mColors = colors;
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reward_layout, viewGroup, false);
        ViewHolder2 holder = new ViewHolder2(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder2 rewardHolder = (ViewHolder2) viewHolder;

            rewardHolder.reward_theme.setText(mThemes.get(i));
            rewardHolder.reward_level.setText(mLevels.get(i));
            //rewardHolder.reward_color.setBackgroundColor(mColors.get(i));

            rewardHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: is changing the first in the recycler view instead of Rewards screen stuff???

//                    final String rewardName = mThemes.get(i);
//                    final String rewardLevel = mLevels.get(i);
//                    final Integer rewardColor = mColors.get(i);
//
//                    final TextView mTheme = (TextView) ((Activity)mContext).findViewById(R.id.reward_name);
//                    final TextView mLevel = (TextView) ((Activity)mContext).findViewById(R.id.reward_price);
//
//                    mTheme.setText(rewardName);
//                    mLevel.setText(rewardLevel);

                }
            });
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{

        TextView reward_theme;
        TextView reward_level;
        //ImageView reward_color;
        RelativeLayout parentLayout;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            reward_theme = itemView.findViewById(R.id.reward_name);
            reward_level = itemView.findViewById(R.id.reward_price);
            parentLayout = itemView.findViewById(R.id.parent_layoutReward);

        }
    }
}
