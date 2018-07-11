package christopher.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.StepModel;
import christopher.bakingapp.ui.fragments.PlayerFragment;

public class PlayerActivity extends AppCompatActivity {

    Bundle stepDetailsBundle;
    private ArrayList<StepModel> stepDetailList;

    String stepDescription;

    String stepVid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_main);

        stepDetailsBundle = getIntent().getBundleExtra("stepDetailBundle");

        stepDescription = stepDetailsBundle.getString("stepsDetails");

        stepVid = stepDetailsBundle.getString("stepsVideo");


        if (savedInstanceState == null) {

            PlayerFragment playerFragment = new PlayerFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.player_container, playerFragment)
                    .commit();


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
