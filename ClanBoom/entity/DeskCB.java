package ClanBoom.entity;

public class DeskCB {
	private String DeskRecord;//餐桌标号
	private int DeskState;//包间1,大厅0
	private int DeskUse;//占用1,空闲0
	private int DeskHigh;//楼层
	public int getDeskHigh() {
		return DeskHigh;
	}
	public void setDeskHigh(int deskHigh) {
		DeskHigh = deskHigh;
	}
	public int getDeskUse() {
		return DeskUse;
	}
	public void setDeskUse(int deskUse) {
		DeskUse = deskUse;
	}
	public String getDeskRecord() {
		return DeskRecord;
	}
	public void setDeskRecord(String deskRecord) {
		DeskRecord = deskRecord;
	}
	public int getDeskState() {
		return DeskState;
	}
	public void setDeskState(int deskState) {
		DeskState = deskState;
	}
	public DeskCB(){
		
	}
	

	public DeskCB(int DeskState,String DeskRecord,int DeskHigh,int DeskUse){
		this.DeskRecord=DeskRecord;
		this.DeskState=DeskState;
		this.DeskHigh=DeskHigh;
		this.DeskUse=DeskUse;
	}
	public void show(){
		if(this.DeskState==1){
			if(this.DeskUse==1){
				System.out.println(this.DeskRecord+"\t"+this.DeskHigh+"楼"+"\t"+"占用");
			}else{
				System.out.println(this.DeskRecord+"\t"+this.DeskHigh+"楼"+"\t"+"空闲");
			}
		}else{
			if(this.DeskUse==1){
				System.out.println(this.DeskRecord+"\t"+"1楼"+"\t"+"占用");
			}else{
				System.out.println(this.DeskRecord+"\t"+"1楼"+"\t"+"空闲");
			}
		}
		
	}
}
