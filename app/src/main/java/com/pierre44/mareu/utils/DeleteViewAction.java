package com.pierre44.mareu.utils;


/**
 * Created by pmeignen on 03/06/2021.
 */
/**
public class DeleteViewAction implements ViewAction{
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.delete_button);
        // Maybe check for null
        button.performClick();
    }
}
**/