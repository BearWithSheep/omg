package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class mealmanageActivity extends AppCompatActivity {

  private EditText etMealName;
  private EditText etMealDescription;
  private EditText etMealPrice;
  private Button  btnAddMeal;
  private ListView lvMeal;
  private DatabaseHandler databaseHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mealmanage);

    etMealName = findViewById(R.id.et_meal_name);
    etMealDescription = findViewById(R.id.et_meal_description);
    etMealPrice = findViewById(R.id.et_meal_price);
    btnAddMeal = findViewById(R.id.btn_add_meal);
    lvMeal = findViewById(R.id.lv_all_meals);
    databaseHandler = new DatabaseHandler(this);
    databaseHandler.open();

    View.OnClickListener listener = new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        String mealName = etMealName.getText().toString();
        String mealDescription = etMealDescription.getText().toString();
        int mealPrice = Integer.parseInt(etMealPrice.getText().toString());
        databaseHandler.addMeal(mealName, mealDescription, mealPrice);
        showAllMeals();
      }
    };
    btnAddMeal.setOnClickListener(listener);
    showAllMeals();

    AdapterView.OnItemClickListener listviewItemClickListener = new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mealmanageActivity.this, position + " is select", Toast.LENGTH_SHORT).show();
      }
    };
    lvMeal.setOnItemClickListener(listviewItemClickListener);
  }

  private void showAllMeals() {

    Cursor cursor = databaseHandler.getAllMeals();
    SimpleCursorAdapter   adapter = new SimpleCursorAdapter(
      mealmanageActivity.this,
      android.R.layout.simple_list_item_2,
      cursor,
      new String[] {"name","price"},
      new int []{android.R.id.text1,android.R.id.text2},
      0
    );
    lvMeal.setAdapter(adapter);
  }
}
