package com.android.image.util;

import java.io.File;

import android.content.Context;

public class FileCache {

    private File cacheDir;

    public FileCache(Context context) {
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "LazyList");
        else
            cacheDir = context.getCacheDir();
        if (!cacheDir.exists())
            cacheDir.mkdirs();
        
        /*ANDROID , IPHONE , PHONGAP APP Price and Time quotation.

        Here , every TuesDay we will give update for your project status.
        ==============================================================================================================================================
        Time Quotation : Maximum 1 Month ... most probably we provide 2 week

        Static APP ==> 7-8 k               (Negation-able  2 K)

        Any change request ==> 2 K
        ==============================================================================================================================================
        Maximum 4 month ... (We have provide time quotation according project requirement but we have to use maximum 4 month for this type of project)

        Web Service Base APP  ==> per day 7 $  not per hour 

        Any change request ==> 4 K
        ==============================================================================================================================================
        Maximum 4 month ... (We have provide time quotation according project requirement but we have to use maximum 4 month for this type of project)

        GOOGLE MAP OR GPS TRACKING Base APP  ==> per day 9 $ not per hour 

        Any change request ==> 4 K
        ==============================================================================================================================================
        Maximum 4 month ... (We have provide time quotation according project requirement but we have to use maximum 4 month for this type of project)

        ANY CHAT APP or Any Chat module ADD ==> per day 8 $ not per hour 

        Any change request ==> 4 K
        ==============================================================================================================================================
        ==============================================================================================================================================
        
*/
    }

    public File getFile(String url) {
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename = String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }

}