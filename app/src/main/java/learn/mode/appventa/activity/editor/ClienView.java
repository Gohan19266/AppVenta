package learn.mode.appventa.activity.editor;

public interface ClienView {

    void showProgress();
    void hideProgress();
    void onAddSucces(String message);
    void onAddError(String message);

}
