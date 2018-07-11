package christopher.bakingapp.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StepModel implements Parcelable {

    @SerializedName("id")
    private Integer stepId;
    @SerializedName("shortDescription")
    private String shortDesc;
    @SerializedName("description")
    private String description;
    @SerializedName("videoURL")
    private String vidUrl;
    @SerializedName("thumbnailUrl")
    private String thumbUrl;

    public StepModel(Integer stepId, String shortDesc, String description, String vidUrl, String thumbUrl) {
        this.stepId = stepId;
        this.shortDesc = shortDesc;
        this.description = description;
        this.vidUrl = vidUrl;
        this.thumbUrl = thumbUrl;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl(String vidUrl) {
        this.vidUrl = vidUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.stepId);
        dest.writeString(this.shortDesc);
        dest.writeString(this.description);
        dest.writeString(this.vidUrl);
        dest.writeString(this.thumbUrl);
    }

    protected StepModel(Parcel in) {
        this.stepId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.shortDesc = in.readString();
        this.description = in.readString();
        this.vidUrl = in.readString();
        this.thumbUrl = in.readString();
    }

    public static final Parcelable.Creator<StepModel> CREATOR = new Parcelable.Creator<StepModel>() {
        @Override
        public StepModel createFromParcel(Parcel source) {
            return new StepModel(source);
        }

        @Override
        public StepModel[] newArray(int size) {
            return new StepModel[size];
        }
    };

    public StepModel(){}
}
