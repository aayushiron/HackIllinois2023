package com.example.hackillinois2023;

import static com.example.hackillinois2023.MainActivity.ERROR_CODE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RecipeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);

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

        TextView txtView = new TextView(getApplicationContext());
        txtView.setTextSize(30);
        txtView.setPadding(20, 20, 20, 20);

        new ImageLoadTask(img_url, img).execute();
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
            String recipeInfo = "{\n" +
                    "    \"vegetarian\": true,\n" +
                    "    \"vegan\": false,\n" +
                    "    \"glutenFree\": false,\n" +
                    "    \"dairyFree\": false,\n" +
                    "    \"veryHealthy\": false,\n" +
                    "    \"cheap\": false,\n" +
                    "    \"veryPopular\": false,\n" +
                    "    \"sustainable\": false,\n" +
                    "    \"lowFodmap\": false,\n" +
                    "    \"weightWatcherSmartPoints\": 88,\n" +
                    "    \"gaps\": \"no\",\n" +
                    "    \"preparationMinutes\": -1,\n" +
                    "    \"cookingMinutes\": -1,\n" +
                    "    \"aggregateLikes\": 1,\n" +
                    "    \"healthScore\": 17,\n" +
                    "    \"creditsText\": \"Foodista.com – The Cooking Encyclopedia Everyone Can Edit\",\n" +
                    "    \"license\": \"CC BY 3.0\",\n" +
                    "    \"sourceName\": \"Foodista\",\n" +
                    "    \"pricePerServing\": 190.77,\n" +
                    "    \"extendedIngredients\": [{\n" +
                    "        \"id\": 19334,\n" +
                    "        \"aisle\": \"Baking\",\n" +
                    "        \"image\": \"light-brown-sugar.jpg\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"brown sugar\",\n" +
                    "        \"nameClean\": \"golden brown sugar\",\n" +
                    "        \"original\": \"1/4 cup brown sugar\",\n" +
                    "        \"originalName\": \"brown sugar\",\n" +
                    "        \"amount\": 0.25,\n" +
                    "        \"unit\": \"cup\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 0.25,\n" +
                    "                \"unitShort\": \"cups\",\n" +
                    "                \"unitLong\": \"cups\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 59.147,\n" +
                    "                \"unitShort\": \"ml\",\n" +
                    "                \"unitLong\": \"milliliters\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 2010,\n" +
                    "        \"aisle\": \"Spices and Seasonings\",\n" +
                    "        \"image\": \"cinnamon.jpg\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"cinnamon\",\n" +
                    "        \"nameClean\": \"cinnamon\",\n" +
                    "        \"original\": \"3/4 teaspoon cinnamon\",\n" +
                    "        \"originalName\": \"cinnamon\",\n" +
                    "        \"amount\": 0.75,\n" +
                    "        \"unit\": \"teaspoon\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 0.75,\n" +
                    "                \"unitShort\": \"tsps\",\n" +
                    "                \"unitLong\": \"teaspoons\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 0.75,\n" +
                    "                \"unitShort\": \"tsps\",\n" +
                    "                \"unitLong\": \"teaspoons\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 20081,\n" +
                    "        \"aisle\": \"Baking\",\n" +
                    "        \"image\": \"flour.png\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"flour\",\n" +
                    "        \"nameClean\": \"wheat flour\",\n" +
                    "        \"original\": \"3 cups all-purpose flour\",\n" +
                    "        \"originalName\": \"all-purpose flour\",\n" +
                    "        \"amount\": 3.0,\n" +
                    "        \"unit\": \"cups\",\n" +
                    "        \"meta\": [\"all-purpose\"],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 3.0,\n" +
                    "                \"unitShort\": \"cups\",\n" +
                    "                \"unitLong\": \"cups\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 709.764,\n" +
                    "                \"unitShort\": \"ml\",\n" +
                    "                \"unitLong\": \"milliliters\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 1109003,\n" +
                    "        \"aisle\": \"Produce\",\n" +
                    "        \"image\": \"apple.jpg\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"gala apple\",\n" +
                    "        \"nameClean\": \"gala apple\",\n" +
                    "        \"original\": \"1 gala apple\",\n" +
                    "        \"originalName\": \"gala apple\",\n" +
                    "        \"amount\": 1.0,\n" +
                    "        \"unit\": \"\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 1.0,\n" +
                    "                \"unitShort\": \"\",\n" +
                    "                \"unitLong\": \"\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 1.0,\n" +
                    "                \"unitShort\": \"\",\n" +
                    "                \"unitLong\": \"\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 1089003,\n" +
                    "        \"aisle\": \"Produce\",\n" +
                    "        \"image\": \"grannysmith-apple.png\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"granny smith apple\",\n" +
                    "        \"nameClean\": \"granny smith apple\",\n" +
                    "        \"original\": \"1 granny smith apple\",\n" +
                    "        \"originalName\": \"granny smith apple\",\n" +
                    "        \"amount\": 1.0,\n" +
                    "        \"unit\": \"\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 1.0,\n" +
                    "                \"unitShort\": \"\",\n" +
                    "                \"unitLong\": \"\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 1.0,\n" +
                    "                \"unitShort\": \"\",\n" +
                    "                \"unitLong\": \"\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 9152,\n" +
                    "        \"aisle\": \"Produce\",\n" +
                    "        \"image\": \"lemon-juice.jpg\",\n" +
                    "        \"consistency\": \"LIQUID\",\n" +
                    "        \"name\": \"lemon juice\",\n" +
                    "        \"nameClean\": \"lemon juice\",\n" +
                    "        \"original\": \"1 tablespoon lemon juice\",\n" +
                    "        \"originalName\": \"lemon juice\",\n" +
                    "        \"amount\": 1.0,\n" +
                    "        \"unit\": \"tablespoon\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 1.0,\n" +
                    "                \"unitShort\": \"Tbsp\",\n" +
                    "                \"unitLong\": \"Tbsp\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 1.0,\n" +
                    "                \"unitShort\": \"Tbsp\",\n" +
                    "                \"unitLong\": \"Tbsp\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 2025,\n" +
                    "        \"aisle\": \"Spices and Seasonings\",\n" +
                    "        \"image\": \"ground-nutmeg.jpg\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"nutmeg\",\n" +
                    "        \"nameClean\": \"nutmeg\",\n" +
                    "        \"original\": \"1/2 teaspoon nutmeg (4 g)\",\n" +
                    "        \"originalName\": \"1/2 teaspoon nutmeg\",\n" +
                    "        \"amount\": 4.0,\n" +
                    "        \"unit\": \"g\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 0.141,\n" +
                    "                \"unitShort\": \"oz\",\n" +
                    "                \"unitLong\": \"ounces\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 4.0,\n" +
                    "                \"unitShort\": \"g\",\n" +
                    "                \"unitLong\": \"grams\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 2047,\n" +
                    "        \"aisle\": \"Spices and Seasonings\",\n" +
                    "        \"image\": \"salt.jpg\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"salt\",\n" +
                    "        \"nameClean\": \"table salt\",\n" +
                    "        \"original\": \"1/4 teaspoon salt\",\n" +
                    "        \"originalName\": \"salt\",\n" +
                    "        \"amount\": 0.25,\n" +
                    "        \"unit\": \"teaspoon\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 0.25,\n" +
                    "                \"unitShort\": \"tsps\",\n" +
                    "                \"unitLong\": \"teaspoons\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 0.25,\n" +
                    "                \"unitShort\": \"tsps\",\n" +
                    "                \"unitLong\": \"teaspoons\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 19335,\n" +
                    "        \"aisle\": \"Baking\",\n" +
                    "        \"image\": \"sugar-in-bowl.png\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"sugar\",\n" +
                    "        \"nameClean\": \"sugar\",\n" +
                    "        \"original\": \"2 cups sugar\",\n" +
                    "        \"originalName\": \"sugar\",\n" +
                    "        \"amount\": 2.0,\n" +
                    "        \"unit\": \"cups\",\n" +
                    "        \"meta\": [],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 2.0,\n" +
                    "                \"unitShort\": \"cups\",\n" +
                    "                \"unitLong\": \"cups\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 473.176,\n" +
                    "                \"unitShort\": \"ml\",\n" +
                    "                \"unitLong\": \"milliliters\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": 1145,\n" +
                    "        \"aisle\": \"Milk, Eggs, Other Dairy\",\n" +
                    "        \"image\": \"butter-sliced.jpg\",\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"butter\",\n" +
                    "        \"nameClean\": \"unsalted butter\",\n" +
                    "        \"original\": \"3/4 stick (6 tablespoons) unsalted butter, melted\",\n" +
                    "        \"originalName\": \"3/4 stick unsalted butter, melted\",\n" +
                    "        \"amount\": 6.0,\n" +
                    "        \"unit\": \"tablespoons\",\n" +
                    "        \"meta\": [\"unsalted\", \"melted\"],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 6.0,\n" +
                    "                \"unitShort\": \"Tbsps\",\n" +
                    "                \"unitLong\": \"Tbsps\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 6.0,\n" +
                    "                \"unitShort\": \"Tbsps\",\n" +
                    "                \"unitLong\": \"Tbsps\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }, {\n" +
                    "        \"id\": -1,\n" +
                    "        \"aisle\": \"?\",\n" +
                    "        \"image\": null,\n" +
                    "        \"consistency\": \"SOLID\",\n" +
                    "        \"name\": \"tropical foods pb&j mix\",\n" +
                    "        \"nameClean\": null,\n" +
                    "        \"original\": \"¼ cup Tropical Foods PB&J mix, rough chopped\",\n" +
                    "        \"originalName\": \"Tropical Foods PB&J mix, rough chopped\",\n" +
                    "        \"amount\": 0.25,\n" +
                    "        \"unit\": \"cup\",\n" +
                    "        \"meta\": [\"chopped\"],\n" +
                    "        \"measures\": {\n" +
                    "            \"us\": {\n" +
                    "                \"amount\": 0.25,\n" +
                    "                \"unitShort\": \"cups\",\n" +
                    "                \"unitLong\": \"cups\"\n" +
                    "            },\n" +
                    "            \"metric\": {\n" +
                    "                \"amount\": 59.147,\n" +
                    "                \"unitShort\": \"ml\",\n" +
                    "                \"unitLong\": \"milliliters\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }],\n" +
                    "    \"id\": 632583,\n" +
                    "    \"title\": \"Apple Pie with PB&J Streusel\",\n" +
                    "    \"readyInMinutes\": 45,\n" +
                    "    \"servings\": 2,\n" +
                    "    \"sourceUrl\": \"http://www.foodista.com/recipe/3WLJQBDB/apple-pie-with-pb-j-streusel\",\n" +
                    "    \"image\": \"https://spoonacular.com/recipeImages/632583-556x370.jpg\",\n" +
                    "    \"imageType\": \"jpg\",\n" +
                    "    \"summary\": \"You can never have too many American recipes, so give Apple Pie with PB&J Streusel a try. This recipe serves 2 and costs $1.91 per serving. This dessert has <b>1967 calories</b>, <b>20g of protein</b>, and <b>38g of fat</b> per serving. From preparation to the plate, this recipe takes around <b>45 minutes</b>. This recipe from Foodista has 1 fans. If you have granny smith apple, galan apple, flour, and a few other ingredients on hand, you can make it. It is a good option if you're following a <b>lacto ovo vegetarian</b> diet. All things considered, we decided this recipe <b>deserves a spoonacular score of 59%</b>. This score is pretty good. If you like this recipe, take a look at these similar recipes: <a href=\\\"https://spoonacular.com/recipes/apple-pie-streusel-cake-565640\\\">Apple Pie Streusel Cake</a>, <a href=\\\"https://spoonacular.com/recipes/apple-pear-streusel-pie-265449\\\">Apple-Pear Streusel Pie</a>, and <a href=\\\"https://spoonacular.com/recipes/caramel-apple-streusel-pie-559616\\\">Caramel Apple Streusel Pie</a>.\",\n" +
                    "    \"cuisines\": [\"American\"],\n" +
                    "    \"dishTypes\": [\"dessert\"],\n" +
                    "    \"diets\": [\"lacto ovo vegetarian\"],\n" +
                    "    \"occasions\": [],\n" +
                    "    \"winePairing\": {\n" +
                    "        \"pairedWines\": [\"prosecco\", \"moscato dasti\", \"late harvest riesling\"],\n" +
                    "        \"pairingText\": \"Prosecco, Moscato d'Asti, and Late Harvest Riesling are my top picks for Apple Pie. These dessert wines have the right amount of sweetness and light, fruity flavors that won't overpower apple pie. The Bellafina Prosecco with a 4.6 out of 5 star rating seems like a good match. It costs about 10 dollars per bottle.\",\n" +
                    "        \"productMatches\": [{\n" +
                    "            \"id\": 461572,\n" +
                    "            \"title\": \"Bellafina Prosecco\",\n" +
                    "            \"description\": \"Intensely aromatic and crisp on the palate, with aromas and flavorsof yellow apple, white peach, pear and notes of white flowers.Excellent as an aperitif, it is a perfect accompaniment to hors-d'oeuvres and delicate first courses. Also wonderful with fish and shellfish.\",\n" +
                    "            \"price\": \"$9.99\",\n" +
                    "            \"imageUrl\": \"https://spoonacular.com/productImages/461572-312x231.jpg\",\n" +
                    "            \"averageRating\": 0.9199999999999999,\n" +
                    "            \"ratingCount\": 10.0,\n" +
                    "            \"score\": 0.8877419354838709,\n" +
                    "            \"link\": \"https://click.linksynergy.com/deeplink?id=*QCiIS6t4gA&mid=2025&murl=https%3A%2F%2Fwww.wine.com%2Fproduct%2Fbellafina-prosecco%2F136821\"\n" +
                    "        }]\n" +
                    "    },\n" +
                    "    \"instructions\": \"<ol><li>Preheat oven to 425 degrees.</li><li>Peel and slice apples into quarter sized slices.</li><li>Toss apples in cinnamon, nutmeg, lemon juice, sugar and salt; let sit.</li><li>Mix all ingredients for streusel together.</li><li>Fill a greased small baking dish with apple mixture.</li><li>Top with streusel and bake until streusel is brown.</li></ol>\",\n" +
                    "    \"analyzedInstructions\": [{\n" +
                    "        \"name\": \"\",\n" +
                    "        \"steps\": [{\n" +
                    "            \"number\": 1,\n" +
                    "            \"step\": \"Preheat oven to 425 degrees.Peel and slice apples into quarter sized slices.Toss apples in cinnamon, nutmeg, lemon juice, sugar and salt; let sit.\",\n" +
                    "            \"ingredients\": [{\n" +
                    "                \"id\": 9152,\n" +
                    "                \"name\": \"lemon juice\",\n" +
                    "                \"localizedName\": \"lemon juice\",\n" +
                    "                \"image\": \"lemon-juice.jpg\"\n" +
                    "            }, {\n" +
                    "                \"id\": 2010,\n" +
                    "                \"name\": \"cinnamon\",\n" +
                    "                \"localizedName\": \"cinnamon\",\n" +
                    "                \"image\": \"cinnamon.jpg\"\n" +
                    "            }, {\n" +
                    "                \"id\": 9003,\n" +
                    "                \"name\": \"apple\",\n" +
                    "                \"localizedName\": \"apple\",\n" +
                    "                \"image\": \"apple.jpg\"\n" +
                    "            }, {\n" +
                    "                \"id\": 2025,\n" +
                    "                \"name\": \"nutmeg\",\n" +
                    "                \"localizedName\": \"nutmeg\",\n" +
                    "                \"image\": \"ground-nutmeg.jpg\"\n" +
                    "            }, {\n" +
                    "                \"id\": 19335,\n" +
                    "                \"name\": \"sugar\",\n" +
                    "                \"localizedName\": \"sugar\",\n" +
                    "                \"image\": \"sugar-in-bowl.png\"\n" +
                    "            }, {\n" +
                    "                \"id\": 2047,\n" +
                    "                \"name\": \"salt\",\n" +
                    "                \"localizedName\": \"salt\",\n" +
                    "                \"image\": \"salt.jpg\"\n" +
                    "            }],\n" +
                    "            \"equipment\": [{\n" +
                    "                \"id\": 404784,\n" +
                    "                \"name\": \"oven\",\n" +
                    "                \"localizedName\": \"oven\",\n" +
                    "                \"image\": \"oven.jpg\"\n" +
                    "            }]\n" +
                    "        }, {\n" +
                    "            \"number\": 2,\n" +
                    "            \"step\": \"Mix all ingredients for streusel together.Fill a greased small baking dish with apple mixture.Top with streusel and bake until streusel is brown.\",\n" +
                    "            \"ingredients\": [{\n" +
                    "                \"id\": 9003,\n" +
                    "                \"name\": \"apple\",\n" +
                    "                \"localizedName\": \"apple\",\n" +
                    "                \"image\": \"apple.jpg\"\n" +
                    "            }],\n" +
                    "            \"equipment\": [{\n" +
                    "                \"id\": 404646,\n" +
                    "                \"name\": \"baking pan\",\n" +
                    "                \"localizedName\": \"baking pan\",\n" +
                    "                \"image\": \"roasting-pan.jpg\"\n" +
                    "            }, {\n" +
                    "                \"id\": 404784,\n" +
                    "                \"name\": \"oven\",\n" +
                    "                \"localizedName\": \"oven\",\n" +
                    "                \"image\": \"oven.jpg\"\n" +
                    "            }]\n" +
                    "        }]\n" +
                    "    }],\n" +
                    "    \"originalId\": null,\n" +
                    "    \"spoonacularSourceUrl\": \"https://spoonacular.com/apple-pie-with-pb-j-streusel-632583\"\n" +
                    "}";//findRecipeInfo(id);
            String recipeSteps = "[{\n" +
                    "    \"name\": \"\",\n" +
                    "    \"steps\": [{\n" +
                    "        \"number\": 1,\n" +
                    "        \"step\": \"Preheat oven to 425 degrees.Peel and slice apples into quarter sized slices.Toss apples in cinnamon, nutmeg, lemon juice, sugar and salt; let sit.\",\n" +
                    "        \"ingredients\": [{\n" +
                    "            \"id\": 9152,\n" +
                    "            \"name\": \"lemon juice\",\n" +
                    "            \"localizedName\": \"lemon juice\",\n" +
                    "            \"image\": \"lemon-juice.jpg\"\n" +
                    "        }, {\n" +
                    "            \"id\": 2010,\n" +
                    "            \"name\": \"cinnamon\",\n" +
                    "            \"localizedName\": \"cinnamon\",\n" +
                    "            \"image\": \"cinnamon.jpg\"\n" +
                    "        }, {\n" +
                    "            \"id\": 9003,\n" +
                    "            \"name\": \"apple\",\n" +
                    "            \"localizedName\": \"apple\",\n" +
                    "            \"image\": \"apple.jpg\"\n" +
                    "        }, {\n" +
                    "            \"id\": 2025,\n" +
                    "            \"name\": \"nutmeg\",\n" +
                    "            \"localizedName\": \"nutmeg\",\n" +
                    "            \"image\": \"ground-nutmeg.jpg\"\n" +
                    "        }, {\n" +
                    "            \"id\": 19335,\n" +
                    "            \"name\": \"sugar\",\n" +
                    "            \"localizedName\": \"sugar\",\n" +
                    "            \"image\": \"sugar-in-bowl.png\"\n" +
                    "        }, {\n" +
                    "            \"id\": 2047,\n" +
                    "            \"name\": \"salt\",\n" +
                    "            \"localizedName\": \"salt\",\n" +
                    "            \"image\": \"salt.jpg\"\n" +
                    "        }],\n" +
                    "        \"equipment\": [{\n" +
                    "            \"id\": 404784,\n" +
                    "            \"name\": \"oven\",\n" +
                    "            \"localizedName\": \"oven\",\n" +
                    "            \"image\": \"oven.jpg\"\n" +
                    "        }]\n" +
                    "    }, {\n" +
                    "        \"number\": 2,\n" +
                    "        \"step\": \"Mix all ingredients for streusel together.Fill a greased small baking dish with apple mixture.Top with streusel and bake until streusel is brown.\",\n" +
                    "        \"ingredients\": [{\n" +
                    "            \"id\": 9003,\n" +
                    "            \"name\": \"apple\",\n" +
                    "            \"localizedName\": \"apple\",\n" +
                    "            \"image\": \"apple.jpg\"\n" +
                    "        }],\n" +
                    "        \"equipment\": [{\n" +
                    "            \"id\": 404646,\n" +
                    "            \"name\": \"baking pan\",\n" +
                    "            \"localizedName\": \"baking pan\",\n" +
                    "            \"image\": \"roasting-pan.jpg\"\n" +
                    "        }, {\n" +
                    "            \"id\": 404784,\n" +
                    "            \"name\": \"oven\",\n" +
                    "            \"localizedName\": \"oven\",\n" +
                    "            \"image\": \"oven.jpg\"\n" +
                    "        }]\n" +
                    "    }]\n" +
                    "}]";//findRecipeSteps(id);

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
        } catch (JSONException e) {
            throw new RuntimeException(e);
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