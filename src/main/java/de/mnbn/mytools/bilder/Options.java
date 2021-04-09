package de.mnbn.mytools.bilder;

/**
 * @author schrader
 */
public class Options {

    private boolean checkOnly = true;

    private String directory;

    public boolean isCheckOnly() {
        return checkOnly;
    }

    public void setCheckOnly(boolean checkOnly) {
        this.checkOnly = checkOnly;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
