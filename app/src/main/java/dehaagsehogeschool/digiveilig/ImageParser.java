package dehaagsehogeschool.digiveilig;

import android.content.Context;

public class ImageParser {

    public static int getImageBasedOnFileName(String imageName, Context context){

        int imageResource = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return imageResource;
    }
}
