package app.gui;

public class CurrentData {
    private static String search = "";

    public static void setSearch(String search) {
        CurrentData.search = search;
    }

    public static String getSearch() {
        return search;
    }
}
