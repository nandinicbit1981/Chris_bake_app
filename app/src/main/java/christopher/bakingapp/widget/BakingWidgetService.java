package christopher.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


//generates a remote views factory and connects it to a collection widget

public class BakingWidgetService extends RemoteViewsService {

    //only required method that must return a RemoteViewsFactory
    //be sure to register this service in the manifest
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetViewFactory(getApplicationContext(), intent);
    }
}
