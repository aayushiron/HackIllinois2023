package com.example.hackillinois2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.hackillinois2023.FoodActivity;
import com.example.hackillinois2023.ROTDActivity;


public class MainActivity extends AppCompatActivity {

    public static final String ERROR_CODE = "b3f0b0b51c4fb366b9787be1b7c5b809efaecb1ad4413af22afc75f96552df38";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int l_id = 0;
        int b_id = 0;
        int ed_id = 0;
        ImageButton btn = findViewById(R.id.btn);
        btn.setX(10);
        btn.setY(10);

//        Button add_ingredient = findViewById(R.id.add_ingredient);
        LinearLayout main_layout = findViewById(R.id.main_layout);
        btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           // on below line we are displaying a toast message.
          Toast.makeText(MainActivity.this, "Recipe of the Day!", Toast.LENGTH_SHORT).show();
             }
           });
        EditText ed = findViewById(R.id.ingredients);
        Button n = findViewById(R.id.next);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ed.getText().toString().equals("")) {
                    Intent inn1 = new Intent(getApplicationContext(), RecipeList.class);
                    inn1.putExtra("ingredients", ed.getText().toString());
                    startActivity(inn1);
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn2 = new Intent(getApplicationContext(), ROTDActivity.class);
                inn2.putExtra("image_url", ":)");
                inn2.putExtra("recipe_title", "Easy Key Lime Pie");
                inn2.putExtra("id", 642003);
                startActivity(inn2);
            }
        });






//        add_ingredient.setX(20);
//        add_ingredient.setY(50);
//        add_ingredient.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LinearLayout l = new LinearLayout(getApplicationContext());
//                l.setId(l_id);
//                LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//                //params.height = LinearLayout.LayoutParams.MATCH_PARENT;
//                //params.width = LinearLayout.LayoutParams.MATCH_PARENT;
//                l.setLayoutParams(params);
//            }
//        });
    }
}