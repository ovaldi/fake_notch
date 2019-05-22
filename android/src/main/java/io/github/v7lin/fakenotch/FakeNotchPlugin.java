package io.github.v7lin.fakenotch;

import android.os.Build;
import android.text.TextUtils;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.github.v7lin.fakenotch.util.AndroidPNotchUtil;
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

    private static final String METHOD_HASCONVENTIONALNOTCH = "hasConventionalNotch";
    private static final String METHOD_HASSPECIALNOTCH = "hasSpecialNotch";
    private static final String METHOD_GETSPECIALNOTCHSIZE = "getSpecialNotchSize";

    private final Registrar registrar;

    public FakeNotchPlugin(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (TextUtils.equals(METHOD_HASCONVENTIONALNOTCH, call.method)) {
            hasConventionalNotch(call, result);
        } else if (TextUtils.equals(METHOD_HASSPECIALNOTCH, call.method)) {
            hasSpecialNotch(call, result);
        } else if (TextUtils.equals(METHOD_GETSPECIALNOTCHSIZE, call.method)) {
            getSpecialNotchSize(call, result);
        } else {
            result.notImplemented();
        }
    }

    private void hasConventionalNotch(MethodCall call, Result result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            result.success(AndroidPNotchUtil.hasNotch(registrar.activity().getWindow()));
        } else {
            result.success(false);
        }
    }

    private void hasSpecialNotch(MethodCall call, Result result) {
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

    private void getSpecialNotchSize(MethodCall call, Result result) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O || Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) {
            Manufacturer manufacturer = Manufacturer.recognizer();
            if (manufacturer == Manufacturer.HUAWEI) {
                result.success(HwNotchSizeUtil.getNotchSize(registrar.context()));
            } else if (manufacturer == Manufacturer.XIAOMI) {
                result.success(MiNotchSizeUtil.getNotchSize(registrar.context()));
            } else if (manufacturer == Manufacturer.OPPO) {
                result.success(OppoNotchSizeUtil.getNotchSize());
            } else if (manufacturer == Manufacturer.VIVO) {
                result.success(VivoNotchSizeUtil.getNotchSize(registrar.context()));
            } else {
                result.success(new int[]{0, 0});
            }
        } else {
            result.success(new int[]{0, 0});
        }
    }
}
