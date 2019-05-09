package android.bignerdranch.taskr;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Rewards extends AppCompatActivity {

    final int QUICK_EXP = 10;
    final int NORMAL_EXP = 20;
    final int LONG_EXP = 30;

    //Probably really bad fix, but it works LOL. Explanation below at initTasks()
    private ArrayList<UUID> mIds = new ArrayList<>();
    private ArrayList<String> mTaskTitles = new ArrayList<>();
    private ArrayList<String> mDatesNTimes = new ArrayList<>();
    private ArrayList<String> mDifficulties = new ArrayList<>();
    private ArrayList<String> mDateCreated = new ArrayList<>();
    private ArrayList<Task> listOfTasks = MainActivity.getTasks();

    //Vars for grid
//    private GridView book;
//    private GridViewAdapter gridAdapter;
    //Vars for stickers
    public int global_sticker_counter = 0;
    private ImageView mStickers;
    private TextView mStickerNames;
    private TextView mPages;
    public int[] mImages = {
            R.drawable.coolcrumpledpaper,
            R.drawable.computer_locked,
            R.drawable.image_locked
    };
    String name = (global_sticker_counter + 1) + " / " + mImages.length;
    String[] stickerIDs;

    public static final double SCALE = 1.1; //Scale determines how fast to scale the xpToLevel.
    public static final int XP_BASE = 10;
    private static ProgressBar mProgressBar;
    private TextView mExperienceCounter;
    private static TextView mLevels;

    //creates User
    public List<User> listOfUsers = MainActivity.getUsers();

    private static int currentExp;
    private static int currentLevel;
    private static int xpToLevel = XP_BASE;

    Random random = new Random(); //for xp granting

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(Rewards.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    finish();
                    startActivity(new Intent(Rewards.this, CalendarActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    finish();
                    startActivity(new Intent(Rewards.this, Profile.class));
                    return true;
                case R.id.navigation_rewards:
                    //startActivity(new Intent(Themes.this, Themes.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_rewards);

        initUser();
        xpToLevel =(int)Math.pow((XP_BASE * currentLevel), SCALE); //makes sure the xpToLevel is consistent every startup

        mProgressBar = findViewById(R.id.progressingBar);
        mExperienceCounter = findViewById(R.id.textViewExperience);
        mLevels = findViewById(R.id.textViewLevel);


        //Set text here just to display it properly between screens :^)
        mLevels.setText(currentLevel + "");
        mExperienceCounter.setText(currentExp + " / " + xpToLevel );
        mProgressBar.setProgress(currentExp);
        mProgressBar.setMax(xpToLevel);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_rewards);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        book = (GridView) findViewById(R.id.bookGrid);
//        gridAdapter = new GridViewAdapter(this, R.layout.sticker_layout, getData());
//        book.setAdapter(gridAdapter);

        //Sticker book stuff
        stickerIDs = getResources().getStringArray(R.array.sticker_titles);
        mStickers = findViewById(R.id.sticker);
        mStickers.setImageDrawable(getResources().getDrawable(mImages[global_sticker_counter]));
        mPages =findViewById(R.id.pages);
        mPages.setText(name);
        mStickerNames = findViewById(R.id.sticker_Name);
        mStickerNames.setText(stickerIDs[global_sticker_counter]);

        initTasks();

        //instantly does all the exp checking instead once per screen refresh
        if (MainActivity.firstStart) //Refer to MainActivity.java line:58
        {
            for(int j = listOfTasks.size() - 1; j > 0; j--)
            {
                String difficulty = listOfTasks.get(j).getDifficulty();
                expCheck(difficulty);
            }
        }

        if (!MainActivity.firstStart)//Refer to MainActivity.java line:58
        {
            MainActivity.globalTaskFinishedCounter = mIds.size();
            MainActivity.firstStart = true;
        }

        mProgressBar.setProgress(currentExp);

        defineButtons();

    }

    public void defineButtons() {
        findViewById(R.id.themeChange).setOnClickListener(buttonClickListener);
        //findViewById(R.id.stickerBook).setOnClickListener(buttonClickListener);
        findViewById(R.id.nextPage).setOnClickListener(buttonClickListener);
        findViewById(R.id.previousPage).setOnClickListener(buttonClickListener);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.themeChange:
                    finish();
                    startActivity(new Intent(Rewards.this, Themes.class));
                    break;
//                case R.id.stickerBook:
//                    startActivity(new Intent(Rewards.this, Stickers.class));
//                    break;
                case R.id.nextPage:
                    if (global_sticker_counter == (mImages.length - 1)){
                        global_sticker_counter = 0;
                    } else {
                        global_sticker_counter++;
                    }
                    mStickers.setImageDrawable(getResources().getDrawable(mImages[global_sticker_counter]));
                    name = (global_sticker_counter + 1) + " / " + mImages.length;
                    mPages.setText(name);
                    mStickerNames.setText(stickerIDs[global_sticker_counter]);
                    break;

                case R.id.previousPage:
                    if (global_sticker_counter == (mImages.length - 1)){
                        global_sticker_counter = 0;
                    } else {
                        global_sticker_counter--;
                    }
                    mStickers.setImageDrawable(getResources().getDrawable(mImages[global_sticker_counter]));
                    name = (global_sticker_counter + 1) + " / " + mImages.length;
                    mPages.setText(name);
                    mStickerNames.setText(stickerIDs[global_sticker_counter]);
                    break;

            }
        }
    };

    private void initTasks() {

        //TODO: Should check to make sure it is working 100%


        for(int i = 0; i < listOfTasks.size(); i++)
        {
            if (listOfTasks.get(i).isCompleted()) {
                mIds.add(listOfTasks.get(i).getId());
                mTaskTitles.add(listOfTasks.get(i).getmName());
                mDatesNTimes.add(listOfTasks.get(i).getmDateAndTimeDue());
                mDifficulties.add(listOfTasks.get(i).getDifficulty());
                mDateCreated.add(listOfTasks.get(i).getDateCreated());
            }
        }
    }

    //Used in GridView stuff
//    private ArrayList<ImageItem> getData() {
//        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.stickers_id);
//        String[] itemsArray = getResources().getStringArray(R.array.sticker_titles);
//
//        for (int i = 0; i < imgs.length(); i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
//            imageItems.add(new ImageItem(bitmap, itemsArray[i]));
//        }
//
//        return imageItems;
//    }

    private void initUser()
    {
        //it specifically gets the zeroth position cause that's what it should always do
        //#SoloUserApplication btw
        User user = listOfUsers.get(0);
        currentLevel = user.getLevel();
        currentExp = user.getExpToNextLevel();
    }

    private static void updateUser(int level, int exp)
    {
        User newUser = new User(level, exp);
        MainActivity.updateUser(newUser);
    }

    public static void isLevelUp()
    {
        //THIS LOGIC FIXES THE OVERFLOW ISSUE. NEVER COMPARE PROGRESSBAR.PROGRESS
        if (currentExp >= xpToLevel)
        {
            mProgressBar.setProgress(0);
            currentLevel++;
            xpToLevel = (int)Math.pow((XP_BASE * currentLevel), SCALE); // Algorithm to determine how many exp for next level
            mProgressBar.setMax(xpToLevel);
            currentExp = 0;
            mLevels.setText(currentLevel + ""); //no idea why you need to add_icon a blank thing but ok
        }
    }

    public void expCheck(String difficulty)
    {
        if (MainActivity.globalTaskFinishedCounter < mIds.size())
        {
            addExp(difficulty);

            MainActivity.globalTaskFinishedCounter++;
        }
        else if (MainActivity.globalTaskFinishedCounter > mIds.size())
        {
            while(MainActivity.globalTaskFinishedCounter > mIds.size())
            {
                MainActivity.globalTaskFinishedCounter--;
            }
        }
    }

    private void addExp(String difficulty)
    {
        int xpLoop = 0;

        if (difficulty.equals("Quick"))
        {
            while (xpLoop < QUICK_EXP) //Possibly make into constant
            {
                isLevelUp();
                mProgressBar.incrementProgressBy(1);
                currentExp +=1;
                xpLoop++;
            }
        }
        else if(difficulty.equals("Normal"))
        {
            while (xpLoop < NORMAL_EXP) //Possibly make into constant
            {
                isLevelUp();
                mProgressBar.incrementProgressBy(1);
                currentExp++;
                xpLoop++;
            }
        }
        else if(difficulty.equals("Long"))
        {
            while (xpLoop < LONG_EXP) //Possibly make into constant
            {
                isLevelUp();
                mProgressBar.incrementProgressBy(1);
                currentExp++;
                xpLoop++;
            }
        }

        mExperienceCounter.setText(currentExp + " / " + xpToLevel );
        updateUser(currentLevel,currentExp);
    } //end AddEXP

// OLD VERSION
//    private void addExp()
//    {
//        //Generate random xp from 5-25
//        int randomXP = random.nextInt(25-5) + 5;
//        int xpLoop = 0; //just a counter for the while loop
//        while(xpLoop < randomXP)// loops through giving xp
//        {
//            isLevelUp();
//            mProgressBar.incrementProgressBy(1);
//            currentExp +=1;
//            mExperienceCounter.setText(currentExp + " / " + xpToLevel );
//            xpLoop++;
//        }
//        updateUser(currentLevel,currentExp);
//    }

}
