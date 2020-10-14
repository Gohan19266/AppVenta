package learn.mode.appventa.activity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import learn.mode.appventa.R;
import learn.mode.appventa.apiInterface.ApiClientInterface;

public class ClienteActivity extends AppCompatActivity implements ClienView{

    EditText text_name,text_surname,text_dni;
    ProgressDialog progressDialog;
    ApiClientInterface apiClientInterface;

    ClientPresenter presenter;

    int color, id;
    String nombre, apellidos,dni;

    Menu actionMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        text_name= findViewById(R.id.idname);
        text_surname = findViewById(R.id.apellidos);
        text_dni = findViewById(R.id.dni);
        //palette= findViewById(R.id.palette);

        progressD();

        presenter = new ClientPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        nombre = intent.getStringExtra("nombre");
        apellidos = intent.getStringExtra("apellidos");
        dni = intent.getStringExtra("dni");

        setDataFromIntentExtra();
    }




    private void progressD() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuclient,menu);
        actionMenu = menu;

        if (id != 0 ){
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        }else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nombre= text_name.getText().toString().trim();
        String apellidos = text_surname.getText().toString().trim();
        String dni = text_dni.getText().toString().trim();
        int color= -123454;

        switch (item.getItemId()){
            case R.id.save:

                if (nombre.isEmpty()){
                    text_name.setError("Please enter your name");
                }else if (apellidos.isEmpty()){
                    text_surname.setError("Please enter your surname");
                }else if (dni.isEmpty()){
                    text_dni.setError("Please enter your dni");
                }else{
                    presenter.saveNote(nombre ,apellidos ,dni ,color);
                }
                return true;
            case R.id.edit:
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);

                return true;

            case R.id.update:
                if (nombre.isEmpty()){
                    text_name.setError("Please enter your name");
                }else if (apellidos.isEmpty()){
                    text_surname.setError("Please enter your surname");
                }else if (dni.isEmpty()){
                    text_dni.setError("Please enter your dni");
                }else{
                    presenter.updateCliente(id, nombre,apellidos,dni);
                }
            case R.id.delete:

                AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirm");
                alertDialog.setMessage("Are you sure ?");
                alertDialog.setNegativeButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    presenter.deleteCliente(id);
                });
                alertDialog.setPositiveButton("Cancel",(dialog, which) -> {
                    dialog.dismiss();
                });
                alertDialog.show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }

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
        Toast.makeText(ClienteActivity.this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(ClienteActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if (id != 0){
            text_name.setText(nombre);
            text_surname.setText(apellidos);
            text_dni.setText(dni);
            getSupportActionBar().setTitle("Update Cliente");
            readMode();
        }else {
            editMode();
        }
    }

    private void editMode() {
        text_name.setFocusableInTouchMode(true);
        text_surname.setFocusableInTouchMode(true);
        text_dni.setFocusableInTouchMode(true);
    }

    private void readMode() {
        text_name.setFocusableInTouchMode(false);
        text_surname.setFocusableInTouchMode(false);
        text_dni.setFocusableInTouchMode(false);
        text_name.setFocusable(false);
        text_surname.setFocusable(false);
        text_dni.setFocusable(false);
    }
}
