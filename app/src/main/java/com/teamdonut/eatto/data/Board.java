package com.teamdonut.eatto.data;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

public class Board implements ClusterItem {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("appointed_time")
    @Expose
    private String appointedTime;

    @SerializedName("max_person")
    @Expose
    private int maxPerson;

    @SerializedName("current_person")
    @Expose
    private int currentPerson;

    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;

    @SerializedName("writer_id")
    @Expose
    private long writerId;

    @SerializedName("write_date")
    @Expose
    private String writeDate;

    @SerializedName("validation")
    @Expose
    private int validation;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("expire_date")
    @Expose
    private String expireDate;

    @SerializedName("longitude")
    @Expose
    private double longitude;

    @SerializedName("latitude")
    @Expose
    private double latitude;

    @SerializedName("budget")
    @Expose
    private String budget;

    @SerializedName("min_age")
    @Expose
    private int minAge;

    @SerializedName("max_age")
    @Expose
    private int maxAge;

    @SerializedName("photo")
    @Expose
    private String profileImage;

    public Board(String title, String address, String appointed_time, String restaurant_name, int max_person, int min_age, int max_age, double longitude, double latitude, long writer_id) {
        this.title = title;
        this.address = address;
        this.appointedTime = appointed_time;
        this.restaurantName = restaurant_name;
        this.maxPerson = max_person;
        this.minAge = min_age;
        this.maxAge = max_age;
        this.longitude = longitude;
        this.latitude = latitude;
        this.writerId = writer_id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(getLatitude(), getLongitude());
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return address;
    }

    public String getAppointedTime() {
        return appointedTime;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public int getCurrentPerson() {
        return currentPerson;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public long getWriterId() {
        return writerId;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public int getValidation() {
        return validation;
    }

    public String getContent() {
        return content;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getBudget() {
        return budget;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAppointedTime(String appointedTime) {
        this.appointedTime = appointedTime;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public void setCurrentPerson(int currentPerson) {
        this.currentPerson = currentPerson;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
