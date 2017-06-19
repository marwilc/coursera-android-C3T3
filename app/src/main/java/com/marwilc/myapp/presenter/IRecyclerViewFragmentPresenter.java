package com.marwilc.myapp.presenter;

/**
 * Created by marwilc on 28/05/17.
 */

public interface IRecyclerViewFragmentPresenter {
    public void getPetsDB();
    public void getRecentMedia(); // obtiene toda la informacion de las imagenes recientes del usuario
    public void getRecentMediaById(String id); // obtiene toda la informacion de las imagenes recientes del usuario por el Id
    public void getDataUser(String userName); // obtiene la info de un usuario por su nombre
    public void getFollowsUser();   // obtiene los seguidores del usuario
    public void showPetsRV();
}
