package com.sensibill.sensibillcodingchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bhaskar on 2016-03-04
 */
public class Receipt implements Parcelable {
    public Display display;
    public double receiptAmount;
    public String receiptName;
    public String receiptDate;
    public int id;

    public static class Display implements Parcelable {
        public String name;
        public String amount;
        public String date;

        protected Display(Parcel in) {
            name = in.readString();
            amount = in.readString();
            date = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(amount);
            dest.writeString(date);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<Display> CREATOR = new Parcelable.Creator<Display>() {
            @Override
            public Display createFromParcel(Parcel in) {
                return new Display(in);
            }

            @Override
            public Display[] newArray(int size) {
                return new Display[size];
            }
        };
    }

    protected Receipt(Parcel in) {
        display = (Display) in.readValue(Display.class.getClassLoader());
        receiptAmount = in.readDouble();
        receiptName = in.readString();
        receiptDate = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(display);
        dest.writeDouble(receiptAmount);
        dest.writeString(receiptName);
        dest.writeString(receiptDate);
        dest.writeInt(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Receipt> CREATOR = new Parcelable.Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };
}