package hoyn.eventbusl;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hoyn on 16/12/20.
 */

class LoggerL {
    private static final String TAG = "EventbusL";
    public static boolean isDebug = true;

    private static final String line_top = "╔══════════════════════════════════════════════════════════════════════════════════════════════╗";
    private static final String line_bottom = "╚══════════════════════════════════════════════════════════════════════════════════════════════╝";
    private static final String line_part = "║----------------------------------------------------------------------------------------------║";
    private static final String content_post = "║                                Post where : ";
    private static final String content_event = "║                              Invoked ! EventType : ";
    private static final String content_subscribe_where = "║            register : ";
    private static final String content_subscribe_method = "method name : ";

    private static StringBuilder postBuilder;
    private static String EventType = "";
    private static List<Map<String, String>> subscribeList = new ArrayList<>();

    /**
     * set the post Activity message
     *
     * @return
     */
    public static void setPostMessage(String eventType) {
        EventType = eventType;
        postBuilder = new StringBuilder();
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        postBuilder.append(content_post)
                .append("(")
                .append(stackTraceElement.getFileName())
                .append(":")
                .append(stackTraceElement.getLineNumber())
                .append(")");
    }

    public static void addSubcribe(String activity, String activityLog, String methodName, String eventType) {
        Map<String, String> subscribeInfo = new HashMap<>();
        subscribeInfo.put("activity", activity);
        subscribeInfo.put("activityLog", activityLog);
        subscribeInfo.put("methodName", methodName);
        subscribeInfo.put(methodName, eventType);
        subscribeList.add(subscribeInfo);
    }

    public static void removeSubcribe(String activity) {
        for (Map<String, String> subscribe : subscribeList) {
            if (activity.equals(subscribe.get("activity"))) {
                subscribeList.remove(subscribe);
                break;
            }
        }
    }

    public static void print() {
        if (isDebug) {
            Log.d(TAG, line_top);
            Log.d(TAG, fixFormat(postBuilder.toString()));
            Log.d(TAG, line_part);
            Log.d(TAG, fixFormat(content_event + EventType));
            Log.d(TAG, line_part);
            for (Map<String, String> subscribeInfo : subscribeList) {
                if (EventType.equals(subscribeInfo.get(subscribeInfo.get("methodName")))) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(content_subscribe_where)
                            .append(subscribeInfo.get("activityLog"))
                            .append("     ")
                            .append(content_subscribe_method)
                            .append(subscribeInfo.get("methodName"))
                            .append("()");
                    Log.d(TAG, fixFormat(sb.toString()));
                }
            }
            Log.d(TAG, line_bottom);
        }
    }

    private static String fixFormat(String info) {
        StringBuilder temp = new StringBuilder();
        temp.append(info);
        if (info.length() < line_top.length()) {
            int dif = line_top.length() - info.length();
            for (int i = 0; i < dif - 1; i++) {
                temp.append(" ");
            }
            temp.append("║");
        }
        return temp.toString();
    }
}
