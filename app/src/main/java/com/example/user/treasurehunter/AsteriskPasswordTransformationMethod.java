package com.example.user.treasurehunter;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod
{
    /**
     *                  This method is used to hide a character when typing in a password
     * @param source    The characters from the source input when entering a password on the login screen
     * @param view      Uses a view for the currently displayed screen
     * @return          The transformed characters to an asterisk for the password
     */
    @Override
    public CharSequence getTransformation(CharSequence source, View view)
    {
        return new PasswordCharSequence(source);
    }

    /**
     *  This class changes the characters, that are entered into the password input on the login screen, into
     *  astrisks.
     */
    private class PasswordCharSequence implements CharSequence
    {
        private CharSequence mSource;
        public PasswordCharSequence(CharSequence source)
        {
            mSource = source; // Store char sequence
        }
        public char charAt(int index)
        {
            return '*'; // This is the important part
        }
        public int length()
        {
            return mSource.length(); // Return default
        }
        public CharSequence subSequence(int start, int end)
        {
            return mSource.subSequence(start, end); // Return default
        }
    }
}
