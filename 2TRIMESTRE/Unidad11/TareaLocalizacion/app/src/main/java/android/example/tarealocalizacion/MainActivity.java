package android.example.tarealocalizacion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private RequestQueue requestQueue;
    private EditText searchBar;
    private Button searchButton, mapTypeButton;
    private boolean isSatellite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.search_bar);
        searchButton = findViewById(R.id.search_button);
        mapTypeButton = findViewById(R.id.map_type_button);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestQueue = Volley.newRequestQueue(this);

        // Cargar el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Listener para el botón de búsqueda
        searchButton.setOnClickListener(v -> searchPlaces());

        // Listener para el botón de cambio de tipo de mapa
        mapTypeButton.setOnClickListener(v -> toggleMapType());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        mMap.setMyLocationEnabled(true);
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Mi ubicación"));
            }
        });
    }

    private void searchPlaces() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        String placeType = searchBar.getText().toString().trim().toLowerCase();
        if (placeType.isEmpty()) {
            Log.e("ERROR", "El tipo de lugar está vacío");
            return;
        }

        String apiKey = "YOUR_API_KEY";  // Sustituye por tu clave de API

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                        + "location=" + latitude + "," + longitude
                        + "&radius=1500&type=" + placeType
                        + "&key=" + apiKey;

                Log.d("API_REQUEST", "Solicitando: " + url);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        response -> {
                            Log.d("API_RESPONSE", response.toString());
                            try {
                                JSONArray results = response.getJSONArray("results");
                                mMap.clear();

                                if (results.length() == 0) {
                                    Log.d("API_RESPONSE", "No se encontraron lugares.");
                                }

                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject place = results.getJSONObject(i);
                                    JSONObject locationObj = place.getJSONObject("geometry").getJSONObject("location");
                                    double lat = locationObj.getDouble("lat");
                                    double lng = locationObj.getDouble("lng");
                                    String name = place.getString("name");

                                    LatLng placeLatLng = new LatLng(lat, lng);
                                    mMap.addMarker(new MarkerOptions().position(placeLatLng).title(name));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, error -> Log.e("API_ERROR", "Error en la API: " + error.getMessage()));

                requestQueue.add(request);
            }
        });
    }

    private void toggleMapType() {
        if (mMap != null) {
            if (isSatellite) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);  // Cambia a tipo normal
            } else {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);  // Cambia a tipo satélite
            }
            isSatellite = !isSatellite;  // Cambia el estado
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onMapReady(mMap);
        }
    }
}
