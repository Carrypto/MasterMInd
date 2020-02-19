package pl.zhr.hak.mastermind;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Hint {
    private int firstHintNumber;
    private int secondHintNumber;
    private int thirdHintNumber;
    private int fourthHintNumber;
    private int blackPins;
    private int whitePins;

    public Hint(int firstHintNumber, int secondHintNumber, int thirdHintNumber, int fourthHintNumber, int blackPins, int whitePins) {
        this.firstHintNumber = firstHintNumber;
        this.secondHintNumber = secondHintNumber;
        this.thirdHintNumber = thirdHintNumber;
        this.fourthHintNumber = fourthHintNumber;
        this.blackPins = blackPins;
        this.whitePins = whitePins;
    }

    public int getFirstHintNumber() {
        return firstHintNumber;
    }

    public void setFirstHintNumber(int firstHintNumber) {
        this.firstHintNumber = firstHintNumber;
    }

    public int getSecondHintNumber() {
        return secondHintNumber;
    }

    public void setSecondHintNumber(int secondHintNumber) {
        this.secondHintNumber = secondHintNumber;
    }

    public int getThirdHintNumber() {
        return thirdHintNumber;
    }

    public void setThirdHintNumber(int thirdHintNumber) {
        this.thirdHintNumber = thirdHintNumber;
    }

    public int getFourthHintNumber() {
        return fourthHintNumber;
    }

    public void setFourthHintNumber(int fourthHintNumber) {
        this.fourthHintNumber = fourthHintNumber;
    }

    public int getBlackPins() {
        return blackPins;
    }

    public void setBlackPins(int blackPins) {
        this.blackPins = blackPins;
    }

    public int getWhitePins() {
        return whitePins;
    }

    public void setWhitePins(int whitePins) {
        this.whitePins = whitePins;
    }

}

