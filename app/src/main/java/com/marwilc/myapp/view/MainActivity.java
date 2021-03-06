package com.marwilc.myapp.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.firebase.iid.FirebaseInstanceId;
import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.PageAdapter;
import com.marwilc.myapp.db.adapter.InstagramUserAdapterDB;
import com.marwilc.myapp.restAPI.IEndPointsAPI;
import com.marwilc.myapp.restAPI.adapter.RestApiAdapter;
import com.marwilc.myapp.restAPI.model.PetResponse;
import com.marwilc.myapp.restAPI.model.ResponseUser;
import com.marwilc.myapp.view.fragments.ProfileFragment;
import com.marwilc.myapp.view.fragments.RecyclerViewFragment;
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static final String PAGE_PROFILE = "page_profile";
    public static final String PAGE_DOS = "dos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // activa las transiciones
        }
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        String currentPage = "uno";
        Bundle extras = intent.getExtras();
        if(extras != null)
            currentPage = extras.getString(PAGE_PROFILE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.myActionBar);
        tabLayout   = (TabLayout) findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);

        setupViewPager();
        //Log.d("item Tab", currentPage);
//        showPage(currentPage);

        if (toolbar != null)
            setSupportActionBar(toolbar);

     }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    // crear menu e inflar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    // menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.mContact:
                // to activity contact
                Intent intent1 = new Intent(this, ContactActivity.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    // implementacion de transiciones explode
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    this.getWindow().setExitTransition(explode);
                    this.startActivity(intent1,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewPager,"").toBundle());

                } else
                    startActivity(intent1);
                break;

            case R.id.mAbout:
                // to activity about
                Intent intent2 = new Intent(this, AboutActivity.class);
                // genera compatibilidad de aplicaciones en otras versiones de android

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    // implementacion de transiciones explode
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    this.getWindow().setExitTransition(explode);
                    this.startActivity(intent2,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewPager,"").toBundle());

                } else
                    startActivity(intent2);


                break;

            case R.id.mFavorites:
                // to activity favorites
                toFavoritesActivity();
                break;

            case R.id.mAccount:
                // to activity account settings

                Intent intent3 = new Intent(this, AccountActivity.class);

                // genera compatibilidad de aplicaciones en otras versiones de android
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    // implementacion de transiciones explode
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    this.getWindow().setExitTransition(explode);
                    this.startActivity(intent3,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewPager,"").toBundle());

                } else
                    startActivity(intent3);

                break;

            case R.id.mNotifications:
                registerUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void registerUser() {
        InstagramUserAdapterDB userAdapterDB = new InstagramUserAdapterDB(getBaseContext());
        ArrayList<ResponseUser> userArrayList = userAdapterDB.getData();

        if(!userArrayList.isEmpty()){
            sendToken(userArrayList.get(0).getIdUser());

            Snackbar.make(viewPager,"Send succes", Snackbar.LENGTH_LONG).show();
        }else {
            Snackbar.make(viewPager,"Send  faile", Snackbar.LENGTH_LONG).show();
        }

    }
    // recibe un id de usuario y configura el id del dispositivo a registrarse para enviarlo a firebase
    public void sendToken  (String idUser) {
        String idDevice = FirebaseInstanceId.getInstance().getToken();
        sendTokenRegister(idDevice, idUser);
    }

    private void sendTokenRegister(String idDevice, String idUser){
        Log.d("ID_DEVICE", idDevice);
        Log.d("ID_USER", idUser);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        IEndPointsAPI iEndPoints = restApiAdapter.setConnectionRestAPIServer();
        Call<ResponseUser> responseUserCall = iEndPoints.registerUser(idDevice, idUser);

        responseUserCall.enqueue(new Callback<ResponseUser>() {

            @Override
            public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {

                ResponseUser responseUser = response.body();
               // Log.d("ID", responseUser.getId());
                //Log.d("ID_DEVICE", responseUser.getIdDevice());
                //Log.d("ID_USER", responseUser.getIdUser());
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {

            }
        });
    }

    private ArrayList<Fragment> aggFragments(){
        ArrayList<Fragment> fragmets = new ArrayList<>();

        RecyclerViewFragment rvfPets = new RecyclerViewFragment();
        ProfileFragment pfProfilePet = new ProfileFragment();
        fragmets.add(rvfPets);
        fragmets.add(pfProfilePet);

        return fragmets;
    }

    private void setupViewPager() {

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),aggFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_pets_1);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_pets_2);
    }

    public void toFavoritesActivity() {

        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }


    public void showPage(String page) {
        if (page.equals(PAGE_DOS))
            viewPager.setCurrentItem(1);
    }
}


