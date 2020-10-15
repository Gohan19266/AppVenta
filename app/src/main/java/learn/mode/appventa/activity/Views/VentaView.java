package learn.mode.appventa.activity.Views;

public interface VentaView {
    void showProgress();
    void hideProgress();
    void onRequestSucces(String message);
    void onRequestError(String message);
}
