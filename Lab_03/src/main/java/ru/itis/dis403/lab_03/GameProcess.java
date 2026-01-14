package ru.itis.dis403.lab_03;

import java.util.List;
import java.util.Random;

public class GameProcess {
    private final Random random = new Random();

    public void makeMove(List<Row> table) {
        if (bestMove(table, "o.png")) return; //сначала пытаемся выиграть
        if (bestMove(table, "x.png")) return; //если не получилось выиграть - пытаемся не дать игроку выиграть
        randomMove(table); //если вообще ничего не получилось - делаем рандомный ход
    }

    private boolean bestMove(List<Row> table, String mark) {
        for (int r =  0; r < table.size(); r++) {
            Row row = table.get(r);
            for (int c = 0; c < table.size(); c++) {
                if (row.getCell(c).equals("empty.jpg")) {
                    row.setCell(c, mark);
                    if (checkWin(table, mark)) {
                        if (mark.equals("o.png")) {
                            return true;
                        }
                        row.setCell(c, "o.png");
                        return true;
                    }
                    row.setCell(c, "empty.jpg");
                }
            }
        }
        return false;
    }

    private void randomMove(List<Row> table) {
        while (true) {
            int r = random.nextInt(3);
            int c = random.nextInt(3);
            Row row = table.get(r);
            if (row.getCell(c).equals("empty.jpg")) {
                row.setCell(c, "o.png");
                break;
            }
        }
    }

    public boolean checkWin(List<Row> table, String mark) {

        //строки
        for (Row row : table) {
            if (row.getF().equals(mark) && row.getS().equals(mark) && row.getT().equals(mark)) {
                return true;
            }
        }

        //столбцы
        for (int col = 0; col < table.size(); col++) {
             if (table.get(0).getCell(col).equals(mark) && table.get(1).getCell(col).equals(mark) && table.get(2).getCell(col).equals(mark)) {
                 return true;
             }
        }

        //диагонали
        return (table.get(0).getF().equals(mark) && table.get(1).getS().equals(mark) && table.get(2).getT().equals(mark)
                || table.get(0).getT().equals(mark) && table.get(1).getS().equals(mark) && table.get(2).getF().equals(mark));
    }

    public boolean checkDraw(List<Row> table) {
        for (Row row : table) {
            for (int col = 0; col < table.size(); col++) {
                if (row.getCell(col).equals("empty.jpg")) return false;
            }
        }
        return true;
    }

}
