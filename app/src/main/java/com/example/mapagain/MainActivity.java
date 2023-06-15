package com.example.mapagain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;



import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener{


    boolean autorisation_accordee;

    GoogleMap googleM;
    private SearchView searchView;

    private ListView listView;

    //menu code

    private DrawerLayout drawerLayout;

    LinearLayout layoutticket;
    LinearLayout layoutmenu;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                // Do nothing since already in MainActivity
                break;
            case R.id.nav_historique:
                startActivity(new Intent(this, History.class));
                break;
            case R.id.nav_ajouParking:
                startActivity(new Intent(this, AjoutParking.class));
                break;
            case R.id.nav_infoParking:
                startActivity(new Intent(this, informationsParkings.class));
                break;
            case R.id.nav_help:
                startActivity(new Intent(this, helpOwner.class));
                break;
            case R.id.nav_info:
                startActivity(new Intent(this, info.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this, Profile.class));
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Se déconnecter", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finishAffinity();
        }
    }











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         layoutticket = findViewById(R.id.layoutticket);
         layoutmenu = findViewById(R.id.layoutmenu);
        //menu code


        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        layoutmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        searchView = findViewById(R.id.localisation);
        listView = findViewById(R.id.listView);
        checkPermission();

        if (verifyGooglePlayServices()) {
            SupportMapFragment supportMapFragment = new SupportMapFragment();
            replaceFragment(supportMapFragment);
            supportMapFragment.getMapAsync(MainActivity.this);
        } else {
            Toast.makeText(MainActivity.this, "Google Play services are not available", Toast.LENGTH_SHORT).show();
        }

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Geocoder geocoder= new Geocoder(MainActivity.this,Locale.getDefault());
                try {
                    List<Address>addressList=geocoder.getFromLocation(36.767987, 2.959846,1);
                    if(addressList.size()>0){
                        if (!MainActivity.this.isFinishing()) {
                            Toast.makeText(MainActivity.this, ""+addressList.get(0).getCountryName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }













    // khati lmenu
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contaner, fragment);
        fragmentTransaction.commit();
    }
    private boolean verifyGooglePlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Toast.makeText(MainActivity.this, "service annulé par l'utilisateur", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }
        return false;
    }

    private void checkPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                autorisation_accordee = true;
                Toast.makeText(MainActivity.this, "permission accordée", Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
                // nrslo l'utilisateur l setting
            }
            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleM= googleMap;
        LatLng latLng = new LatLng(35.70208274189568, -0.6350574660064296);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("votre position");
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        googleM.animateCamera(cameraUpdate);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.noneMap){
            googleM.setMapType(GoogleMap.MAP_TYPE_NONE);
        }
        if (item.getItemId()==R.id.normalMap){
            googleM.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        if (item.getItemId()==R.id.satelliteMap){
            googleM.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        if (item.getItemId()==R.id.mapHybrid){
            googleM.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        if (item.getItemId()==R.id.mapTerrain){
            googleM.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        return super.onOptionsItemSelected(item);
    }



}