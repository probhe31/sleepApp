package com.probe31.probe.sleepyapp.MODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("results")
    @Expose
    private List<Friend> results;

    public List<Friend> getResults() {
        return results;
    }

    public void setResults(List<Friend> results) {
        this.results = results;
    }
}
