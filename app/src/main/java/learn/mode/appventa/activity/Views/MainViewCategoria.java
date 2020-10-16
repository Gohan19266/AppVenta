package learn.mode.appventa.activity.Views;

import java.util.List;

import learn.mode.appventa.model.Categoria;
import learn.mode.appventa.model.Producto;

public interface MainViewCategoria {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Categoria> categorias);
    void onErrorLoading(String message);
}
