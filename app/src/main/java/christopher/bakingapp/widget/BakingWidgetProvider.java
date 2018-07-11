package christopher.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.google.gson.Gson;

import java.util.List;

import christopher.bakingapp.R;
import christopher.bakingapp.retrofit.IngredientModel;

/**
 * Implementation of App Widget functionality.
 */


//This class binds all the widget components together
//The AppWidgetProvider is a special type of BroadcastReceiver that is specifically used to
// send messages to a home screen widget. The AppWidgetProvider class provides several different callback methods,
// one each for the five different broadcasts that a widget would typically receive.

public class BakingWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_VIEW_DETAILS =
            "com.company.android.ACTION_VIEW_DETAILS";
    public static final String EXTRA_ITEM =
            "com.company.android.CollectionWidgetProvider.EXTRA_ITEM";
    static List<IngredientModel> ingredientsList;
    static String recipe;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.app_name);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        Intent intent = new Intent(context, BakingWidgetService.class);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.putExtra("ingredients", (new Gson().toJson(ingredientsList)).toString());
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.appwidget_ingredients, intent);
        views.setTextViewText(R.id.appwidget_recipe, recipe);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }


    //called when widget is created and at every update interval

    //do our normal widget setup, but we also bind our service to the widget's view using a service
    // intent and the setRemoteAdapter() method of the RemoteViews class.
    // We also specify which of our widget views is going to be the empty view.
    // Then, we create and set our pending intent template

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int i = 0; i < appWidgetIds.length; i++) {

            int widgetId = appWidgetIds[i];

            updateAppWidget(context, appWidgetManager, widgetId);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    //we can now listen for that custom broadcast action we assigned in our pending intent template.
    // If we receive an intent of that type, we can do whatever we want with it.
    // If it's not the custom action, be sure to call super so that onUpdate gets called when necessary.
    // Don't forget to register this receiver in the manifest to listen for both the update and custom intent actions.
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            ingredientsList = (List<IngredientModel>) extras.get("ingredients");
            recipe = extras.getString("recipeName");

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), BakingWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

            onUpdate(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);

        } catch (Exception e) {

        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

