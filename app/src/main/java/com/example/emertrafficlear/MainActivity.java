package com.example.emertrafficlear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button startSharing;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startSharing = (Button) findViewById(R.id.start_button);
        startSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent locationIntent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(locationIntent);
            }
        });
    }
}

//import android.Manifest;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class MainActivity extends AppCompatActivity implements LocationListener {
//    private static final String SERVER_ADDRESS = "IP";
//    private static final int SERVER_PORT = 5000;
//
//    private LocationManager locationManager;
//
//    private Handler handler = new Handler();
//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, MainActivity.this);
//            handler.postDelayed(this, 6000);
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//        } else {
//            handler.postDelayed(runnable, 6000);
//        }
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        Log.d("MainActivity", "onLocationChanged: " + location.toString());
//        LocationSender locationSender = new LocationSender(SERVER_ADDRESS, SERVER_PORT, MainActivity.this);
//        locationSender.execute(location);
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//
//    private static class LocationSender extends AsyncTask<Location, Void, Void> {
//        private final String serverAddress;
//        private final int serverPort;
//        private final Context context;
//
//        public LocationSender(String serverAddress, int serverPort, Context context) {
//            this.serverAddress = serverAddress;
//            this.serverPort = serverPort;
//            this.context = context;
//        }
//
//        @Override
//        protected Void doInBackground(Location... locations) {
//            Log.d("LocationSender", "doInBackground: " + locations[0].toString());
//            try {
//                Socket socket = new Socket(serverAddress, serverPort);
//                OutputStream outputStream = socket.getOutputStream();
//                PrintWriter printWriter = new PrintWriter(outputStream);
//
//                Location location = locations[0];
//
//                if (location != null) {
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                    String message = latitude + "," + longitude;
//                    printWriter.write(message);
//                    printWriter.flush();
//                }
//
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//    }
//}
