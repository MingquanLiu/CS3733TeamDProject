package edu.wpi.cs3733.programname.commondata;

public class TitleData {
    private String acronym;
    private String titleName;

    public TitleData(String acronym, String titleName) {
        this.acronym = acronym;
        this.titleName = titleName;
    }

    public String getAcronym() {return this.acronym;}

    public void setAcronym(String acronym) {this.acronym = acronym;}

    public String getTitleName() {return this.titleName;}

    public void setTitleName(String titleName) {this.titleName = titleName;}
}
