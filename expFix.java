private void addExp()
    {
        //Generate random xp from 5-25
        int randomXP = random.nextInt(25-5) + 5;
        int xpLoop = 0; //just a counter for the while loop
        while(xpLoop < randomXP)// loops through giving xp
        {
            isLevelUp();
            mProgressBar.incrementProgressBy(1);
            currentExp +=1;
            mExperienceCounter.setText(currentExp + " / " + xpToLevel );
            xpLoop++;
        }
        updateUser(currentLevel,currentExp);
    }

//NEW STUFF. Pracitcally what to do lol
    private void addExp()
    {
        int taskDifficulty = currentTask.getDifficulty();
        int xpLoop = 0;
        switch (taskDifficulty)
        {
            case 1:
                    while (xpLoop < 10) //Possibly make into constant
                    {
                        isLevelUp();
                        mProgressBar.incrementProgressBy(1);
                        currentExp +=1;
                        mExperienceCounter.setText(currentExp + " / " + xpToLevel );
                        xpLoop++;
                    }
                    break;
            case 2:
                    while (xpLoop < 20) //Possibly make into constant
                    {
                        isLevelUp();
                        mProgressBar.incrementProgressBy(1);
                        currentExp +=1;
                        mExperienceCounter.setText(currentExp + " / " + xpToLevel );
                        xpLoop++;
                    } 
            case 3: 
                    while (xpLoop < 30) //Possibly make into constant
                    {
                        isLevelUp();
                        mProgressBar.incrementProgressBy(1);
                        currentExp +=1;
                        mExperienceCounter.setText(currentExp + " / " + xpToLevel );
                        xpLoop++;
                    }
            default: break;
        }
        updateUser(currentLevel,currentExp);
    }