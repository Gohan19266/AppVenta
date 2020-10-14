package learn.mode.appventa.activity.Views;

public interface ClienView {

    void showProgress();
    void hideProgress();
    void onRequestSucces(String message);
    void onRequestError(String message);

}
