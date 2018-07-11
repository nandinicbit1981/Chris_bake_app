package christopher.bakingapp.retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class RecipeModel implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String recipeName;
    @SerializedName("ingredients")
    private ArrayList<IngredientModel> ingredients = null;
    @SerializedName("steps")
    private ArrayList<StepModel> steps;
    @SerializedName("servings")
private Integer servings;
    @SerializedName("image")
private String image;


    public RecipeModel(Integer id, String recipeName, ArrayList<IngredientModel> ingredients, ArrayList<StepModel> steps, Integer servings, String image) {
        this.id = id;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public ArrayList<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.recipeName);
        dest.writeList(this.ingredients);
        dest.writeList(this.steps);
        dest.writeString(this.image);
        dest.writeValue(this.servings);
    }

    protected RecipeModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.recipeName = in.readString();
        this.ingredients = new ArrayList<IngredientModel>();
        in.readList(this.ingredients, IngredientModel.class.getClassLoader());
        this.steps = new ArrayList<StepModel>();
        in.readList(this.steps, StepModel.class.getClassLoader());
        this.image = in.readString();
        this.servings = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<RecipeModel> CREATOR = new Parcelable.Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel source) {
            return new RecipeModel(source);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };

    public RecipeModel(){}
}
