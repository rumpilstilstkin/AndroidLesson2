package com.geekbrains.navigationdrawerexample;


public enum HomeScreen {
    FIRST(R.id.nav_gallery), SECOND(R.id.nav_slideshow), THIRD(R.id.nav_manage);

    HomeScreen(int itemId) {
        this.itemId = itemId;
    }

    private final int itemId;

    public int getItemId() {
        return itemId;
    }
}
