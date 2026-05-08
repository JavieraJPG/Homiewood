package com.homiwood.peliculas.dto;

import java.util.List;

public class TmdbSearchResponse {

    private List<TmdbItemDto> results;

    public List<TmdbItemDto> getResults() {
        return results;
    }

    public void setResults(List<TmdbItemDto> results) {
        this.results = results;
    }
}