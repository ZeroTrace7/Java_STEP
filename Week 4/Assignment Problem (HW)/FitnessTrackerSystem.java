class Workout {
    private String activityName;
    private int durationInMinutes;
    private int caloriesBurned;
    private static final int DEFAULT_DURATION = 30;
    private static final int CALORIE_RATE = 5;
    
    public Workout() {
        this.activityName = "Walking";
        this.durationInMinutes = 30;
        this.caloriesBurned = 100;
    }
    
    public Workout(String activityName) {
        this.activityName = activityName;
        this.durationInMinutes = DEFAULT_DURATION;
        this.caloriesBurned = DEFAULT_DURATION * CALORIE_RATE;
    }
    
    public Workout(String activityName, int durationInMinutes) {
        this.activityName = activityName;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = durationInMinutes * CALORIE_RATE;
    }
    
    public Workout(String activityName, int durationInMinutes, int caloriesBurned) {
        this.activityName = activityName;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = caloriesBurned;
    }
    
    public void displayWorkout() {
        System.out.println("WORKOUT DETAILS");
        System.out.println("Activity Name    : " + activityName);
        System.out.println("Duration         : " + durationInMinutes + " minutes");
        System.out.println("Calories Burned  : " + caloriesBurned + " calories");
        System.out.println("Calorie Rate     : " + String.format("%.2f", (double)caloriesBurned / durationInMinutes) + " calories/minute");
        System.out.println();
    }
    
    public String getActivityName() {
        return activityName;
    }
    
    public int getDurationInMinutes() {
        return durationInMinutes;
    }
    
    public int getCaloriesBurned() {
        return caloriesBurned;
    }
    
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    
    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = durationInMinutes * CALORIE_RATE;
    }
    
    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}

public class FitnessTrackerSystem {
    public static void main(String[] args) {
        System.out.println("FITNESS TRACKER SYSTEM");
        System.out.println();
        
        System.out.println("1. Workout created with Default Constructor:");
        Workout workout1 = new Workout();
        workout1.displayWorkout();
        
        System.out.println("2. Workout created with Activity Name only:");
        Workout workout2 = new Workout("Running");
        workout2.displayWorkout();
        
        System.out.println("3. Workout created with Activity Name and Duration:");
        Workout workout3 = new Workout("Swimming", 45);
        workout3.displayWorkout();
        
        System.out.println("4. Workout created with Full Constructor:");
        Workout workout4 = new Workout("Cycling", 60, 400);
        workout4.displayWorkout();
        
        System.out.println("DIFFERENT WORKOUT ACTIVITIES:");
        System.out.println();
        
        Workout[] workouts = {
            new Workout("Jogging", 25),
            new Workout("Yoga", 40),
            new Workout("Weight Training", 50),
            new Workout("Dancing"),
            new Workout("Basketball", 90),
            new Workout("Tennis", 75, 350),
            new Workout()
        };
        
        for (int i = 0; i < workouts.length; i++) {
            System.out.println("Workout " + (i + 1) + ":");
            workouts[i].displayWorkout();
        }
        
        System.out.println("WORKOUT STATISTICS:");
        System.out.println();
        
        int totalDuration = 0;
        int totalCalories = 0;
        String longestActivity = "";
        int longestDuration = 0;
        String highestCalorieActivity = "";
        int highestCalories = 0;
        
        for (Workout workout : workouts) {
            totalDuration += workout.getDurationInMinutes();
            totalCalories += workout.getCaloriesBurned();
            
            if (workout.getDurationInMinutes() > longestDuration) {
                longestDuration = workout.getDurationInMinutes();
                longestActivity = workout.getActivityName();
            }
            
            if (workout.getCaloriesBurned() > highestCalories) {
                highestCalories = workout.getCaloriesBurned();
                highestCalorieActivity = workout.getActivityName();
            }
        }
        
        System.out.println("Total Workouts: " + workouts.length);
        System.out.println("Total Duration: " + totalDuration + " minutes");
        System.out.println("Total Calories Burned: " + totalCalories + " calories");
        System.out.println("Average Duration: " + String.format("%.2f", (double)totalDuration / workouts.length) + " minutes");
        System.out.println("Average Calories: " + String.format("%.2f", (double)totalCalories / workouts.length) + " calories");
        System.out.println("Longest Activity: " + longestActivity + " (" + longestDuration + " minutes)");
        System.out.println("Highest Calorie Burn: " + highestCalorieActivity + " (" + highestCalories + " calories)");
        
        System.out.println();
        System.out.println("WEEKLY WORKOUT PLAN:");
        System.out.println();
        
        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Workout[] weeklyPlan = {
            new Workout("Morning Walk", 30),
            new Workout("Strength Training", 45),
            new Workout("Swimming", 40),
            new Workout("Yoga", 35),
            new Workout("Cycling", 50),
            new Workout("Dancing", 60),
            new Workout("Rest Day", 0, 0)
        };
        
        for (int i = 0; i < weekDays.length; i++) {
            System.out.println(weekDays[i] + ":");
            weeklyPlan[i].displayWorkout();
        }
        
        System.out.println("CONSTRUCTOR OVERLOADING DEMONSTRATION:");
        System.out.println("Default Constructor: Workout()");
        System.out.println("Single Parameter: Workout(String activityName)");
        System.out.println("Two Parameters: Workout(String activityName, int durationInMinutes)");
        System.out.println("Three Parameters: Workout(String activityName, int durationInMinutes, int caloriesBurned)");
        System.out.println();
        System.out.println("All constructors successfully demonstrated!");
    }
}