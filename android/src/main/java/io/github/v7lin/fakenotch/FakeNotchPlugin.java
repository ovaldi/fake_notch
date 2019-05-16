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
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O || Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) {
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
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O || Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) {
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
