package com.juaracoding.ujianher;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelBiodata implements Parcelable {

    private String nama;
    private String alamat;

    public ModelBiodata() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
    }

    protected ModelBiodata(Parcel in) {
        this.nama = in.readString();
        this.alamat = in.readString();
    }

    public static final Parcelable.Creator<ModelBiodata> CREATOR = new Parcelable.Creator<ModelBiodata>() {
        @Override
        public ModelBiodata createFromParcel(Parcel source) {
            return new ModelBiodata(source);
        }

        @Override
        public ModelBiodata[] newArray(int size) {
            return new ModelBiodata[size];
        }
    };
}
