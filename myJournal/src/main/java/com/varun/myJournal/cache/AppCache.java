package com.varun.myJournal.cache;

import com.varun.myJournal.entity.ConfigJournalAppEntity;
import com.varun.myJournal.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{

        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;


    public Map<String, String> appCacheMap;

    @PostConstruct
    public void init(){

        //appCacheMap=null;
        appCacheMap = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity: all)
        {
            appCacheMap.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }

    }
}

//@PostConstruct
//whenever AppCache bean is created by Spring, the @PostConstruct initializes the init() method.
//the init() method will load the collection "config_journal_app" in the appCache map.

//NOTE: The main purpose of this is that we are not using Mongo here to access the collection.
//we are using AppCache.

//Now we create a collection in mongoDB called "config_journal_app".
//This collection will have "configurations" that are key->value pairs.
//If you want to store something that needs change frequently(like versioning, or api)
// we can store those configs in this collection "config_journal_app".



//    public Map<String, String> appCacheMap = new HashMap<>();
// we cannot initialize appCacheMap like above
//because the map only initializes once the bean is created, and any further changes
//to the (key or value) will not re-initialize the appCacheMap.
//So to avoid this, we create appCacheMap instance inside init() method.

//So when ever there are changes made to (key or value)(i.e inside "config_journal_app" collection)
// this init() method can be called from a different method to re-initialize the map.
//this is done in AdminController using ("clear-app-cache")
//So, whenever this api("clear-app-cache") is called , the appCacheMap will be re-initialized
//Instead of loading entire application.


//Eg: suppose we change the key(i.e api name) value inside the collection.
//In order to apply the changes, we need to re-initialize the code by calling init()
//method from a custom defined api "clear-app-cache" in AdminController

//Finally, by simply hitting this "clear-app-cache" in AdminController, we can re-initialize
//the appCacheMap.
//otherwise we have to run the entire application to re-initialize the appCacheMap
//which is not good in the case of a large application