package entity;

public class User {

    String skin;
    String name;
    String firstName;
    String email;
    int experience;
    int idActualLevel;
    String actualLevel;
    int idGroup;
    String groupName;
    int idPromo;
    String promoName;
    int id;

    //region Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //endregion

    //region Skin
    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
    //endregion

    //region Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion

    //region First name
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //endregion

    //region Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    //region Experience
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    //endregion

    //region Actual level id
    public int getIdActualLevel() {
        return idActualLevel;
    }

    public void setIdActualLevel(int idActualLevel) {
        this.idActualLevel = idActualLevel;
    }
    //endregion

    //region Actual level name
    public String getActualLevel() {
        return actualLevel;
    }

    public void setActualLevel(String actualLevel) {
        this.actualLevel = actualLevel;
    }
    //endregion

    //region Group id
    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
    //endregion

    //region Group name
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    //endregion

    //region Promo id
    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }
    //endregion

    //region Promo name
    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }
    //endregion

    public User(String skin, String name, String firstName, String email, int experience, int idActualLevel, String  actualLevel, int idGroup, String groupName, int idPromo, String promoName)
    {
        this.skin = skin;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.experience = experience;
        this.idActualLevel = idActualLevel;
        this.actualLevel = actualLevel;
        this.idGroup = idGroup;
        this.groupName = groupName;
        this.idPromo = idPromo;
        this.promoName = promoName;
    }

    public User(int id, String skin, String name, String firstName, String email, int experience, int idActualLevel, String  actualLevel, int idGroup, String groupName, int idPromo, String promoName)
    {
        this.id = id;
        this.skin = skin;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.experience = experience;
        this.idActualLevel = idActualLevel;
        this.actualLevel = actualLevel;
        this.idGroup = idGroup;
        this.groupName = groupName;
        this.idPromo = idPromo;
        this.promoName = promoName;
    }
}
