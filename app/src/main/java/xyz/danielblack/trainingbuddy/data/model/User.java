package xyz.danielblack.trainingbuddy.data.model;

public class User {
    private String mEmail;
    private int mTotalNumberOfWorkouts;

    public User(String mEmail, int mTotalNumberOfWorkouts) {
        this.mEmail = mEmail;
        this.mTotalNumberOfWorkouts = mTotalNumberOfWorkouts;
    }

    public int getTotalNumberOfWorkouts() {
        return mTotalNumberOfWorkouts;
    }

    public void setTotalNumberOfWorkouts(int mTotalNumberOfWorkouts) {
        this.mTotalNumberOfWorkouts = mTotalNumberOfWorkouts;
    }
}
