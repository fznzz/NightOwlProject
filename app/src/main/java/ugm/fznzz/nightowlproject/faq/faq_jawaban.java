package ugm.fznzz.nightowlproject.faq;

import android.os.Parcel;
import android.os.Parcelable;

public class faq_jawaban implements Parcelable {
    public final String name;

    public faq_jawaban(String name) {
        this.name = name;
    }

    protected faq_jawaban(Parcel in) {
        name = in.readString();
    }

    public static final Creator<faq_jawaban> CREATOR = new Creator<faq_jawaban>() {
        @Override
        public faq_jawaban createFromParcel(Parcel in) {
            return new faq_jawaban(in);
        }

        @Override
        public faq_jawaban[] newArray(int size) {
            return new faq_jawaban[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
