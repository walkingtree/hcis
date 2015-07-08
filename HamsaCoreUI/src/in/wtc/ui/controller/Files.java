package in.wtc.ui.controller;

import org.directwebremoting.dwrp.FileUpload;

public class Files
{
    public FileUpload getFile()
    {
        return file;
    }
    public void setFile(FileUpload file)
    {
        this.file = file;
    }
    public FileUpload getImage()
    {
        return image;
    }
    public void setImage(FileUpload image)
    {
        this.image = image;
    }

    private FileUpload image;
    private FileUpload file;
}
