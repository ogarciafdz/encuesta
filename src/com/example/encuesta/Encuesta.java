package com.example.encuesta;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.Toast;

public class Encuesta extends Activity {
	private ImageButton selectedP1;
	private ImageButton selectedP2;
	private ImageButton selectedP3;
	private ImageButton h15;
	private ImageButton h14;
	private ImageButton h13;
	private ImageButton h12;
	private ImageButton h11;
	private ImageButton h25;
	private ImageButton h24;
	private ImageButton h23;
	private ImageButton h22;
	private ImageButton h21;
	private ImageButton h35;
	private ImageButton h34;
	private ImageButton h33;
	private ImageButton h32;
	private ImageButton h31;

	// url to create new Empleado Reemplaza la IP de tu equipo o la direccion de
	// tu servidor
	// Si tu servidor es tu PC colocar IP Ej:
	// "http://127.97.99.200/taller06oct/..", no colocar
	// "http://localhost/taller06oct/.."

	// JSON Node names
	static final String TAG_SUCCESS = "success";

	// url to create new Empleado Reemplaza la IP de tu equipo o la direccion de
	// tu servidor
	// Si tu servidor es tu PC colocar IP Ej:
	// "http://127.97.99.200/taller06oct/..", no colocar
	// "http://localhost/taller06oct/.."
	// private static String url_insert_registro =
	// "http://AquiTuServidor/taller06oct/nuevoempleado.php";
	static String url_insert_registro = "http://127.0.0.1/encuestas/insert2.php";
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle = getIntent().getExtras();
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_encuesta);

		h15 = (ImageButton) findViewById(R.id.buttonP1e5);
		h14 = (ImageButton) findViewById(R.id.buttonP1e4);
		h13 = (ImageButton) findViewById(R.id.buttonP1e3);
		h12 = (ImageButton) findViewById(R.id.buttonP1e2);
		h11 = (ImageButton) findViewById(R.id.buttonP1e1);

		h25 = (ImageButton) findViewById(R.id.buttonP2e5);
		h24 = (ImageButton) findViewById(R.id.buttonP2e4);
		h23 = (ImageButton) findViewById(R.id.buttonP2e3);
		h22 = (ImageButton) findViewById(R.id.buttonP2e2);
		h21 = (ImageButton) findViewById(R.id.buttonP2e1);

		h35 = (ImageButton) findViewById(R.id.buttonP3e5);
		h34 = (ImageButton) findViewById(R.id.buttonP3e4);
		h33 = (ImageButton) findViewById(R.id.buttonP3e3);
		h32 = (ImageButton) findViewById(R.id.buttonP3e2);
		h31 = (ImageButton) findViewById(R.id.buttonP3e1);
		// Animaciones
		final AnimationSet setAnimation = new AnimationSet(true);
		// Escala
		final ScaleAnimation scaleAnimation = new ScaleAnimation(3, 1, 2, 1);
		scaleAnimation.setDuration(1000);
		setAnimation.addAnimation(scaleAnimation);

		setupAnimation(h15, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h14, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h13, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h12, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h11, scaleAnimation, R.anim.scale_anim);

		setupAnimation(h25, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h24, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h23, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h22, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h21, scaleAnimation, R.anim.scale_anim);

		setupAnimation(h35, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h34, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h33, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h32, scaleAnimation, R.anim.scale_anim);
		setupAnimation(h31, scaleAnimation, R.anim.scale_anim);

	}

	private void setupAnimation(View view, final Animation animation,
			final int animationID) {
		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Animacion del boton en circulo
				final RotateAnimation rotateAnimation = new RotateAnimation(0,
						360, Animation.RELATIVE_TO_SELF, .5f,
						Animation.RELATIVE_TO_SELF, .5f);
				rotateAnimation.setDuration(1000);

				// Animaciones
				final AnimationSet setAnimation = new AnimationSet(true);
				setAnimation.addAnimation(rotateAnimation);

				ArrayList<View> buttons = getViewsByTag(
						(ViewGroup) findViewById(R.id.containerEncuesta),
						(String) v.getTag());
				switch ((String) v.getTag()) {
				case "buttonP1":
					selectedP1 = (ImageButton) v;

					break;
				case "buttonP2":
					selectedP2 = (ImageButton) v;
					break;
				case "buttonP3":
					selectedP3 = (ImageButton) v;
					break;
				default:
					break;
				}

				for (View view : buttons) {
					if (v.getId() != view.getId()) {
						view.setAlpha(.5f);

					} else {
						view.setAlpha(1);
						v.startAnimation(AnimationUtils.loadAnimation(
								Encuesta.this, animationID));
					}
				}
			}
		});
	}

	// public void myOnClick(View view) {
	// // Animacion del boton en circulo
	// final RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
	// Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF,
	// .5f);
	// rotateAnimation.setDuration(1000);
	//
	// // Animaciones
	// final AnimationSet setAnimation = new AnimationSet(true);
	// setAnimation.addAnimation(rotateAnimation);
	//
	// ArrayList<View> buttons = getViewsByTag(
	// (ViewGroup) findViewById(R.id.containerEncuesta),
	// (String) view.getTag());
	// switch ((String) view.getTag()) {
	// case "buttonP1":
	// selectedP1 = (ImageButton) view;
	//
	// break;
	// case "buttonP2":
	// selectedP2 = (ImageButton) view;
	// break;
	// case "buttonP3":
	// selectedP3 = (ImageButton) view;
	// break;
	// default:
	// break;
	// }
	//
	// for (View v : buttons) {
	// if (view.getId() != v.getId()) {
	// v.setAlpha(.5f);
	//
	// } else {
	// v.setAlpha(1);
	//
	// }
	// }
	// }

	public void terminar(View view) {
		if (selectedP1 != null && selectedP2 != null && selectedP3 != null) {
			//sqliteHelper sqlh = new sqliteHelper(this, "Registro", null, 1);
			// SQLiteDatabase db = sqlh.getWritableDatabase();

			String calS1 = selectedP1.getResources().getResourceName(
					selectedP1.getId());
			calS1 = calS1.substring(calS1.length() - 1);

			String calS2 = selectedP2.getResources().getResourceName(
					selectedP2.getId());
			calS2 = calS2.substring(calS2.length() - 1);

			String calS3 = selectedP3.getResources().getResourceName(
					selectedP3.getId());
			calS3 = calS3.substring(calS3.length() - 1);
			// if (db != null) {
			 String sql =
			 "INSERT INTO Registro (usuario, pregunta1, pregunta2, pregunta3) "
			 + "VALUES ("
			 + "'test', "
			 + calS1
			 + ", "
			 + calS2
			 + ", "
			 + calS3 + ")";
			 Log.i("sql", sql);
			// db.execSQL(sql);
			// db.close();
			// }

			 new Insertar(Encuesta.this).execute(calS1, calS2, calS3);
			

			finish();
		}

		Intent intent = new Intent(this, Start.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

	}

	private static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
		ArrayList<View> views = new ArrayList<View>();
		final int childCount = root.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = root.getChildAt(i);
			if (child instanceof ViewGroup) {
				views.addAll(getViewsByTag((ViewGroup) child, tag));
			}

			final Object tagObj = child.getTag();
			if (tagObj != null && tagObj.equals(tag)) {
				views.add(child);
			}

		}
		return views;
	}

	private boolean insertar(String calS1, String calS2, String calS3){
			Log.i("HttpClient", "Adentro de insertar");
			HttpClient httpclient;
			List<NameValuePair> nameValuePairs;
			HttpPost httpPost;
			httpclient = new DefaultHttpClient();
			httpPost = new HttpPost(url_insert_registro);

			Evaluacion evaluacion = new Evaluacion(bundle.getString("namePerson"), calS1,
					calS2, calS3);
			nameValuePairs = new ArrayList<NameValuePair>(4);
			nameValuePairs.add(new BasicNameValuePair("nombre", evaluacion
					.getNombre()));
			nameValuePairs.add(new BasicNameValuePair("preg1", evaluacion
					.getPregunta1().toString()));
			nameValuePairs.add(new BasicNameValuePair("preg2", evaluacion
					.getPregunta2().toString()));
			nameValuePairs.add(new BasicNameValuePair("preg3", evaluacion
					.getPregunta3().toString()));
			Log.i("HttpClient", "Creando la evaluacion");

			Log.i("HttpClient", "Creando URL");
			// URL del servidor con el archivo php para insertar
			// no se recomienda poner localhost sino la IP de la maquina
			try {
				   httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			       httpclient.execute(httpPost);
			       return true;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return false;

	}
	
	class Insertar extends AsyncTask<String,String,String>{
		
		private Activity context;
		
		Insertar(Activity context){
			this.context=context;
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(insertar(params[0], params[1], params[2]))
				context.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub							
						Toast.makeText(context, "Registro insertado con éxito", Toast.LENGTH_LONG).show();
					}						
				});
			else
				context.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub							
						Toast.makeText(context, "Registro no insertado con éxito", Toast.LENGTH_LONG).show();
					}					
				});
		      return null;
		}			
	}

}
