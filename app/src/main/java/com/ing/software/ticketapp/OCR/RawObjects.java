package com.ing.software.ticketapp.OCR;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.Log;

import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store objects detected.
 * Contains useful methods and variables that TextBlock does not provide.
 * @author Michelon
 */
class RawBlock {

    private List<RawText> rawTexts = new ArrayList<>();
    private List<? extends Text> textComponents;
    private RectF rectF;
    private RawImage rawImage;
    private String grid;

    /**
     * Constructor, parameters must not be null
     * @param textBlock source TextBlock
     * @param imageMod source image
     * @param grid grid from ProbGrid.gridMap
     */
    RawBlock(TextBlock textBlock, RawImage imageMod, String grid) {
        rectF = new RectF(textBlock.getBoundingBox());
        textComponents = textBlock.getComponents();
        this.rawImage = imageMod;
        this.grid = grid;
        initialize();
    }

    /**
     * Populates this block with its Rawtexts
     */
    private void initialize() {
        for (Text currentText : textComponents) {
            rawTexts.add(new RawText(currentText));
        }
    }

    /**
     * Search string in block, only first occurrence is returned (top -> bottom, left -> right)
     * @param string string to search
     * @return RawText containing the string, null if nothing found
     */
    RawText bruteSearch(String string) {
        for (RawText rawText : rawTexts) {
            if (rawText.bruteSearch(string))
                return rawText;
        }
        return null;
    }

    /**
     * Search string in block, all occurrences are returned (top -> bottom, left -> right)
     * @param string string to search
     * @return list of RawText containing the string, null if nothing found
     */
    List<RawText> bruteSearchContinuous(String string) {
        List<RawText> rawTextList = new ArrayList<>();
        for (RawText rawText : rawTexts) {
            if (rawText.bruteSearch(string))
                rawTextList.add(rawText);
        }
        if (rawTextList.size()>0)
            return rawTextList;
        else
            return null;
    }

    /**
     * Find all Rawtexts inside chosen rect with an error of 'percent' (on width and height of chosen rect)
     * @param rect rect where you want to find texts
     * @param percent error accepted on chosen rect
     * @return list of RawText in chosen rect, null if nothing found
     */
    List<RawText> findByPosition(RectF rect, int percent) {
        List<RawText> rawTextList = new ArrayList<>();
        RectF newRect = extendRect(rect, percent);
        for (RawText rawText : rawTexts) {
            if (rawText.isInside(newRect)) {
                rawTextList.add(rawText);
                Log.d("OcrAnalyzer", "Found target rect: " + rawText.getDetection());
            }
        }
        if (rawTextList.size()>0)
            return rawTextList;
        else
            return null;
    }

    /**
     * Create a new rect extending source rect with chosen percentage (on width and height of chosen rect)
     * Note: Min value for top and left is 0
     * @param rect source rect
     * @param percent chosen percentage
     * @return new extended rectangle
     */
    private RectF extendRect(RectF rect, int percent) {
        Log.d("RawObjects.extendRect","Source rect: left " + rect.left + " top: "
                + rect.top + " right: " + rect.right + " bottom: " + rect.bottom);
        float extendedHeight = rect.height()*percent/100;
        float extendedWidth = rect.width()*percent/100;
        float left = rect.left - extendedWidth/2;
        if (left<0)
            left = 0;
        float top = rect.top - extendedHeight/2;
        if (top < 0)
            top = 0;
        float right = rect.right + extendedWidth/2;
        float bottom = rect.bottom + extendedHeight/2;
        Log.d("RawObjects.extendRect","Extended rect: left " + left + " top: " + top
                + " right: " + right + " bottom: " + bottom);
        return new RectF(left, top, right, bottom);
    }

    class RawText {

        private RectF rectText;
        private Text text;
        /**
         * Constructor
         * @param text current Text inside TextBlock
         */
        RawText(Text text) {
            rectText = new RectF(text.getBoundingBox());
            this.text = text;
        }

        /**
         * @return string contained in this Text
         */
        String getDetection() {
            return text.getValue();
        }

        /**
         * @return rect of this Text
         */
        RectF getRect() {
            return rectText;
        }

        /**
         * @return rawImage of this Text
         */
        RawImage getRawImage() {
            return rawImage;
        }

        /**
         * Search string in text
         * @param string string to search
         * @return true if string is present
         */
        private boolean bruteSearch(String string) {
            //Here Euristic search will be implemented
            if (text.getValue().contains(string))
                return true;
            else
                return false;
        }

        /**
         * Check if this text is inside chosen rect
         * @param rect target rect that could contain this text
         * @return true if is inside
         */
        private boolean isInside(RectF rect) {
            return rect.contains(rectText);
        }
    }
}

class RawImage {

    private int height;
    private int width;

    RawImage(Bitmap bitmap) {
        height = bitmap.getHeight();
        width = bitmap.getWidth();
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }
}
