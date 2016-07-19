package com.axeleroy.adressbook.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Entry implements Parcelable {
    private String lastname = "";
    private String surname = "";

    private String number = "";

    public Entry() {
    }

    public Entry(String lastname, String surname, String number) {
        this.lastname = lastname;
        this.surname = surname;
        this.number = number;
    }

    private Entry(Parcel in) {
        lastname = in.readString();
        surname = in.readString();
        number = in.readString();
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastname);
        dest.writeString(surname);
        dest.writeString(number);
    }

    @Override
    public String toString() {
        return surname + "." + lastname + "=" + number + "\n";
    }
}
