package fr.o80.shortcutsdemo;

import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        // Report shortcut usage even if the user didn't use the shortcut
        reportShortcutUsage();

        // Will restore shortcuts when app is backed up on new device
        restoreWhenBackedUp();
    }

    private void restoreWhenBackedUp() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        if (shortcutManager.getDynamicShortcuts().size() == 0) {
            // Application restored. Need to re-publish dynamic shortcuts.
            if (shortcutManager.getPinnedShortcuts().size() > 0) {
                // Pinned shortcuts have been restored. Use
                // updateShortcuts(List) to make sure they
                // contain up-to-date information.
            }
        }
    }

    private void reportShortcutUsage() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        shortcutManager.reportShortcutUsed("home");
    }
}
