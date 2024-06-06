package sourcecode.maingame.EssentialClasses;


public class Map {
    public int [][]  mapStructure = new int[17][31];
    public  Map(String mapStructureArray){

        String[] rows = mapStructureArray.split("\n");
        for(int i = 0; i< rows.length; i++){
            String[] columns = rows[i].split(",");
            for(int  j= 0; j< columns.length; j++){
                mapStructure[i][j]= Integer.parseInt(columns[j]);
            }
        }

    }

}
