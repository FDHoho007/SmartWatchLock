package de.myfdweb.smartwatchlock;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class MainActivity extends AppCompatActivity {

    // This empty class is required so android knows we want to be an device admin
    public static class DeviceAdminReceiver extends android.app.admin.DeviceAdminReceiver {

    }

    // We need a listener to receive events from the SmartWatch
    public static class ListenerService extends WearableListenerService {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onCreate() {
            super.onCreate();
            //Wearable.getMessageClient(this).addListener(this);
        }

        @Override
        public void onMessageReceived(@NonNull MessageEvent messageEvent) {
            // When we receive a message we request to lock the device immediately
            // This requires device admin capabilities
            if (messageEvent.getPath().equals("/button_press"))
                ((DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE)).lockNow();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We are required to set a content view
        setContentView(R.layout.activity_main);
    }
}