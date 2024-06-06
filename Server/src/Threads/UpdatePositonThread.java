package Threads;

import Main.Server;

public class UpdatePositonThread implements Runnable{

    private Thread thread;
    private int matchIndex;
    private int playerIndex;
    private int x;
    private int y;
    private int angel;
    private int isFire;

    private int[][] mapStructure = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    
    public UpdatePositonThread(int matchIndex, int playerIndex, int x, int y,int angel,int isFire){

        this.matchIndex = matchIndex;
        this.playerIndex = playerIndex;
        this.x = x;
        this.y = y;
        this.angel = angel;
        this.isFire = isFire;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){
       
        Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).setPosX(x);
        Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).setPosY(y);
        Server.randomMatchs.get(matchIndex).getMatchPlayers().get(playerIndex).setAngel(angel);

      
    }

    public boolean isGotHit(double angel, double fX, double fY, double tX, double tY){

        fX = fX+13;
        fY = fY+17;
        angel = Math.toRadians(90-angel);

        if(!isBulletBlocked(angel,fX,fY,tX,tY)){
            if(isGetIntersect(angel,fX,fY,tX,tY,35)) {
                if ((Math.toDegrees(angel) >= 0 && Math.toDegrees(angel) <= 90 && fX <= tX && fY >= tY) || (Math.toDegrees(angel) >= 90 && Math.toDegrees(angel) <= 180 && fX >= tX && fY >= tY) || (Math.toDegrees(angel) >= 180 && Math.toDegrees(angel) <= 270 && fX >= tX && fY <= tY) || (Math.toDegrees(angel) <= 0 && Math.toDegrees(angel) >= -90 && fX <= tX && fY <= tY)) {

                    return  true;
                }
            }
        }

        return  false;
    }
    public boolean isBulletBlocked(double angel,double startingX,double startingY,double endingX,double endingY ){
        {
            int startingPositionBlockRow = (int) (startingY / 40.0);
            int startingPositionBlockColumn = (int) (startingX / 40.0);
            int endingPositionBlockRow = (int) (endingY / 40.0);
            int endingPositionBlockColumn = (int) (endingX / 40.0);

            int rowStart;
            int rowEnd;

            if (startingPositionBlockRow > endingPositionBlockRow) {
                rowStart = endingPositionBlockRow;
                rowEnd = startingPositionBlockRow;
            }
            else {
                rowStart = startingPositionBlockRow;
                rowEnd = endingPositionBlockRow;
            }

            int columnStart;
            int columnEnd;

            if (startingPositionBlockColumn > endingPositionBlockColumn) {
                columnStart = endingPositionBlockColumn;
                columnEnd = startingPositionBlockColumn;
            } else {
                columnStart = startingPositionBlockColumn;
                columnEnd = endingPositionBlockColumn;
            }


            if (startingPositionBlockRow == endingPositionBlockRow && startingPositionBlockColumn != endingPositionBlockColumn) {

                for(int i = columnStart+1; i<columnEnd ; i++){
                    if(mapStructure[rowStart][i] == 1){
                        if(isGetIntersect(angel,startingX,startingY,i*40, rowStart*40,40)){
                            return true;
                        }
                    }
                }

            }
            else if (startingPositionBlockColumn == endingPositionBlockColumn) {

                for(int i = rowStart+1; i<rowEnd ; i++){
                    if(mapStructure[i][columnStart] == 1){
                        if(isGetIntersect(angel,startingX,startingY,columnStart*40, i*40,40)){
                            return true;
                        }
                    }
                }

            }
            else {

                for(int i = rowStart; i<= rowEnd; i++){
                    for(int j = columnStart ; j<= columnEnd; j++){
                        if(!((startingPositionBlockColumn == j && startingPositionBlockRow == i) || (endingPositionBlockColumn == j && endingPositionBlockRow ==i)) && mapStructure[i][j]==1){

                            if(isGetIntersect(angel,startingX,startingY,j*40,i*40,40) ){
                                return  true;
                            }

                        }
                    }
                }

            }
        }
        return false;
    }

    public boolean isGetIntersect(double angel,double fX,double fY,double tX,double tY,int edge) {

        double m = Math.tan(-angel);
        double c = fY - (m*fX);

        double[] interceptsX = new double[4];
        double[] interceptsY = new double[4];

        interceptsX[0]= tX;
        interceptsY[0] = m*interceptsX[0] + c;

        interceptsX[1] = tX+edge;
        interceptsY[1] = (m*interceptsX[1]) + c;

        interceptsY[2] = tY;
        interceptsX[2] = (interceptsY[2]-c)/m;

        interceptsY[3] = tY+edge;
        interceptsX[3] = (interceptsY[3]-c)/m;

        for (int i = 0; i < 4; i++) {

            if (interceptsX[i] >= tX && interceptsX[i] <= (tX + edge) && interceptsY[i] >= tY && interceptsY[i] <= (tY + edge)){

                return  true;

            }
        }

        return  false;

    }
    



}
