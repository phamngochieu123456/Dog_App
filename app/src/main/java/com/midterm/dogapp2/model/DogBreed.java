package com.midterm.dogapp2.model;

import com.google.gson.annotations.SerializedName;
import com.midterm.dogapp2.Height;
import com.midterm.dogapp2.Weight;

import java.io.Serializable;

public class DogBreed implements Serializable
{
    @SerializedName("id")
    private int id;

    @SerializedName("bred_for")
    private String BredFor;

    @SerializedName("name")
    private String name;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("origin")
    private String origin;

    @SerializedName("url")
    private String url;

    @SerializedName("breed_group")
    private String breedgroup;

    @SerializedName("temperament")
    private String temperament;

    @SerializedName("height")
    private Height height;

    @SerializedName("weight")
    private Weight weight;

    public DogBreed(int id, String bredFor, String name, String lifeSpan, String origin, String url, String breedgroup, String temperament, Height height, Weight weight) {
        this.id = id;
        BredFor = bredFor;
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
        this.breedgroup = breedgroup;
        this.temperament = temperament;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBredFor() {
        return BredFor;
    }

    public void setBredFor(String bredFor) {
        BredFor = bredFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBreedgroup() {
        return breedgroup;
    }

    public void setBreedgroup(String breedgroup) {
        this.breedgroup = breedgroup;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}

