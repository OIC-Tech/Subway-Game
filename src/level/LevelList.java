package level;

public enum LevelList {
	LEVEL_01("res/level/01/fg.png",
			"res/level/01/mg.png",
			"res/level/01/bg.png",
			"res/mob/girl/",
			new float[] { 1106, 716 },
			new float[] { 1077, 700 },
			new float[] { 0, 0 },
			new float[] { 1, 15});

	public String fgLocation;
	public String mgLocation;
	public String bgLocation;
	public String girlTexture;

	public float[] fgCoord;
	public float[] mgCoord;
	public float[] bgCoord;
	
	public float[] startCoord;

	LevelList(String fgLocation,
			String mgLocation,
			String bgLocation,
			String girlTexture,
			float[] fgCoord,
			float[] mgCoord,
			float[] bgCoord,
			float[] startCoord) {

		this.fgLocation = fgLocation;
		this.mgLocation = mgLocation; 
		this.bgLocation = bgLocation;
		this.girlTexture = girlTexture;

		this.fgCoord = fgCoord;
		this.mgCoord = mgCoord;
		this.bgCoord = bgCoord;
		
		this.startCoord = startCoord;

	}

}
