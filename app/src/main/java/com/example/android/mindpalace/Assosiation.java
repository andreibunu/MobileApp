package com.example.android.mindpalace;

public class Assosiation {
    private String word;
    private String meaning;
    private String room;
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Assosiation(String word, String meaning, String r) {
        this.word = word;
        this.meaning = meaning;
        this.room=r;
    }
}
