package christopher.bakingapp.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class IngredientModel implements Parcelable {

    @SerializedName("quantity")
    private double amount;
    @SerializedName("measure")
    private String measure;
    @SerializedName("ingredient")
    private String ingredients;

    public IngredientModel(double amount, String measure, String ingredients) {
        this.amount = amount;
        this.measure = measure;
        this.ingredients = ingredients;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.amount);
        dest.writeString(this.measure);
        dest.writeString(this.ingredients);
    }

    protected IngredientModel(Parcel in) {
        this.amount = in.readDouble();
        this.measure = in.readString();
        this.ingredients = in.readString();
    }

    public static final Parcelable.Creator<IngredientModel> CREATOR = new Parcelable.Creator<IngredientModel>() {
        @Override
        public IngredientModel createFromParcel(Parcel source) {
            return new IngredientModel(source);
        }

        @Override
        public IngredientModel[] newArray(int size) {
            return new IngredientModel[size];
        }
    };

    public IngredientModel(){}
}
