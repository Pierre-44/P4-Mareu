package com.pierre44.mareu.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.pierre44.mareu.R;

import org.hamcrest.Matcher;


/**
 * Created by pmeignen on 03/06/2021.
 */

public class DeleteViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on delete button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.delete_button);
        button.performClick();
    }
}
