package com.address.list.frame.main.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter
{

	public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }

        int index = f.getName().lastIndexOf(".");
        String extension = f.getName().substring(index + 1).toLowerCase();
        if (extension != null)
        {
            if (extension.equals("gif") || extension.equals("jpeg")
                    || extension.equals("jpg") || extension.equals("png"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public String getDescription()
    {
        return "图片文件(*.jpg, *.jpeg, *.gif, *.png)";
    }

}
