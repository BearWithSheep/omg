package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

  private Button btnMealManagemaent;

  private ListView lvMainMeals;

  private DatabaseHandler databaseHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnMealManagemaent = findViewById(R.id.btn_meal_manage);
    lvMainMeals = findViewById(R.id.lv_main_meals);

    databaseHandler = new DatabaseHandler(this);
    databaseHandler.open();

    View.OnClickListener listener = new View.OnClickListener() {
     @Override
      public void onClick(View v) {
          Intent intent = new Intent(MainActivity.this, mealmanageActivity.class);
          startActivity(intent);
      }
    };
    btnMealManagemaent.setOnClickListener(listener);
    showAllMeals();
  }

  @Override
  protected void onResume(){
    super.onResume();
    showAllMeals();
  }

  private  void  showAllMeals(){
    Cursor cursor = databaseHandler.getAllMeals();
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(
      this,
      R.layout.meal_list_item,
      cursor,
      new String[] {"name","description","price"},
      new int []{R.id.tv_meal_name,R.id.tv_meal_descrption,R.id.tv_meal_price},
      0
    );
    lvMainMeals.setAdapter(adapter);
  }
}
