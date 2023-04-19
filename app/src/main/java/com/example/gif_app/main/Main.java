package com.example.gif_app.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Object.Datum;
import com.Object.Response2;
import com.example.gif_app.R;
import com.example.gif_app.api.Retrofit_Item;
import com.example.gif_app.api.Retrofit_Caller;
import com.example.gif_app.DataBase.GIF_DB;
import com.example.gif_app.main.RV_Adapter.Gif_Adapter;
import com.example.gif_app.main.RV_Adapter.Gif_Adapter_2;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class Main
        extends AppCompatActivity
        implements Gif_Adapter.OnInsertListener {

    private static final String api_key = "lkrJDzDTVXJtbt8lPVphAMKC05nGDLji";
    String limit = "50";
    String rating = "R";
    String offset = "0";
    int SpanCount = 2;
    Retrofit retrofit;

    RecyclerView recycler_view;
    private GIF_DB DataBase;
    private EditText search_keyword;
    Button button_from_database; // кнопка для загрузки базы данных
    Button button_from_online; // кнопка загрузки онлайн


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button_from_database = findViewById(R.id.load_local);
        button_from_online = findViewById(R.id.load_online);
        search_keyword = findViewById(R.id.search_input);
        recycler_view = findViewById(R.id.recycler_view_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SpanCount);
        gridLayoutManager.setItemPrefetchEnabled(true);
        gridLayoutManager.setInitialPrefetchItemCount(6);

        recycler_view.setLayoutManager(gridLayoutManager);

        DataBase = GIF_DB.getDatabase(this);
        retrofit = Retrofit_Item.getRetrofit();

        View.OnClickListener click_button_load_db = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gif_Adapter_2 recycler_view_adapter = new Gif_Adapter_2(this, DataBase.getGifDao().LoadAll());
                recycler_view.setAdapter(recycler_view_adapter);
            }
        };

        button_from_database.setOnClickListener(click_button_load_db);


        View.OnClickListener click_button_load_online = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = search_keyword.getText().toString();

                retrofit.create(Retrofit_Caller.class)
                        .getSearchPhotos(api_key, q, limit, offset, rating)
                        .enqueue(new Callback<Response2>() {

                            @Override
                            public void onResponse(Call<Response2> call, Response<Response2> response) {
                                response.body();
                                //  Log.e("test_request", String.valueOf((((Response2) response.body()).getPagination().getCount()))); Лог для проверки запрсоа
                                Gif_Adapter gif_adapter = new Gif_Adapter(this, response.body().getData());
                                gif_adapter.setOnInsertListener(Main.this);
                                recycler_view.setAdapter(gif_adapter);

                            }

                            @Override
                            public void onFailure(Call<Response2> call, Throwable t) {
                            }
                        });
            }
        };
        button_from_online.setOnClickListener(click_button_load_online);
    }




    @Override
    public void onInsert(Datum datum) {
        DataBase.getGifDao().insertGif(datum);
    }

    //Хотел подумать насчет intenta, чтобы при перевороте на ландшафтный дизайн перезагружались загруженные gif-ки,
    //но потом подумал насчет разных устройств и специфики отображения на них, так как в Object есть куча форматов
    //под разные разрешения (фиксированная длина/ширина, сжатые, полноразмерные, mp4 формат есть(для лучшего качества) и т.д.
}