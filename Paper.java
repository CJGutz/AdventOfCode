public class Paper {
    
    private boolean[][] coordinates;
    private int lastXAxis;
    private int lastYAxis;

    public Paper() { 
        lastYAxis = 2000;
        lastXAxis = 2000;
        coordinates =  new boolean[lastYAxis][lastXAxis];
    }

    public boolean[][] getList() {
        boolean[][] smallerList = new boolean[lastYAxis][lastXAxis];
        for (int i = 0; i < coordinates.length; i++)
            for (int j = 0; j < coordinates[i].length; j++)
                if (coordinates[i][j])
                    smallerList[i][j] = coordinates[i][j];
        return smallerList;
    }

    public void addCoordinate(String[] point) {
        int x = Integer.parseInt(point[0].trim());
        int y = Integer.parseInt(point[1].trim());
        coordinates[y][x] = true;
    }

    public void foldXAxis(int axis) {
        lastXAxis = axis + 10;
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = axis + 1; j < coordinates[i].length; j++) {
                if (coordinates[i][j]) {
                    coordinates[i][2*axis - j] = true;
                    coordinates[i][j] = false;
                }
            }
        }
    }

    public void foldYAxis(int axis) {
        lastYAxis = axis + 10;
        for (int i = axis + 1; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[i].length; j++) {
                if (coordinates[i][j]) {
                    coordinates[2*axis - i][j] = true;
                    coordinates[i][j] = false;
                }
            }
        }
    }

    public int numOfPoints() {
        int points = 0;
        for (int i = 0; i < coordinates.length; i++)
            for (int j = 0; j < coordinates[i].length; j++)
                if (coordinates[i][j])
                    points++;
        return points;
    }    

}
