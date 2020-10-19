package learn.mode.appventa.activity.Views;

public interface ProductoView {
    void showProgress();
    void hideProgress();
    void onRequestSucces(String message);
    void onRequestError(String message);


}
