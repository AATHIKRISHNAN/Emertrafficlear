package com.example.emertrafficlear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//public class LocationActivity extends AppCompatActivity implements LocationListener {
//    private static final String SERVER_ADDRESS = "192.168.189.201";
//    private static final int SERVER_PORT = 5000;
//
//    private LocationManager locationManager;
//    private Socket socket;
//
//    private Button stopSharing;
//
//    private Handler handler = new Handler();
//    private Runnable runnable = new Runnable() {
//        @SuppressLint("MissingPermission")
//        @Override
//        public void run() {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, LocationActivity.this);
//            handler.postDelayed(this, 6000);
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location);
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        boolean gps_enabled = false;
//        boolean network_enabled = false;
//
//        try {
//            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        } catch (Exception e) {}
//
//        try {
//            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        } catch (Exception e) {}
//
//        try {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//
//            } else {
//                if (!gps_enabled && !network_enabled) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//            }
//                handler.postDelayed(runnable, 6000);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        stopSharing = (Button) findViewById(R.id.stop_button);
//        stopSharing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    OutputStream outputStream = socket.getOutputStream();
//                    PrintWriter printWriter = new PrintWriter(outputStream);
//                    printWriter.write(socket.getInetAddress() + "is disconnected");
//                    printWriter.flush();
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Intent mainIntent = new Intent(LocationActivity.this, MainActivity.class);
//                startActivity(mainIntent);
//            }
//        });
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        Log.d("MainActivity", "onLocationChanged: " + location.toString());
//        LocationSender locationSender = new LocationSender(socket, SERVER_ADDRESS, SERVER_PORT, this);
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
//        private Socket socket;
//        private final LocationActivity context;
//        private final String serverAddress;
//        private final int serverPort;
//
//        public LocationSender(Socket socket, String serverAddress, int serverPort, LocationActivity context) {
//            this.socket = socket;
//            this.serverAddress = serverAddress;
//            this.serverPort = serverPort;
//            this.context = context;
//        }
//
//        @Override
//        protected Void doInBackground(Location... locations) {
//            Log.d("LocationSender", "doInBackground: " + locations[0].toString());
//            try {
//                if (this.socket == null) {
//                    this.socket = new Socket(this.serverAddress, this.serverPort);
//                    this.context.socket = this.socket;
//                }
//                OutputStream outputStream = this.socket.getOutputStream();
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
//                    outputStream.flush();
//                }
//
//                InputStream inputStream = socket.getInputStream();
//                boolean canDisconnect = inputStream.toString() == "disconnect_request";
//                if (canDisconnect) {
//                    this.socket.close();
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            handler.postDelayed(runnable, 60000);
//        } else {
//            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//        }
//    }
//}

public class LocationActivity extends AppCompatActivity implements LocationListener {
    private static final String SERVER_ADDRESS = "192.168.217.201";
    private static final int SERVER_PORT = 5001;

    private LocationManager locationManager;
    private Button stopSharing;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, LocationActivity.this);
            handler.postDelayed(this, 6000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {}

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {}

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            if (!gps_enabled && !network_enabled) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
            handler.postDelayed(runnable, 6000);
        }

        stopSharing = (Button) findViewById(R.id.stop_button);
        stopSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("MainActivity", "onLocationChanged: " + location.toString());
        LocationSender locationSender = new LocationSender(SERVER_ADDRESS, SERVER_PORT, LocationActivity.this);
        locationSender.execute(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class LocationSender extends AsyncTask<Location, Void, Void> {
        private final String serverAddress;
        private final int serverPort;
        private final Context context;

        public LocationSender(String serverAddress, int serverPort, Context context) {
            this.serverAddress = serverAddress;
            this.serverPort = serverPort;
            this.context = context;
        }

        private RequestBody buildRequestBody(String msg) {
            return RequestBody.create(MediaType.parse("text/plain"), msg);
        }

        private void postRequest(String message, String URL) {
            RequestBody requestBody = buildRequestBody(message);
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request
                    .Builder()
                    .post(requestBody)
                    .url(URL)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LocationActivity.this, "Something went wromg:" + " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            call.cancel();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (response.body().string().equals("disconnet_request")) {
                                    Toast.makeText(LocationActivity.this, "You have reached your destination:", Toast.LENGTH_LONG).show();
                                    Intent mainIntent = new Intent(LocationActivity.this, MainActivity.class);
                                    startActivity(mainIntent);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }

        @Override
        protected Void doInBackground(Location... locations) {
            Log.d("LocationSender", "doInBackground: " + locations[0].toString());
            try {
                Socket socket = new Socket(serverAddress, serverPort);
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);

                Location location = locations[0];

                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    String message = latitude + "," + longitude;

                    this.postRequest(message, "http://"+this.serverAddress+":"+this.serverPort+"/location");
                    printWriter.write(message);
                    printWriter.flush();
                }

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            handler.postDelayed(runnable, 6000);
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}

