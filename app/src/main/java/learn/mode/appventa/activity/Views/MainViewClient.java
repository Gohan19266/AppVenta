package learn.mode.appventa.activity.Views;

import java.util.List;

import learn.mode.appventa.model.Cliente;

public interface MainViewClient {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Cliente> clientes);
    void onErrorLoading(String message);
}
