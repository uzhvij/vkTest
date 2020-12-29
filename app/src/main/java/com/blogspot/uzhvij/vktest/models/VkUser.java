package com.blogspot.uzhvij.vktest.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.json.JSONObject;

public class VkUser implements Parcelable {
    public int id = 0;
    public String firstName = "";
    public String lastName = "";
    public String photo = "";
    public Boolean deactivated = false;

    protected VkUser(int id, String firstName, String lastName, String photo, Boolean deactivated){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
        this.deactivated = deactivated;
    }

    protected VkUser(Parcel parcel) {
        parcel.readInt();
        parcel.readString();
        parcel.readString();
        parcel.readString();
        parcel.readByte();
    }

    protected VkUser(JSONObject json) {
        id = json.optInt("id", 0);
        firstName = json.optString("first_name", "");
        lastName = json.optString("last_name", "");
        photo = json.optString("photo_200", "");
        deactivated = json.optBoolean("deactivated", false);
    }

    static public VkUser parse(JSONObject json) {
        return new VkUser(json);
    }

    public static final Creator<VkUser> CREATOR = new Creator<VkUser>() {
        @Override
        public VkUser createFromParcel(Parcel in) {
            return new VkUser(in);
        }

        @Override
        public VkUser[] newArray(int size) {
            return new VkUser[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(photo);
        parcel.writeByte((byte) (deactivated ? 1 : 0));
    }

    @NonNull
    @Override
    public String toString() {
        return "" + id +
                " " + firstName +
                " " + lastName;
    }
}
