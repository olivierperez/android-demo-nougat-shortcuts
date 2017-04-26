package fr.o80.shortcutsdemo;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    public static final String SHORTCUT_ID = "SecondShortcut";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.button).setOnClickListener(v -> {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            List<ShortcutInfo> dynamicShortcuts = shortcutManager.getDynamicShortcuts();

            if (dynamicShortcuts.isEmpty()) {
                shortcutManager.setDynamicShortcuts(createShortcut());
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();

            } else {
                shortcutManager.removeDynamicShortcuts(Collections.singletonList(SHORTCUT_ID));
                Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private List<ShortcutInfo> createShortcut() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, SHORTCUT_ID)
                .setShortLabel(getString(R.string.shortcut_second_short))
                .setLongLabel(getString(R.string.shortcut_second_long))
                .setDisabledMessage(getString(R.string.shortcut_second_disabled))
                .setIcon(Icon.createWithResource(this, R.drawable.ic_second))
                .setIntent(intent)
                .build();

        return Collections.singletonList(shortcut);
    }
}
