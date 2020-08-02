package ugm.fznzz.nightowlproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import ugm.fznzz.nightowlproject.bluetooth.serialListener;
import ugm.fznzz.nightowlproject.bluetooth.serialService;
import ugm.fznzz.nightowlproject.bluetooth.serialSocket;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Waiting extends AppCompatActivity{

    String kode;

    EditText txtIP, txtIsi;

//    public Waiting() { }

//    private enum Connected {False, Pending, True}
//    private String deviceAddress;
//    private String newline = "\r\n";
//    private TextView receiveText;
//    private serialSocket socket;
//    private serialService service;
//    private boolean initialStart = true;
//    private Connected connected = Connected.False;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        txtIP = findViewById(R.id.txt_ip);
        txtIsi = findViewById(R.id.txt_isi);
        TextView tv = findViewById(R.id.tv_testingKode);
        kode= getIntent().getExtras().getString("kode");
        tv.setText(kode);

        Thread thread = new Thread(new MyServer());
        thread.start();
    }

    class MyServer implements Runnable
    {
        ServerSocket ss;
        Socket mysocket;
        DataInputStream dis;
        String message;
        Handler handler = new Handler();


        @Override
        public void run() {
            try{
                ss = new ServerSocket(9700);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Waiting for Client", Toast.LENGTH_SHORT).show();
                    }
                });
                while (true)
                {
                    mysocket = ss.accept();
                    dis = new DataInputStream(mysocket.getInputStream());
                    message = dis.readUTF();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"message: "+message,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public void sendClick(View v)
    {
        BackgroundTask b = new BackgroundTask();
        b.execute(txtIP.getText().toString(),txtIsi.getText().toString());
    }

    public void copyClick(View v)
    {
        txtIsi.setText(kode);
    }

    public void backClick(View v)
    {
        finish();
    }

    class BackgroundTask extends AsyncTask<String, Void,String>
    {
        Socket s;
        DataOutputStream dos;
        String ip, message;


        @Override
        protected String doInBackground(String... strings) {
            ip = strings[0];
            message = strings[1];
            try {
                s = new Socket(ip,9700);
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(message);

                dos.close();
                s.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

//        TextView address = findViewById(R.id.address_text);
//
//        ImageView btBack = findViewById(R.id.bt_back_waiting);
//        TextView tv = findViewById(R.id.tv_testingKode);
//        kode= getIntent().getExtras().getString("kode");
//        tv.setText(kode);
//        receiveText = findViewById(R.id.receive_text);
//        receiveText.setTextColor(getResources().getColor(R.color.colorRecieveText)); // set as default color to reduce number of spans
//        receiveText.setMovementMethod(ScrollingMovementMethod.getInstance());
//        TextView sendtext= findViewById(R.id.send_text);
//        Button bt_copy = findViewById(R.id.bt_copy);
//        bt_copy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendtext.setText(kode);
//            }
//        });
//
//        Button bt_send =findViewById(R.id.bt_send);
//        bt_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                send(sendtext.getText().toString());
//            }
//        });
//
//        btBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        deviceAddress = address.getText().toString(); //insert address her
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if(service != null)
//            service.attach(this);
//        // deleted -- prevents service destroy on unbind from recreated activity caused by orientation change
//    }
//
//    @Override
//    public void onStop() {
//        if(service != null)
//            service.detach();
//        super.onStop();
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if(initialStart && service !=null) {
//            initialStart = false;
//            this.runOnUiThread(this::connect);
//        }
//    }
//
//    /*
//     * UI
//     */
//
//
//    /*
//     * Serial + UI
//     */
//    private void connect() {
//        try {
//            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);
//            String deviceName = device.getName() != null ? device.getName() : device.getAddress();
//            status("connecting...");
//            connected = Connected.Pending;
//            socket = new serialSocket();
//            service.connect((serialListener) this, "Connected to " + deviceName);
//            socket.connect(getApplicationContext(), service, device);
//        } catch (Exception e) {
//            onSerialConnectError(e);
//        }
//    }
//
//    private void disconnect() {
//        connected = Connected.False;
//        service.disconnect();
//        socket.disconnect();
//        socket = null;
//    }
//
//    private void send(String str) {
//        if(connected != Connected.True) {
//            Toast.makeText(this, "not connected", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        try {
//            SpannableStringBuilder spn = new SpannableStringBuilder(str+'\n');
//            byte[] data = (str + newline).getBytes();
//            socket.write(data);
//        } catch (Exception e) {
//            onSerialIoError(e);
//        }
//    }
//
//    private void receive(byte[] data) {
//        receiveText.append(new String(data));
//    }
//
//    private void status(String str) {
//        SpannableStringBuilder spn = new SpannableStringBuilder(str+'\n');
//        spn.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorStatusText)), 0, spn.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        receiveText.append(spn);
//    }
//
//    /*
//     * SerialListener
//     */
//    @Override
//    public void onSerialConnect() {
//        status("connected");
//        connected = Connected.True;
//    }
//
//    @Override
//    public void onSerialConnectError(Exception e) {
//        status("connection failed: " + e.getMessage());
//        disconnect();
//    }
//
//    @Override
//    public void onSerialRead(byte[] data) {
//        receive(data);
//    }
//
//    @Override
//    public void onSerialIoError(Exception e) {
//        status("connection lost: " + e.getMessage());
//        disconnect();
//    }
}
