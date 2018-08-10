package com.example.administrator.myall.search;

import android.content.SearchRecentSuggestionsProvider;

public class SearchSuggestionSampleProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY="com.android.cbin.SearchSuggestionSampleProvider";
    public final static int MODE=DATABASE_MODE_QUERIES;

    public SearchSuggestionSampleProvider(){
        super();
        setupSuggestions(AUTHORITY, MODE);
    }
}
