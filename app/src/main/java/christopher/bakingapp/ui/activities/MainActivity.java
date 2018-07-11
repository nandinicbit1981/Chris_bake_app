package christopher.bakingapp.ui.activities;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.adapter.RecipeAdapter;
import christopher.bakingapp.retrofit.Client;
import christopher.bakingapp.retrofit.IngredientModel;
import christopher.bakingapp.retrofit.RecipeModel;
import christopher.bakingapp.retrofit.Service;
import christopher.bakingapp.retrofit.StepModel;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecipeAdapter mAdapter;

    private ArrayList<RecipeModel> recipeList = new ArrayList<>();

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    private int recyclerViewState;

    Boolean isTablet;

    private ArrayList<StepModel> stepList = new ArrayList<>();
    private ArrayList<IngredientModel> ingredientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViews();

        networkRequestRecipes();


    }

    private void loadViews() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_recipes);

        mAdapter = new RecipeAdapter(getApplicationContext(), recipeList);
        if (findViewById(R.id.tablet_layout_main) != null) {
            isTablet = true;

            if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                layoutManager = new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
            } else {
                layoutManager = new GridLayoutManager(MainActivity.this, 4);
                recyclerView.setLayoutManager(layoutManager);
            }

        } else {

            isTablet = false;
            if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                layoutManager = new GridLayoutManager(MainActivity.this, 1);
                recyclerView.setLayoutManager(layoutManager);
            } else {
                layoutManager = new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
            }
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void networkRequestRecipes() {
        Client client = new Client();

        Service service = client.getClient().create(Service.class);

        Call<ArrayList<RecipeModel>> call = service.getRecipes();

        call.enqueue(new Callback<ArrayList<RecipeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RecipeModel>> call, retrofit2.Response<ArrayList<RecipeModel>> response) {
                recipeList = response.body();


                recyclerView.setAdapter(new RecipeAdapter(getApplicationContext(), recipeList));
                recyclerView.scrollToPosition(recyclerViewState);

            }

            @Override
            public void onFailure(Call<ArrayList<RecipeModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "It didn't work! :(", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        recyclerViewState = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        outState.putInt("position", recyclerViewState);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        recyclerViewState = savedInstanceState.getInt("position");


    }
}
