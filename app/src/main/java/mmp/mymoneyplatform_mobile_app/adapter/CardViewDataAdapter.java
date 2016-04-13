package mmp.mymoneyplatform_mobile_app.adapter;

/**
 * Created by K on 13/04/2016.
 * Utility class made to load the data into the cards.
 * Singleton design pattern applied.
 */
public class CardViewDataAdapter {

    private static CardViewDataAdapter instance = null;

    CardViewDataAdapter(){

    }

    public static CardViewDataAdapter getInstance(){
        if (instance == null){
            instance = new CardViewDataAdapter();
        }
        return instance;
    }

    public static void loadData(){

    }

}
