package com.example.encuesta;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.example.encuesta.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PruebaDatos extends Activity {

	private static final String SOAP_ACTION = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
	private static final String METHOD_NAME = "CelsiusToFahrenheit";
	private static final String NAMESPACE = "http://www.w3schools.com/webservices/";
	private static final String URL = "http://www.w3schools.com/WebServices/TempConvert.asmx";
	final HttpTransportSE transporte = new HttpTransportSE(URL);
	TextView tx;
	Button bOk, bLimpiar;
	EditText ed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prueba_web);
		tx = (TextView) findViewById(R.id.tvResult);
		tx.setText("");

		bOk = (Button) findViewById(R.id.bConvertir);
		bOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setConvert();
			}
		});

		ed = (EditText) findViewById(R.id.etGrados);

		bLimpiar = (Button) findViewById(R.id.bLimpiar);
		bLimpiar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearCampos();

			}
		});

	}

	protected void setConvert() {
		try {
			int numero = Integer.parseInt(ed.getText().toString());

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("Celsius", numero);

			SoapSerializationEnvelope soapEnveloped = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			soapEnveloped.dotNet = true;
			soapEnveloped.setOutputSoapObject(request);

			transporte.call(SOAP_ACTION, soapEnveloped);
			SoapPrimitive result = (SoapPrimitive) soapEnveloped.getResponse();
			tx.setText( result.toString());
			
		} catch (IOException | XmlPullParserException e) {
			tx.setText(e.getMessage());
			e.printStackTrace();
		}

	}

	protected void clearCampos() {
		ed.setText("");
		tx.setText("");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prueba_web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
