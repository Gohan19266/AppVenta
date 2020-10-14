package learn.mode.appventa.activity.Views;

import java.util.List;

import learn.mode.appventa.model.Cliente;
import learn.mode.appventa.model.Producto;

public interface MainViewProducto {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Producto> productos);
    void onErrorLoading(String message);
}
