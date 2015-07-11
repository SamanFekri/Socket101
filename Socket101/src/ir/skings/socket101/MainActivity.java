package ir.skings.socket101;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.app.ProgressDialog;

public class MainActivity extends Activity {

	private ProgressDialog pd = null;
	private String data = null;
	private Socket socket;
	private String serverIpAddress= "130.385.72.146";
	private static final int REDIRECTED_SERVERPORT = 443;
	public PrintWriter out;
	public BufferedReader in;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		try {
			this.pd = ProgressDialog.show(this, "Loading..", "Please Wait...",
					true, false);
			new AsyncAction().execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class AsyncAction extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... args) {
			try {
				InetAddress serverAddr = InetAddress.getByName("130.185.72.146");
				socket = new Socket(serverAddr, REDIRECTED_SERVERPORT);
				Log.d("samani", "connected");
			} catch (UnknownHostException e1) {
				Log.d("samani", "e1 : "+e1.getMessage());
				e1.printStackTrace();
			} catch (IOException e1) {
				Log.d("samani", "e2 : "+e1.getMessage());
				e1.printStackTrace();
			}
			try {
//				out = new PrintWriter(new BufferedWriter(
//						new OutputStreamWriter(socket.getOutputStream())), true);
				Log.d("samani", "chi migi ?");
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				char[] b = new char[6];
				Log.d("samani", "salam : "+in.read(b));
				
				Log.d("samani", b[1]+"");
				
//				while (!in.ready())
//					;
//				readBuffer();
//				out.println("root\r\n");
//				// System.out.print("root\r\n");
//				while (!in.ready())
//					;
//				readBuffer();
//				out.println("root\r\n");
//				// System.out.print("root\r\n");
//				while (!in.ready())
//					;
//				String msg = "";
//
//				while (in.ready()) {
//					msg = msg + (char) in.read();
//				}
			} catch (IOException e) {
			}
			
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			return "saman";// returns what you want to pass to the onPostExecute()
		}

		protected void onPostExecute(String result) {
			// resultis the data returned from doInbackground
			MainActivity.this.data = result;

			if (MainActivity.this.pd != null) {
				MainActivity.this.pd.dismiss();
			}
		}
	}

	private String readBuffer() throws IOException {
		String msg = "";

		while (in.ready()) {
			msg = msg + (char) in.read();
		}
		// System.out.print(msg);
		if (msg.indexOf("SNX_COM> ") != -1)
			return msg.substring(0, msg.indexOf("SNX_COM> "));
		else
			return msg;
	}
}
