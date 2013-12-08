package org.kiwix.kiwixmobile;

import android.os.Parcel;
import android.os.Parcelable;

// This items class stores the Data for the ArrayAdapter.
// We Have to implement Parcelable, so we can store ArrayLists with this generic type in the Bundle
// of onSaveInstanceState() and retrieve it later on in onRestoreInstanceState()
public class DataModel implements Parcelable {

    // Interface that must be implemented and provided as a public CREATOR field.
    // It generates instances of our Parcelable class from a Parcel.
    public Creator<DataModel> CREATOR = new Creator<DataModel>() {

        @Override
        public DataModel createFromParcel(Parcel source) {
            return new DataModel(source);
        }

        @Override
        public boolean equals(Object o) {
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    private String mTitle;

    private String mPath;

    public DataModel(String title, String path) {
        mTitle = title;
        mPath = path;
    }

    // This constructor will be called when this class is generated by a Parcel.
    // We have to read the previously written Data in this Parcel.
    public DataModel(Parcel parcel) {
        String[] data = new String[2];
        parcel.readStringArray(data);
        mTitle = data[0];
        mTitle = data[1];
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPath() {
        return mPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write the data to the Parcel, so we can restore this Data later on.
        // It will be restored by the DataModel(Parcel parcel) constructor.
        dest.writeArray(new String[]{mTitle, mPath});
    }

    // Override equals(Object) so we can compare objects. Specifically, so List#contains() works.
    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof DataModel) {
            isEqual = (this.mPath.equals(((DataModel) object).mPath));
        }

        return isEqual;
    }
}
