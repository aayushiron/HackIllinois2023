package com.example.hackillinois2023;

import static com.example.hackillinois2023.MainActivity.ERROR_CODE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ROTDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotdactivity);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Bundle bundle = getIntent().getExtras();
        String img_url = bundle.getString("image_url");
        String title = bundle.getString("recipe_title");
        int id = bundle.getInt("id");

        LinearLayout linlay = findViewById(R.id.linlay);

        CardView card = new CardView(getApplicationContext());
        ImageView img = new ImageView(getApplicationContext());
        img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        img.setAdjustViewBounds(true);
        card.setLayoutParams(new ViewGroup.LayoutParams(800, ViewGroup.LayoutParams.WRAP_CONTENT));
        img.setLayoutParams(new ViewGroup.LayoutParams(800, ViewGroup.LayoutParams.WRAP_CONTENT));
        img.setImageResource(R.drawable.pie);

        TextView txtView = new TextView(getApplicationContext());
        txtView.setTextSize(30);
        txtView.setPadding(20, 20, 20, 20);

        txtView.setText(title);

        card.addView(img);
        card.setRadius(50);

        linlay.addView(card);
        linlay.addView(txtView);
//        <View
//        android:layout_width="320dp"
//        android:layout_height="2dp"
//        android:background="#E91E63"
//        android:layout_marginTop="270dp"
//        android:layout_marginLeft="20dp"/>

        View v = new View(getApplicationContext());
        v.setLayoutParams(new ViewGroup.LayoutParams(1000, 4));
        v.setBackgroundColor(0xFF000000);
        linlay.addView(v);

        try {
            LinearLayout ingredients = new LinearLayout(getApplicationContext());
            String recipeInfo = findRecipeInfo(id);
            String recipeSteps = findRecipeSteps(id);

            TextView ingTit = new TextView(getApplicationContext());
            ingTit.setText("Ingredients:");
            ingTit.setTextSize(25);
            ingTit.setPadding(20, 0, 0, 0);

            ingredients.setOrientation(LinearLayout.VERTICAL);
            ingredients.addView(ingTit);
            JSONObject obj = new JSONObject(recipeInfo);
            JSONArray arr = obj.getJSONArray("extendedIngredients");
            for (int i = 0; i < arr.length(); i++) {
                LinearLayout ing = new LinearLayout(getApplicationContext());
                ing.setOrientation(LinearLayout.HORIZONTAL);
                JSONObject oneObject = arr.getJSONObject(i);
                TextView bull = new TextView(getApplicationContext());
                bull.setText("\u2022");
                bull.setTextSize(20);
                TextView t = new TextView(getApplicationContext());
                t.setTextSize(20);
                t.setText(oneObject.getDouble("amount") + " " + oneObject.getString("unit") + " " + oneObject.getString("name"));
                ing.addView(bull);
                ing.addView(t);
                bull.setPadding(20, 0, 20, 0);
                ingredients.addView(ing);
            }

            linlay.addView(ingredients);

            v = new View(getApplicationContext());
            v.setLayoutParams(new ViewGroup.LayoutParams(1000, 4));
            v.setBackgroundColor(0xFF000000);
            linlay.addView(v);

            LinearLayout steps = new LinearLayout(getApplicationContext());
            TextView stepsTit = new TextView(getApplicationContext());
            stepsTit.setText("Steps:");
            stepsTit.setTextSize(25);
            stepsTit.setPadding(20, 0, 0, 0);

            steps.setOrientation(LinearLayout.VERTICAL);
            steps.addView(stepsTit);
            arr = (new JSONArray(recipeSteps)).getJSONObject(0).getJSONArray("steps");
            for (int i = 0; i < arr.length(); i++) {
                LinearLayout ing = new LinearLayout(getApplicationContext());
                ing.setOrientation(LinearLayout.HORIZONTAL);
                JSONObject oneObject = arr.getJSONObject(i);
                TextView bull = new TextView(getApplicationContext());
                bull.setText("\u2022");
                bull.setTextSize(20);
                TextView t = new TextView(getApplicationContext());
                t.setTextSize(20);
                t.setText(oneObject.getString("step"));
                ing.addView(bull);
                ing.addView(t);
                bull.setPadding(20, 0, 20, 0);
                steps.addView(ing);
            }

            linlay.addView(steps);
        } catch (JSONException | IOException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private static String findRecipeInfo(int id) throws IOException {
        String url = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=0fc40001fdf2408f843ade13fb981a8b&includeNutrition=false";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            return ERROR_CODE;
        }
    }

    private static String findRecipeSteps(int id) throws IOException {
        String url = "https://api.spoonacular.com/recipes/" + id + "/analyzedInstructions?apiKey=0fc40001fdf2408f843ade13fb981a8b";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            return ERROR_CODE;
        }
    }
}