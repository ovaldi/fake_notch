package io.github.v7lin.fakenotch;

import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.flutter.plugin.common.JSONUtil;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.github.v7lin.fakenotch.util.HwNotchSizeUtil;
import io.github.v7lin.fakenotch.util.MiNotchSizeUtil;
import io.github.v7lin.fakenotch.util.OppoNotchSizeUtil;
import io.github.v7lin.fakenotch.util.VivoNotchSizeUtil;
import manufacturer.android.Manufacturer;

/**
 * FakeNotchPlugin
 */
public class FakeNotchPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "v7lin.github.io/fake_notch");
        channel.setMethodCallHandler(new FakeNotchPlugin(registrar));
    }

    private static final String METHOD_HASNOTCH = "hasNotch";
    private static final String METHOD_GETNOTCHRECTS = "getNotchRects";

    private final Registrar registrar;

    public FakeNotchPlugin(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (TextUtils.equals(METHOD_HASNOTCH, call.method)) {
            hasNotch(call, result);
        } else if (TextUtils.equals(METHOD_GETNOTCHRECTS, call.method)) {
            getNotchRects(call, result);
        } else {
            result.notImplemented();
        }
    }

    private void hasNotch(MethodCall call, Result result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            View decorView = registrar.activity().getWindow().getDecorView();
            DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
            List<Rect> notchRects = displayCutout.getBoundingRects();
            result.success(notchRects != null && !notchRects.isEmpty());
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            Manufacturer manufacturer = Manufacturer.recognizer();
            if (manufacturer == Manufacturer.HUAWEI) {
                result.success(HwNotchSizeUtil.hasNotch(registrar.context()));
            } else if (manufacturer == Manufacturer.XIAOMI) {
                result.success(MiNotchSizeUtil.hasNotch());
            } else if (manufacturer == Manufacturer.OPPO) {
                result.success(OppoNotchSizeUtil.hasNotch(registrar.context()));
            } else if (manufacturer == Manufacturer.VIVO) {
                result.success(VivoNotchSizeUtil.hasNotch(registrar.context()));
            } else {
                result.success(false);
            }
        } else {
            result.success(false);
        }
    }

    private void getNotchRects(MethodCall call, Result result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            View decorView = registrar.activity().getWindow().getDecorView();
            DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
            List<Rect> notchRects = displayCutout.getBoundingRects();
            if (notchRects != null && !notchRects.isEmpty()) {
                List<Map<String, Object>> ret = new ArrayList<>();
                for (Rect notchRect : notchRects) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("left", notchRect.left);
                    map.put("top", notchRect.top);
                    map.put("right", notchRect.right);
                    map.put("bottom", notchRect.bottom);
                    ret.add(map);
                }
                result.success(ret);
            } else {
                result.success(Collections.<String>emptyList());
            }
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            Manufacturer manufacturer = Manufacturer.recognizer();
            if (manufacturer == Manufacturer.HUAWEI) {
            } else if (manufacturer == Manufacturer.XIAOMI) {
            } else if (manufacturer == Manufacturer.OPPO) {
            } else if (manufacturer == Manufacturer.VIVO) {
            } else {
                result.success(Collections.<String>emptyList());
            }
        } else {
            result.success(Collections.<String>emptyList());
        }
    }
}
