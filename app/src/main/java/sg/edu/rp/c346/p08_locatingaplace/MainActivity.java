package sg.edu.rp.c346.p08_locatingaplace;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    private GoogleMap map;
    LatLng north, central, east,central2;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }
                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);
                east = new LatLng(1.341784, 103.945541);
                north = new LatLng(1.463946, 103.813854);
                central = new LatLng(1.333170, 103.832638);
                central2 = new LatLng(1.354485, 103.800260);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(central2,
                        10));
                Marker east1 = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                Marker north1 = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                Marker central1 = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(MainActivity.this, marker.getSnippet(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });


            }




        });

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer item = (Integer) spinner.getSelectedItemPosition();
                if (item == 1){
                    if (map != null) {
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(north,
                                15));
                    }
                }else if(item == 2){
                    if (map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,
                                15));
                    }
                }else if(item == 3){
                    if (map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(east,
                                15));

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(central2,
                        10));
            }
            });

    }
}
