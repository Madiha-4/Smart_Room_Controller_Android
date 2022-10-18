package rob.myappcompany.smartroomcontroller;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;
import static com.android.volley.Request.*;

public class HttpsHelper {


    public void sendReq(String url, String apiKey, String deviceId, String action, String state, View v){
        RequestQueue mRequestQueue = Volley.newRequestQueue(v.getContext());

        JSONObject params = new JSONObject();
        JSONObject internalObj = new JSONObject();

        String jsonString;

        try {
            internalObj.put("state", state);

            params.put("api_key", apiKey);
            params.put("device_id",deviceId);
            params.put("action", action);
            params.put("value", internalObj);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonString = params.toString();

        //String Request initialized
        StringRequest mStringRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(v.getContext(), "Response :" + response,
                        Toast.LENGTH_SHORT).show();  //display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(v.getContext(), "Error :" + error.toString(),
                        Toast.LENGTH_SHORT).show();  //display the response on screen

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError
            {
                byte[] body = new byte[0];
                try {
                    body = jsonString.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return body;
            }
        };

        mRequestQueue.add(mStringRequest);
        }

}
