package learn.mode.appventa.activity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import learn.mode.appventa.R;
import learn.mode.appventa.activity.Views.ProductoView;
import learn.mode.appventa.api.ApiProduct;
import learn.mode.appventa.apiInterface.ApiClientInterface;
import learn.mode.appventa.apiInterface.ApiProductoInterface;
import learn.mode.appventa.model.Categoria;
import learn.mode.appventa.model.Producto;

public class ProductoActivity extends AppCompatActivity implements ProductoView {

    EditText name_pro, precio_pro,cantidad_pro;

    ProgressDialog progressDialog;
    ApiClientInterface apiClientInterface;
    ProductoPresenter productoPresenter;
    String nombres, precios, cantidades;
    Spinner spinner;

    int color, id, idc;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        name_pro = findViewById(R.id.r_producto);
        precio_pro = findViewById(R.id.r_precio);
        cantidad_pro = findViewById(R.id.r_cantidad);

        progresso();
        productoPresenter = new ProductoPresenter(this);

        Intent int3 = getIntent();
        id = int3.getIntExtra("id",0);
        nombres = int3.getStringExtra("nombre");
        precios = String.valueOf(int3.getIntExtra("precio",0));
        cantidades = String.valueOf(int3.getIntExtra("cantidad",0));

        setDataFromIntentExtra();
    }


    private void progresso() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menus) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuproducto,menus);
        menu = menus;

        if (id != 0 ){
            menu.findItem(R.id.p_edit).setVisible(true);
            menu.findItem(R.id.p_delete).setVisible(true);
            menu.findItem(R.id.p_save).setVisible(false);
            menu.findItem(R.id.p_update).setVisible(false);
        }else {
            menu.findItem(R.id.p_edit).setVisible(false);
            menu.findItem(R.id.p_delete).setVisible(false);
            menu.findItem(R.id.p_save).setVisible(true);
            menu.findItem(R.id.p_update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem items) {

        String nombres = name_pro.getText().toString().trim();
        String precios = precio_pro.getText().toString().trim();
        String cantidades = cantidad_pro.getText().toString().trim();
        int color= -123454;
        int idc = 2;
        switch (items.getItemId()){
            case R.id.p_save:

                if (nombres.isEmpty()){
                    name_pro.setError("Please llene el nombre");
                }else if (precios.isEmpty()){
                    precio_pro.setError("Please llene el precio");
                }else if (cantidades.isEmpty()){
                    cantidad_pro.setError("Please llene la cantidad");
                }else{
                    Producto pr_o = new Producto();
                    pr_o.setIdcategoria(idc);
                    pr_o.setNom_producto(nombres);
                    pr_o.setCantidad(Integer.parseInt(cantidades));
                    pr_o.setPrecio(Integer.parseInt(precios));
                    System.out.println(pr_o);
                    productoPresenter.guarda_productos(pr_o);
                }
                return true;
            case R.id.p_edit:
                editMode();
                menu.findItem(R.id.p_edit).setVisible(false);
                menu.findItem(R.id.p_delete).setVisible(false);
                menu.findItem(R.id.p_save).setVisible(false);
                menu.findItem(R.id.p_update).setVisible(true);

                return true;

            case R.id.p_update:
                if (nombres.isEmpty()){
                    name_pro.setError("Please llene el nombre");
                }else if (precios.isEmpty()){
                    precio_pro.setError("Please llene el precio");
                }else if (cantidades.isEmpty()){
                    cantidad_pro.setError("Please llene la cantidad");
                }else{
                    Producto pro = new Producto();
                    pro.setIdcategoria(idc);
                    pro.setNom_producto(nombres);
                    pro.setCantidad(Integer.parseInt(cantidades));
                    pro.setPrecio(Integer.parseInt(precios));
                    System.out.println(pro);
                    productoPresenter.modificar_producto(id, pro);
                }
            case R.id.p_delete:

                AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirm");
                alertDialog.setMessage("Are you sure ?");
                alertDialog.setNegativeButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    productoPresenter.eliminar_producto(id);
                });
                alertDialog.setPositiveButton("Cancel",(dialog, which) -> {
                    dialog.dismiss();
                });
                alertDialog.show();
                return  true;
            default:
                return super.onOptionsItemSelected(items);
        }

    }
/*
    public void cargar_categoria(){
        ApiProductoInterface data = ApiProduct.getConnection().create(ApiProductoInterface.class);
        ArrayList<Categoria> ar_ca = new ArrayList<Categoria>();
        ar_ca.add(new Categoria(1, "Menestras"));
        ar_ca.add(new Categoria(2, "frutas"));
        ar_ca.add(new Categoria(3, "Cerealea"));
        ar_ca.add(new Categoria( 4, "Verduras"));
        ArrayAdapter<Categoria> categoria_adapter = new ArrayAdapter<Categoria>
                (this,R.layout.support_simple_spinner_dropdown_item, ar_ca);
        spinner.setAdapter(categoria_adapter);

    }

 */
    private void setDataFromIntentExtra() {
        if (id != 0){
            name_pro.setText(nombres);
            precio_pro.setText(precios);
            cantidad_pro.setText(cantidades);
            getSupportActionBar().setTitle("Update Cliente");
            readMode();
        }else {
            editMode();
        }
    }
    private void editMode() {
        name_pro.setFocusableInTouchMode(true);
        precio_pro.setFocusableInTouchMode(true);
        cantidad_pro.setFocusableInTouchMode(true);
    }

    private void readMode() {
        name_pro.setFocusableInTouchMode(false);
        precio_pro.setFocusableInTouchMode(false);
        cantidad_pro.setFocusableInTouchMode(false);
        name_pro.setFocusable(false);
        precio_pro.setFocusable(false);
        cantidad_pro.setFocusable(false);
    }
    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSucces(String message) {
        Toast.makeText(ProductoActivity.this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(ProductoActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
