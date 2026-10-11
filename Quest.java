
public class Quest {
    private String title;
    private String targetEnemy;
    private int targetCount;
    private int progress;
    private boolean completed;
    private Reward reward;

    public Quest(String title, String targetEnemy, int targetCount, Reward reward) {
        this.title = title;
        this.targetEnemy = targetEnemy;
        this.targetCount = targetCount;
        this.reward = reward;
        this.progress = 0;
        this.completed = false;
    }

    // Returns true only at the moment the quest becomes complete
    public boolean recordKill(String enemyName) {
        if (!completed && enemyName.equals(targetEnemy)) {
            progress++;
            if (progress >= targetCount) {
                completed = true;
                return true;
            }
        }
        return false;
    }

    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public Reward getReward() { return reward; }

    @Override
    public String toString() {
        String status = completed ? "COMPLETED" : progress + "/" + targetCount;
        return title + " - defeat " + targetCount + " " + targetEnemy + " [" + status + "] Reward: " + reward;
    }
}
