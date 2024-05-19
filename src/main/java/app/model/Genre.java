package app.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Genre {

    Pop("#FFA07A", "/app/images/genres/Pop.jpg"),
    Rock("#FFB6C1", "/app/images/genres/Rock.jpg"),
    HipHop("#D8BFD8", "/app/images/genres/HipHop.jpg"),
    RnB("#FFDAB9", "/app/images/genres/RnB.jpg"),
    Soul("#FFDEAD", "/app/images/genres/Soul.jpg"),
    Electronic("#E6E6FA", "/app/images/genres/Electronic.jpg"),
    Dance("#F0F8FF", "/app/images/genres/Dance.jpg"),
    Country("#FFC0CB", "/app/images/genres/Country.jpg"),
    Jazz("#F0E68C", "/app/images/genres/Jazz.jpg"),
    Classical("#D3FFCE", "/app/images/genres/Classical.jpg"),
    Reggae("#E6E6FA", "/app/images/genres/Reggae.jpg"),
    Metal("#FFD700", "/app/images/genres/Metal.jpg"),
    Indie("#FFA07A", "/app/images/genres/Indie.jpg"),
    Alternative("#E0FFFF", "/app/images/genres/Alternative.jpg"),
    Blues("#ADD8E6", "/app/images/genres/Blues.jpg"),
    Latin("#FFC0CB", "/app/images/genres/Latin.jpg"),
    Folk("#D8BFD8", "/app/images/genres/Folk.jpg"),
    Punk("#FFA07A", "/app/images/genres/Punk.jpg"),
    Funk("#FFB6C1", "/app/images/genres/Funk.jpg"),
    Gospel("#FFE4E1", "/app/images/genres/Gospel.jpg"),
    Techno("#F0E68C", "/app/images/genres/Techno.jpg"),
    Trance("#FFDEAD", "/app/images/genres/Trance.jpg"),
    House("#F0FFF0", "/app/images/genres/House.jpg");

    private final String colorId;
    private final String bgImagePath;

    Genre(String colorId, String bgImagePath) {
        this.colorId = colorId;
        this.bgImagePath = bgImagePath;
    }

    public String getColorId() {
        return colorId;
    }

    public String getImagePath() {
        return bgImagePath;
    }

    public static ArrayList<String> getGenreNames() {
        return new ArrayList<>(Arrays.asList(
                Arrays.stream(Genre.values())
                        .map(Genre::name)
                        .toArray(String[]::new)));
    }
}
