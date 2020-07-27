package entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Purchase {
    int rewardId;
    String rewardName;
    int rewardPrice;
    int rewardAvailable;
    int rewardRemaining;
    int userId;
    String userName;
    String userFirstName;
    int userPoints;
    Timestamp requestDate;
    Timestamp validationDate;
    Timestamp useDate;

    //region Reward id
    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }
    //endregion

    //region Reward name
    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }
    //endregion

    //region Reward price
    public int getRewardPrice() {
        return rewardPrice;
    }

    public void setRewardPrice(int rewardPrice) {
        this.rewardPrice = rewardPrice;
    }
    //endregion

    //region Reward available
    public int getRewardAvailable() {
        return rewardAvailable;
    }

    public void setRewardAvailable(int rewardAvailable) {
        this.rewardAvailable = rewardAvailable;
    }
    //endregion

    //region Rewards remaining
    public int getRewardRemaining() {
        return rewardRemaining;
    }

    public void setRewardRemaining(int rewardRemaining) {
        this.rewardRemaining = rewardRemaining;
    }
    //endregion

    //region User id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    //endregion

    //region User name
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    //endregion

    //region User firstname
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    //endregion

    //region User points
    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }
    //endregion

    //region Request date
    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }
    //endregion

    //region Validation date
    public Timestamp getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Timestamp validationDate) {
        this.validationDate = validationDate;
    }
    //endregion

    //region Use date
    public Timestamp getUseDate() {
        return useDate;
    }

    public void setUseDate(Timestamp useDate) {
        this.useDate = useDate;
    }
    //endregion

    public Purchase(int rewardId, String rewardName, int rewardPrice, int rewardAvailable, int userId, String userName, String userFirstName, int userPoints, Timestamp requestDate, Timestamp validationDate, Timestamp useDate)
    {
        this.rewardId = rewardId;
        this.rewardName = rewardName;
        this.rewardPrice = rewardPrice;
        this.rewardAvailable = rewardAvailable;
        this.userId = userId;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userPoints = userPoints;
        this.requestDate = requestDate;
        this.validationDate = validationDate;
        this.useDate = useDate;
    }

}
