package christopher.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.IngredientModel;
import christopher.bakingapp.retrofit.StepModel;
import christopher.bakingapp.ui.fragments.MasterStepFragment;
import christopher.bakingapp.ui.fragments.PlayerFragment;
import christopher.bakingapp.widget.BakingWidgetProvider;

public class StepActivity extends AppCompatActivity{

    String recipeName;
    public static final String ACTION_VIEW_DETAILS =
            "com.company.android.ACTION_VIEW_DETAILS";


    Bundle recipeDetailsBundle;


    private ArrayList<StepModel> stepList;
    private ArrayList<IngredientModel> ingredientList;

    private boolean mTwoPane;


    ScrollView scrollViewSteps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_master_main);

        scrollViewSteps = (ScrollView) findViewById(R.id.scrollViewSteps);


        recipeDetailsBundle = getIntent().getBundleExtra("recipeBundle");

        recipeName = recipeDetailsBundle.getString("recipeNames");

        ingredientList = recipeDetailsBundle.getParcelableArrayList("ingredients");
        stepList = recipeDetailsBundle.getParcelableArrayList("steps");

        Gson gson = new Gson();
        String ingredientsJson = gson.toJson(ingredientList);

        Intent broadCastIntent = new Intent(getApplicationContext(), BakingWidgetProvider.class);
        broadCastIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        broadCastIntent.putExtra("ingredients", ingredientList);
        broadCastIntent.putExtra("recipeNames",  recipeName);
        getApplicationContext().sendBroadcast(broadCastIntent);


        getSupportFragmentManager().beginTransaction().replace(R.id.master_list_fragment, new MasterStepFragment()).commit();
        getSupportActionBar().setTitle(recipeName);


        if (findViewById(R.id.two_pane) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {


                FragmentManager fragmentManager = getSupportFragmentManager();

                PlayerFragment playerFragment = new PlayerFragment();
                Bundle bundle = new Bundle();
                bundle.putString("stepsDetails", stepList.get(0).getDescription());
                bundle.putString("stepsVideo", stepList.get(0).getVidUrl());
                playerFragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .add(R.id.player_container, playerFragment)
                        .commit();
            } else {
                mTwoPane = false;


            }

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public List<StepModel> getStepList() {
        return stepList;
    }

    public List<IngredientModel> getIngredientsList() {
        return ingredientList;
    }

//
//
//
//    @Override
//    public void onItemClick(int position) {
//        Toast.makeText(this, "You clicked on it", Toast.LENGTH_SHORT).show();
//        recipeDetailsBundle = getIntent().getBundleExtra("recipeBundle");
//        recipeName = recipeDetailsBundle.getString("recipeNames");
//
//        ingredientList = recipeDetailsBundle.getParcelableArrayList("ingredients");
//        stepList = recipeDetailsBundle.getParcelableArrayList("steps");
//    }


}