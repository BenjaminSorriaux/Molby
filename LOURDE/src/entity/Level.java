package entity;

public class Level {

    public Level() {

    }

    //region ID
    public int getId() {return id; }

    public void setId(int id) { this.id = id; }
    //endregion

    //region Label
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    //endregion

    //region Description
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    //region Cost XP
    public int getCostXp() {return costXp; }

    public void setCostXp(int costXp) { this.costXp = costXp; }
    //endregion

    //region Required XP
    public int getRequiredXp() {return requiredXp; }

    public void setRequiredXp(int requiredXp) { this.requiredXp = requiredXp; }
    //endregion

    //region Skin
    public String getSkin() {
        return this.skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
    //endregion

    int id;
    String label;
    String description;
    int costXp;
    int requiredXp;
    String skin;

    public Level(String label, String description, int costXp, int requiredXp, String skin)
    {
        this.label = label;
        this.description = description;
        this.costXp = costXp;
        this.requiredXp = requiredXp;
        this.skin = skin;
    }

    public Level(int id, String label, String description, int costXp, int requiredXp, String skin)
    {
        this.id = id;
        this.label = label;
        this.description = description;
        this.costXp = costXp;
        this.requiredXp = requiredXp;
        this.skin = skin;
    }
}
