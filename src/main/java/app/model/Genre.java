package app.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Genre {
    Pop,
    Rock,
    HipHop,
    RnB,
    Soul,
    Electronic,
    Dance,
    Country,
    Jazz,
    Classical,
    Reggae,
    Metal,
    Indie,
    Alternative,
    Blues,
    Latin,
    Folk,
    Punk,
    Funk,
    Gospel,
    Techno,
    Trance,
    House;

    public static ArrayList<String> getGenreNames() {
        return new ArrayList<>(Arrays.asList(
                Arrays.stream(Genre.values())
                        .map(Genre::name)
                        .toArray(String[]::new)));
    }
}
