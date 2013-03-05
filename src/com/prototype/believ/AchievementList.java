package com.prototype.believ;


import android.os.Parcel;
import android.os.Parcelable;

public class AchievementList implements Parcelable{
	private int id;
	private String type;
	private int current;
	private int high;
	private int credits;
	private String measure;

	// Empty constructor
	public AchievementList() {

	}

	// Constructor
	public AchievementList(int id, String type, int current, int high, int credits, String measure) {
		this.id = id;
		this.type = type;
		this.current = current;
		this.high = high;
		this.credits = credits;
		this.measure = measure;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return type;
	}
	
	/***** Parcelable methods to be able to pass the list to activity ***********/
	//constructor Parcelable
	 public AchievementList(Parcel in) {
         super(); 
         readFromParcel(in);
     }

     public static final Parcelable.Creator<AchievementList> CREATOR = new Parcelable.Creator<AchievementList>() {
         public AchievementList createFromParcel(Parcel in) {
             return new AchievementList(in);
         }

         public AchievementList[] newArray(int size) {

             return new AchievementList[size];
         }

     };

     public void readFromParcel(Parcel in) {
		this.id = in.readInt();
 		this.type = in.readString();
 		this.current = in.readInt();
 		this.high = in.readInt();
 		this.credits = in.readInt();
 		this.measure = in.readString();

     }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		 dest.writeInt( this.id);
		 dest.writeString(this.type);
		 dest.writeInt(this.current);
		 dest.writeInt(this.high);
		 dest.writeInt(this.credits);
		 dest.writeString(this.measure);
		
	}
}