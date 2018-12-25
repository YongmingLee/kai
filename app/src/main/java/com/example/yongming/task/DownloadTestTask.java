package com.example.yongming.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/*
* 异步任务
* 模板参数：第一个，传入的参数类型。 第二个，进度回调函数中进度的类型。第三个，任务完成返回值类型，根据这个返回值判断任务是否成功。
* */
public class DownloadTestTask extends AsyncTask <Void, Integer, Integer> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // ！主线程，初始化UI操作
        Log.i("dtt", "开始了 " + Thread.currentThread().getName());
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        // ！主线程，做一些更新操作
        Log.i("dtt", "更新进度 " + Thread.currentThread().getName() + " " + values[0]);
    }

    // important
    @Override
    protected Integer doInBackground(Void... voids) {

        // ！！子线程，做后台任务

        for (int i = 0; i < 10000; i ++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress(i);

            if (isCancelled()) break;
        }

        Log.i("dtt", "工作中 " + Thread.currentThread().getName());

        return 0;
    }

    @Override
    protected void onPostExecute(Integer aBoolean) {
        super.onPostExecute(aBoolean);

        // ！主线程，任务结束，做一些操作
        Log.i("dtt", "完成了 " + Thread.currentThread().getName() + " 结果 " + aBoolean);
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);

        Log.i("dtt", "取消了 结果: " + integer);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        Log.i("dtt", "just 取消了");
    }
}
