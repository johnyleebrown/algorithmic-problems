package Basics;

import java.util.Arrays;

public class SocialMedia {
    private String[] data;

    public SocialMedia() {
        this.data = new String[]{
                "Gold is here",
                "Buy Gold",
                "Bitgolcoind"
        };
    }

    public String[] getData() {
        return data;
    }

    public static void main(String[] args) {
        SocialMedia socialMedia = new SocialMedia();
        String gold = "(?i:.*GOLD.*)";
        Arrays.stream(socialMedia.getData()).filter(data -> data.matches(gold)).forEach(System.out::println);
    }
}
