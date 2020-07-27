package entity;

public class Reward {

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

    //region Cost XP
    public int getCostXp() { return costXp; }

    public void setCostXp(int costXp) { this.costXp = costXp; }
    //endregion

    //region Number available
    public int getNbAvailable() { return nbAvailable; }

    public void setNbAvailable(int nbAvailable) { this.nbAvailable = nbAvailable; }
    //endregion

    //region Description
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    //region ID level
    public int getIdLevel() { return idLevel; }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }
    //endregion

    //region level
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
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
    int costXp;
    int nbAvailable;
    String description;
    int idLevel;
    String level;
    String skin;

    public Reward(){}

    public Reward (String label, int costXp, int nbAvailable, String description, int idLevel, String level, String skin) {
        this.label = label;
        this.costXp = costXp;
        this.nbAvailable = nbAvailable;
        this.description = description;
        this.idLevel = idLevel;
        this.level = level;
        this.skin = skin;
    }

    public Reward (int id, String label, int costXp, int nbAvailable, String description, int idLevel, String level, String skin) {
        this.id = id;
        this.label = label;
        this.costXp = costXp;
        this.nbAvailable = nbAvailable;
        this.description = description;
        this.idLevel = idLevel;
        this.level = level;
        this.skin = skin;
    }
}
