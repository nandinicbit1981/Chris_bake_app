package christopher.bakingapp.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<RecipeModel>> getRecipes();


}

