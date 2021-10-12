package ru.ozon;

public enum MenuItems {
    TOPFASHION("/highlight/top-fashion/"),
    ACTION("/info/actions/"),
    LIVE("/live");

    private final String url;

    MenuItems(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
