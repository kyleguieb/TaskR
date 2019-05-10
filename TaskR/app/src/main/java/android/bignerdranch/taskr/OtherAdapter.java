package android.bignerdranch.taskr;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class OtherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mThemes = new ArrayList<>();
    private ArrayList<String> mLevels = new ArrayList<>();
    private ArrayList<Integer> mPrimaryColors = new ArrayList<>();
    private ArrayList<Integer> mPrimaryDarkColors = new ArrayList<>();
    private ArrayList<Integer> mPrimaryAccentColors = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();
    private Context mContext;

    public OtherAdapter(Context context, ArrayList<String> themes, ArrayList<String> levels, ArrayList<Integer> primary, ArrayList<Integer> dark, ArrayList<Integer> accent, ArrayList<String> description) {
        mContext = context;
        mThemes = themes;
        mLevels = levels;
        mPrimaryColors = primary;
        mPrimaryDarkColors = dark;
        mPrimaryAccentColors = accent;
        mDescriptions = description;
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

        if (Rewards.isUnlocked(mLevels.get(i))) {
            rewardHolder.set_theme.setText(R.string.set);
            rewardHolder.set_theme.setEnabled(true);
            // Todo: Needs to be fixed
            rewardHolder.set_theme.setBackgroundColor(R.attr.primaryDark);
        } else {
            rewardHolder.set_theme.setText(mLevels.get(i));
            rewardHolder.set_theme.setEnabled(false);
            rewardHolder.set_theme.setBackgroundColor(R.color.colorDisabled);
        }

        rewardHolder.reward_theme.setText(mThemes.get(i));
        //rewardHolder.reward_level.setText(mLevels.get(i));
        rewardHolder.set_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i)
                {
                    case 0:
                        Utils.changeToTheme((Activity)mContext, Utils.THEME_DEFAULT);
                        break;
                    case 1:
                        Utils.changeToTheme((Activity)mContext, Utils.THEME_LILAC);
                        break;
                    case 2:
                        Utils.changeToTheme((Activity)mContext, Utils.THEME_MINT);
                        break;
                    case 3:
                        Utils.changeToTheme((Activity)mContext, Utils.THEME_ECHO);
                        break;
                    case 4:
                        Utils.changeToTheme((Activity)mContext, Utils.THEME_SNS);
                        break;
                    case 5:
                        Utils.changeToTheme((Activity)mContext, Utils.THEME_QP);
                        break;
                    case 6:
                        Utils.changeToTheme((Activity)mContext, Utils.THEME_WISHCRAFT);
                        break;
                }
            }
        });

            rewardHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String rewardName = mThemes.get(i);
                    final String rewardLevel = mLevels.get(i);
                    final Integer rewardColorPrimary = mPrimaryColors.get(i);
                    final Integer rewardColorDark = mPrimaryDarkColors.get(i);
                    final Integer rewardColorAccent = mPrimaryAccentColors.get(i);
                    final String rewardDescription = mDescriptions.get(i);

                    final TextView mTheme = (TextView) ((Activity)mContext).findViewById(R.id.rewardName);
                    final TextView mLevel = (TextView) ((Activity)mContext).findViewById(R.id.priceCost);
                    final ImageView mColorPrimary = (ImageView) ((Activity) mContext).findViewById(R.id.primary);
                    final ImageView mColorDark = (ImageView) ((Activity) mContext).findViewById(R.id.primarydark);
                    final ImageView mColorAccent = (ImageView) ((Activity) mContext).findViewById(R.id.acccent);
                    final TextView mDescription = (TextView) ((Activity) mContext).findViewById(R.id.rewardDescription);

                    mTheme.setText(rewardName);
                    mLevel.setText(rewardLevel);
                    mColorPrimary.setBackgroundResource(rewardColorPrimary);
                    mColorDark.setBackgroundResource(rewardColorDark);
                    mColorAccent.setBackgroundResource(rewardColorAccent);
                    mDescription.setText(rewardDescription);

                }
            });
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{

        TextView reward_theme;
        //TextView reward_level;
        Button set_theme;
        RelativeLayout parentLayout;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            reward_theme = itemView.findViewById(R.id.reward_name);
            //reward_level = itemView.findViewById(R.id.reward_price);
            set_theme = itemView.findViewById(R.id.setButton);
            parentLayout = itemView.findViewById(R.id.parent_layoutReward);

        }
    }
}
