package de.myfdweb.smartwatchlock;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.Wearable;

import de.myfdweb.smartwatchlock.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(ActivityMainBinding.inflate(getLayoutInflater()).getRoot());

        Wearable.getCapabilityClient(this).getCapability("smartwatchlock", CapabilityClient.FILTER_REACHABLE).addOnSuccessListener(ci -> {
            // Send message to mobile and exit app
            ci.getNodes().forEach(n -> Wearable.getMessageClient(this).sendMessage(n.getId(), "/button_press", new byte[0]));
            System.exit(0);
        }).addOnFailureListener(Throwable::printStackTrace);
    }
}