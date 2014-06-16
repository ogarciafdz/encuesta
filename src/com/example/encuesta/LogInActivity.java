package com.example.encuesta;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends Activity implements OnClickListener {

	Button btLogin;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);

		String patron = "dd/MM/yyyy";
		SimpleDateFormat formato = new SimpleDateFormat(patron);

		Date date = new Date();
		final TextView txv = (TextView) findViewById(R.id.login_tvFecha);
		final EditText txData = (EditText) findViewById(R.id.login_usuario);
		Button btLogin = (Button) findViewById(R.id.login_btnLogear);
		btLogin.setOnClickListener(new OnClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				String usuario = txData.getText().toString();
				if (usuario.equals("")) {
					Toast.makeText(getApplicationContext(), "Error en el usuario y/o contrasena", Toast.LENGTH_SHORT).show();
				} else {
					Intent intento = new Intent(getApplicationContext(), Start.class);
					intento.putExtra("namePerson", usuario);
					startActivity(intento);
				}

			}
		});
		txv.setText("Fecha: " + formato.format(date).toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View view) {
		Toast.makeText(LogInActivity.this, "Clickeando al boton",
				Toast.LENGTH_SHORT).show();
		// poner aqui el codigo del boton
		Intent intento = new Intent(LogInActivity.this, PruebaDatos.class);
		startActivity(intento);
	}

}
