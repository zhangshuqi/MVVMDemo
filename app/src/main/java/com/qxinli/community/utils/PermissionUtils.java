package com.qxinli.community.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 需要添加依赖：
 * compile 'com.mylhyl:acp:1.0.0'
 * <p>
 * Created by Administrator on 2016/6/15 0015.
 */
public class PermissionUtils {
    public static final int SETTINGS_REQ_CODE = 10086;
    private static boolean isCancle = false;
    private static String permissionDesc = "APP需要获取一些权限信息";

    /**
     * group:android.permission-group.CALENDAR
     * permission:android.permission.READ_CALENDAR
     * permission:android.permission.WRITE_CALENDAR
     */
    public static void askCalendar(Object object, PermissionListener listener) {
        performRequestPermissions(object, permissionDesc, 2, listener, isCancle, Manifest.permission.READ_CALENDAR);

    }

    /**
     * group:android.permission-group.CAMERA
     * permission:android.permission.CAMERA
     */
    public static void askCamera(Object object, PermissionListener listener) {
        isCancle = true;
        permissionDesc = "选择头像需要您设置权限,否则选择不了头像" + "\n" + "请点击申请,在应用信息界面的权限管理中同意\"读写手机存储的权限\"";
        performRequestPermissions(object, permissionDesc, 2, listener, isCancle, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    public static void askExternalStorage(Object object, PermissionListener listener) {
        isCancle = true;
        permissionDesc = "选择头像需要您设置权限,否则选择不了头像" + "\n" + "请点击申请,在应用信息界面的权限管理中同意\"读写手机存储的权限\"";
        performRequestPermissions(object, permissionDesc, 2, listener, isCancle, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * group:android.permission-group.PHONE
     * <p>
     * permission:android.permission.READ_CALL_LOG
     * permission:android.permission.READ_PHONE_STATE
     * permission:android.permission.CALL_PHONE
     * permission:android.permission.WRITE_CALL_LOG
     * permission:android.permission.USE_SIP
     * permission:android.permission.PROCESS_OUTGOING_CALLS
     * permission:com.android.voicemail.permission.ADD_VOICEMAIL
     */
    public static void askPhone(Object object, PermissionListener listener) {
        performRequestPermissions(object, permissionDesc, 2, listener, isCancle, Manifest.permission.READ_PHONE_STATE);
    }

    public static void askCallPhone(Object object, PermissionListener listener) {
        performRequestPermissions(object, permissionDesc, 2, listener, isCancle, Manifest.permission.CALL_PHONE);
    }

    /**
     * group:android.permission-group.SMS
     * <p>
     * permission:android.permission.READ_SMS
     * permission:android.permission.RECEIVE_WAP_PUSH
     * permission:android.permission.RECEIVE_MMS
     * permission:android.permission.RECEIVE_SMS
     * permission:android.permission.SEND_SMS
     * permission:android.permission.READ_CELL_BROADCASTS
     */
    public static void askSms(Object object, PermissionListener listener) {
        performRequestPermissions(object, permissionDesc, 2, listener, isCancle, Manifest.permission.SEND_SMS);
    }

    /**
     * group:android.permission-group.LOCATION
     * permission:android.permission.ACCESS_FINE_LOCATION
     * permission:android.permission.ACCESS_COARSE_LOCATION
     */
    public static void askLocationInfo(Object object, PermissionListener listener) {
        isCancle = true;
        performRequestPermissions(object, permissionDesc, 1, listener, isCancle, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * group:android.permission-group.MICROPHONE
     * permission:android.permission.RECORD_AUDIO
     */
    public static void askRecord(Object object, PermissionListener listener) {
        performRequestPermissions(object, permissionDesc, 1, listener, isCancle, Manifest.permission.RECORD_AUDIO);
    }

    /**
     * group:android.permission-group.CONTACTS
     * permission:android.permission.WRITE_CONTACTS
     * permission:android.permission.GET_ACCOUNTS
     * permission:android.permission.READ_CONTACTS
     */
    public static void askContacts(Object object, PermissionListener listener) {
        performRequestPermissions(object, permissionDesc, 1, listener, isCancle, Manifest.permission.READ_CONTACTS);
    }

    public interface PermissionListener {
        void onGranted();

        void onAllGranted();

        void onDenied(List<String> permissions, String permissionDesc, boolean isCancle, int requestCode);
    }

    private static PermissionListener mListener;

    private static int mRequestCode;

    /**
     * @param desc        首次申请权限被拒绝后再次申请给用户的描述提示
     * @param permissions 要申请的权限数组
     * @param requestCode 申请标记值
     * @param listener    实现的接口
     * @param isCancle    弹出的dialog是否能够取消(强制申请)
     * @param object      兼容fragment
     */
    protected static void performRequestPermissions(Object object, String desc, int requestCode, PermissionListener listener, boolean isCancle, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        mRequestCode = requestCode;
        mListener = listener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (object == null) {
                throw new IllegalArgumentException("object parameters must be the activity or fragments ");
            }
      /*      boolean shouldShowRationale = false;
            for (String perm : permissions) {
                shouldShowRationale =
                        shouldShowRationale || shouldShowRequestPermissionRationale(object, perm);
            }*/
            // 检查是否声明了权限
            requestPermissions(object, desc, permissions, requestCode, isCancle);
        } else {
            if (mListener != null) {
                mListener.onGranted();
            }
        }
    }

    /**
     * 申请权限前判断是否需要声明
     *
     * @param desc
     * @param permissions
     * @param requestCode
     * @param isCancle
     */
    @TargetApi(23)
    private static void requestPermissions(Object object, String desc, String[] permissions, int requestCode, boolean isCancle) {
        boolean shouldShowRationale = false;
        for (String perm : permissions) {
            shouldShowRationale =
                    shouldShowRationale || shouldShowRequestPermissionRationale(object, perm);
        }
        if (shouldShowRationale) {// 需要再次声明
            showRationaleDialog(getActivity(object), desc, permissions, requestCode, isCancle);
        } else {
            requestEachPermissions(object, permissions, requestCode);
        }
    }

    @TargetApi(23)
    private static void requestEachPermissions(Object object, String[] permissions, int requestCode) {

        if (object instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) object, permissions, requestCode);
        } else if (object instanceof Fragment) {
            ((Fragment) object).requestPermissions(permissions, requestCode);
        } else if (object instanceof android.app.Fragment) {
            ((android.app.Fragment) object).requestPermissions(permissions, requestCode);
        }
    }

    /**
     * 弹出声明的 Dialog
     *
     * @param activity
     * @param desc
     * @param permissions
     * @param requestCode
     */
    private static void showRationaleDialog(final Activity activity, String desc, final String[] permissions, final int requestCode, boolean isCancle) {
        //  DialogUtils.showRequestPermissionDialog(activity,desc,requestCode,isCancle,permissions);
    }


    /**
     * 再次申请权限时，是否需要声明
     *
     * @param object
     * @param perm
     * @return
     */
    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(Object object, String perm) {
        if (object instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) object, perm);
        } else if (object instanceof Fragment) {
            return ((Fragment) object).shouldShowRequestPermissionRationale(perm);
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object).shouldShowRequestPermissionRationale(perm);
        } else {
            return false;
        }
    }

    /**
     * 检察每个权限是否申请
     *
     * @param activity
     * @param permissions
     * @return true 需要申请权限,false 已申请权限
     */
    public static boolean checkEachSelfPermission(Activity activity, String[] permissions) {
        boolean flag = false;
        for (String permission : permissions) {
            int check = ContextCompat.checkSelfPermission(activity, permission);
            boolean b = check != PackageManager.PERMISSION_GRANTED;
            if (b) {
                flag = true;
                break;
            }
        }
/*      AppOpsManager appOpsManager = (AppOpsManager)activity. getSystemService(Context.APP_OPS_SERVICE);
        int checkOp = appOpsManager.checkOp(AppOpsManager.OPSTR_FINE_LOCATION, Process.myUid(), activity.getPackageName());
        if (checkOp == AppOpsManager.MODE_IGNORED) {
            // 权限被拒绝了 .
        }*/

        return flag;
    }

    /**
     * 申请权限结果的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        ArrayList<String> granted = new ArrayList<>();
        ArrayList<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }
        if (!granted.isEmpty()) {
            if (mListener != null) {
                mListener.onGranted();
            }
        }
        if (!denied.isEmpty()) {
            if (mListener != null) {
                mListener.onDenied(denied, permissionDesc, isCancle, requestCode);
            }
        }
        if (!granted.isEmpty() && denied.isEmpty()) {
            if (mListener != null) {
                mListener.onAllGranted();
            }
        }
    }

    @TargetApi(11)
    private static Activity getActivity(Object object) {
        if (object instanceof Activity) {
            return ((Activity) object);
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof android.app.Fragment) {
            return ((android.app.Fragment) object).getActivity();
        } else {
            return null;
        }
    }

    public static boolean checkDeniedPermissionsNeverAskAgain(final Object object,
                                                              String rationale,
                                                              String positiveButton,
                                                              String negativeButton,
                                                              @Nullable DialogInterface.OnClickListener negativeButtonOnClickListener,
                                                              List<String> deniedPerms) {
        boolean shouldShowRationale;
        for (String perm : deniedPerms) {
            shouldShowRationale = shouldShowRequestPermissionRationale(object, perm);
            if (!shouldShowRationale) {
                final Activity activity = getActivity(object);
                if (activity == null) {
                    throw new IllegalArgumentException("object parameters must be the activity or fragments ");
                }
                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setMessage(rationale)
                        .setCancelable(false)
                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                intent.setData(uri);
                                startAppSettingsScreen(object, intent);
                            }
                        })
                        .setNegativeButton(negativeButton, negativeButtonOnClickListener)
                        .create();
                dialog.show();
                return true;
            }
        }
        return false;
    }

    @TargetApi(11)
    private static void startAppSettingsScreen(Object object,
                                               Intent intent) {
        if (object instanceof Activity) {
            ((Activity) object).startActivityForResult(intent, SETTINGS_REQ_CODE);
        } else if (object instanceof Fragment) {
            ((Fragment) object).startActivityForResult(intent, SETTINGS_REQ_CODE);
        } else if (object instanceof android.app.Fragment) {
            ((android.app.Fragment) object).startActivityForResult(intent, SETTINGS_REQ_CODE);
        }
    }
}
