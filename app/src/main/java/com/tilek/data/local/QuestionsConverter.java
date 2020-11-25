package com.tilek.data.local;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tilek.data.models.Question;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionsConverter {

    @TypeConverter
    public static ArrayList<Question> fromRaw(@Nullable String raw){
        if (raw == null) return null;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return gson.fromJson(raw,type);
    }

    @TypeConverter
    public static String toRaw(@Nullable ArrayList<Question> questions){
        if (questions == null) return null;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Question>>(){}.getType();
        return gson.toJson(questions,type);
    }

}
