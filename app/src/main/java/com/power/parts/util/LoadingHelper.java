package com.power.parts.util;

import android.app.Dialog;
import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import static com.qmuiteam.qmui.widget.dialog.QMUITipDialog.Builder.ICON_TYPE_LOADING;

/**
 * Created by Administrator on 2018/11/6.
 * 加载提示辅助类
 */
public class LoadingHelper {
    public static Dialog loading(Context context,boolean cancle) {
        QMUITipDialog dialog = new QMUITipDialog.Builder(context)
                .setIconType(ICON_TYPE_LOADING)
                .setTipWord("请稍后...")
                .create(cancle);
        return dialog;
    }
}
