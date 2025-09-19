class Spreadsheet {
    private int[][] grid; // rows x 26
    private int rows;

    public Spreadsheet(int rows) {
        this.rows = rows;
        grid = new int[rows][26]; // all initialized to 0
    }

    public void setCell(String cell, int value) {
        int[] pos = parseCell(cell);
        grid[pos[0]][pos[1]] = value;
    }

    public void resetCell(String cell) {
        int[] pos = parseCell(cell);
        grid[pos[0]][pos[1]] = 0;
    }

    public int getValue(String formula) {
        // formula like "=A1+5" or "=10+20"
        formula = formula.substring(1); // remove '='
        String[] parts = formula.split("\\+");

        return getOperandValue(parts[0]) + getOperandValue(parts[1]);
    }

    // Helper to get value of operand (cell or number)
    private int getOperandValue(String token) {
        if (Character.isLetter(token.charAt(0))) {
            int[] pos = parseCell(token);
            return grid[pos[0]][pos[1]];
        } else {
            return Integer.parseInt(token);
        }
    }

    // Convert "A1" -> [row, col]
    private int[] parseCell(String cell) {
        char colChar = cell.charAt(0);
        int col = colChar - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1; // convert to 0-index
        return new int[]{row, col};
    }
}
