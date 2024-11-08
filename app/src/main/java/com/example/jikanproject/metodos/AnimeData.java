package com.example.jikanproject.metodos;

public class AnimeData {
    private int mal_id;
    private String title;
    private int episodes;
    private Image images;

    // Cambiado el método para que siga la convención camelCase
    public int getMalId() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    // Clase interna para representar las imágenes JPG
    public static class Image {
        private Jpg jpg;

        public Jpg getJpg() {
            return jpg;
        }

        public void setJpg(Jpg jpg) {
            this.jpg = jpg;
        }

        public static class Jpg {
            private String image_url;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }
    }
}
