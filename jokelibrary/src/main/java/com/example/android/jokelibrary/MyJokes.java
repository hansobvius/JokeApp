package com.example.android.jokelibrary;

import java.util.Random;

public class MyJokes {

    public String[] jokesList() {
        String[] jokes = {
                "Why don't cats like shopping online?\n\nThey prefer a cat-alogues.",
                "What do you call a cat with eight legs that likes to swim?\n\nAn octo-puss.",
                "Why did the cat join the Red Cross?\n\nShe wanted to be a first-aid kit!.",
                "I dressed my dog up as a cat for Halloween.\n\nNow he wont come when I call him.",
                "Why was the cat sitting on the computer?\n\nTo keep an eye on the mouse!",
                "What is the worst kind of cat?\n\nA cat-astrophe",
                "What is the difference between a cat and a comma?\n\nOne has claws at the end of its paws, while the other is a pause at the end of a clause",
                "What is a cats favorite color?\n\nThe Purrrrrple",
                "What did the cat on the smart phone say?\n\nCan you hear meow.",
                "What did the cat say when he lost his toys?\n\nYou got to be kitten me!!!"
        };
        return jokes;
    }

    public String getSomeJoke(){
        Random random = new Random();
        MyJokes myJokes = new MyJokes();
        int n = random.nextInt((myJokes.jokesList().length) - 1);
        String s = myJokes.jokesList()[n];
        return s;
    }
}
