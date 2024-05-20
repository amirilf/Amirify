package app.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Genre {

    Pop("#9ACD32", "/app/images/genres/Pop.jpg"),
    Rock("#4682B4", "/app/images/genres/Rock.jpg"),
    HipHop("#FFA500", "/app/images/genres/HipHop.jpg"),
    RnB("#8FBC8F", "/app/images/genres/RnB.jpg"),
    Soul("#BA55D3", "/app/images/genres/Soul.jpg"),
    Electronic("#5F9EA0", "/app/images/genres/Electronic.jpg"),
    Dance("#FF4500", "/app/images/genres/Dance.jpg"),
    Country("#7B68EE", "/app/images/genres/Country.jpg"),
    Jazz("#20B2AA", "/app/images/genres/Jazz.jpg"),
    Classical("#DA70D6", "/app/images/genres/Classical.jpg"),
    Reggae("#FF6347", "/app/images/genres/Reggae.jpg"),
    Metal("#32CD32", "/app/images/genres/Metal.jpg"),
    Indie("#8B4513", "/app/images/genres/Indie.jpg"),
    Alternative("#4682B4", "/app/images/genres/Alternative.jpg"),
    Blues("#DC143C", "/app/images/genres/Blues.jpg"),
    Latin("#00CED1", "/app/images/genres/Latin.jpg"),
    Folk("#FFD700", "/app/images/genres/Folk.jpg"),
    Punk("#FF69B4", "/app/images/genres/Punk.jpg"),
    Funk("#8A2BE2", "/app/images/genres/Funk.jpg"),
    Gospel("#3CB371", "/app/images/genres/Gospel.jpg"),
    Techno("#9370DB", "/app/images/genres/Techno.jpg"),
    Trance("#40E0D0", "/app/images/genres/Trance.jpg"),
    House("#FFB6C1", "/app/images/genres/House.jpg");

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
