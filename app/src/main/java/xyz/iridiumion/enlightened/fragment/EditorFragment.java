package xyz.iridiumion.enlightened.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import xyz.iridiumion.enlightened.R;
import xyz.iridiumion.enlightened.editor.IridiumHighlightingEditorJ;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditorFragment extends Fragment {
    public static final String TAG = "EditorFragment";

    private InputMethodManager imm;
    private IridiumHighlightingEditorJ codeEditor;
    private int yOffset;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle state) {
        Activity activity;

        if ((activity = getActivity()) == null)
            return null;

        View view;

        if ((imm = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE)) == null ||
                (view = inflater.inflate(
                        R.layout.fragment_editor,
                        container,
                        false)) == null ||
                (codeEditor = (IridiumHighlightingEditorJ) view.findViewById(
                        R.id.editor)) == null) {
            activity.finish();
            return null;
        }

        try {
            codeEditor.setOnTextChangedListener(
                    (IridiumHighlightingEditorJ.OnTextChangedListener) activity);
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " must implement " +
                            "ShaderEditor.OnTextChangedListener");
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //updateToPreferences();
    }

    public boolean hasErrorLine() {
        return codeEditor.hasErrorLine();
    }

    public void clearError() {
        codeEditor.setErrorLine(0);
    }

    public void updateHighlighting() {
        codeEditor.updateHighlighting();
    }

    public void showError(String infoLog) {
        Activity activity = getActivity();

        if (activity == null)
            return;

        /*
        InfoLog.parse(infoLog);
        codeEditor.setErrorLine(InfoLog.getErrorLine());
        updateHighlighting();

        Toast errorToast = Toast.makeText(
                activity,
                InfoLog.getMessage(),
                Toast.LENGTH_SHORT);

        errorToast.setGravity(
                Gravity.TOP | Gravity.CENTER_HORIZONTAL,
                0,
                getYOffset(activity));
        errorToast.show();
        */
    }

    public boolean isModified() {
        return codeEditor.isModified();
    }

    public String getText() {
        return codeEditor.getCleanText();
    }

    public void setText(String text) {
        clearError();
        codeEditor.setTextHighlighted(text);
    }

    public void insertTab() {
        codeEditor.insertTab();
    }

    public void addUniform(String name) {
        codeEditor.addUniform(name);
    }

    public boolean isCodeVisible() {
        return codeEditor.getVisibility() == View.VISIBLE;
    }

    public boolean toggleCode() {
        boolean visible = isCodeVisible();

        codeEditor.setVisibility(visible ?
                View.GONE :
                View.VISIBLE);

        if (visible)
            imm.hideSoftInputFromWindow(
                    codeEditor.getWindowToken(),
                    0);

        return visible;
    }

    /*
    private void updateToPreferences() {
        Preferences preferences =
                ShaderEditorApplication.preferences;

        codeEditor.setUpdateDelay(
                preferences.getUpdateDelay());

        codeEditor.setTextSize(
                android.util.TypedValue.COMPLEX_UNIT_SP,
                preferences.getTextSize());

        codeEditor.setTabWidth(
                preferences.getTabWidth());
    }
    */
    private int getYOffset(Activity activity) {
        if (yOffset == 0) {
            float dp = getResources().getDisplayMetrics().density;

            try {
                ActionBar actionBar = ((AppCompatActivity) activity)
                        .getSupportActionBar();

                if (actionBar != null)
                    yOffset = actionBar.getHeight();
            } catch (ClassCastException e) {
                yOffset = Math.round(48f * dp);
            }

            yOffset += Math.round(16f * dp);
        }

        return yOffset;
    }

    public IridiumHighlightingEditorJ getEditor() {
        return codeEditor;
    }
}
