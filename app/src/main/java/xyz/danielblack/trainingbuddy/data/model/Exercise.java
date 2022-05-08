package xyz.danielblack.trainingbuddy.data.model;

public class Exercise {
    private String mName;
    private String mCategory; // Barbell, Dumbbell, Machine/Other, Weighted bodyweight, Reps only, Cardio exercise, Duration
    private String mBodyPartTargeted; // None, Core, Arms, Back, Chest, Legs, Shoulders, Other, Olympic, Full Body, Cardio

    public Exercise(String mName, String mCategory, String mBodyPartTargeted) {
        this.mName = mName;
        this.mCategory = mCategory;
        this.mBodyPartTargeted = mBodyPartTargeted;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getBodyPartTargeted() {
        return mBodyPartTargeted;
    }

    public void setBodyPartTargeted(String mBodyPartTargeted) {
        this.mBodyPartTargeted = mBodyPartTargeted;
    }
}
