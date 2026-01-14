package ru.itis.dis403.lab_03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/game")
public class GamePage extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GamePage.class);

    private final Map<String, GameState> gamers = new HashMap<>();

    private final GameProcess gameProcess = new GameProcess();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug(request.getServletPath());

        String uid = UUID.randomUUID().toString();
        GameState gameState = new GameState();
        gamers.put(uid, gameState);

        request.setAttribute("table", gameState.getTable());
        request.setAttribute("uid", uid);

        request.getRequestDispatcher("/game.ftlh").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String row = request.getParameter("row");
        String column = request.getParameter("column");
        String uid = request.getParameter("uid");

        logger.debug("uid " + uid + ", row " + row + ", column " + column);

        GameState gameState = gamers.get(uid);

        if (gameState == null) {
            response.sendRedirect(request.getContextPath() + "/game");
            return;
        }

        List<Row> table = gameState.getTable();

        //проверка корректности введенных данных
        if (!row.matches("[1-3]") || !column.matches("[1-3]")) {

            request.setAttribute("message", "Ошибка ввода. Введите число от 1 до 3");

        } else {

            Row r = table.get(Integer.parseInt(row) - 1);
            int c = Integer.parseInt(column) - 1;

            if (!r.getCell(c).equals("empty.jpg")) { //проверка, что ячейка не занята

                request.setAttribute("message", "Эта ячейка занята. Выберите другую.");

            } else { //не занята - делаем ход

                r.setCell(c, "x.png"); //ставим крестик

                if (gameProcess.checkWin(table, "x.png")) { //проверяем не выиграли ли
                    request.setAttribute("message", "Вы выиграли!");
                    gameState.setGameOver(true);
                }

                else if (gameProcess.checkDraw(table)) { //проверяем на ничью
                    request.setAttribute("message", "Ничья!");
                    gameState.setGameOver(true);
                }

                else { //если не выиграли и не ничья - компьютер делает ход

                    gameProcess.makeMove(table); //компьютер ставит нолик

                    if (gameProcess.checkWin(table, "o.png")) { //проверяем не победил ли компьютер
                        request.setAttribute("message", "Вы проиграли!");
                        gameState.setGameOver(true);
                    }

                    else if (gameProcess.checkDraw(table)) { //проверяем на ничью
                        request.setAttribute("message", "Ничья!");
                        gameState.setGameOver(true);
                    }
                }
            }
        }

        request.setAttribute("table", table);
        request.setAttribute("uid", uid);
        request.setAttribute("gameOver", gameState.getGameOver());

        request.getRequestDispatcher("/game.ftlh").forward(request, response);
    }
}