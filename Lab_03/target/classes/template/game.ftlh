<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Крестики-нолики</title>
        <meta name="description" content="Крестики-нолики">
        <link rel="stylesheet" href="/lab_03/css/styles.css">
    </head>

    <body>
    <section id="tic-tac-toe" class="tic-tac-toe">
        <h1 class="tic-tac-toe__title">Крестики-нолики</h1>

        <table class="tic-tac-toe__table">
            <#list table as r>
                <tr class="tic-tac-toe__table-row">
                    <td class="tic-tac-toe__table-cell">
                        <img class="tic-tac-toe__table-image" src="/lab_03/images/${r.f}" />
                    </td>
                    <td class="tic-tac-toe__table-cell">
                        <img class="tic-tac-toe__table-image" src="/lab_03/images/${r.s}" />
                    </td>
                    <td class="tic-tac-toe__table-cell">
                        <img class="tic-tac-toe__table-image" src="/lab_03/images/${r.t}" />
                    </td>
                </tr>
            </#list>
        </table>

        <#if !gameOver?? || !gameOver>
            <form class="tic-tac-toe__form" method='post' action='/lab_03/game'>
                <input class="tic-tac-toe__input" type="hidden" name="uid" value="${uid}">
                <div class="tic-tac-toe__row">
                    <label class="tic-tac-toe__label">Номер строки:</label>
                    <input class="tic-tac-toe__input" type='text' name='row'/>
                </div>
                <div class="tic-tac-toe__row">
                    <label class="tic-tac-toe__label">Номер столбца:</label>
                    <input class="tic-tac-toe__input" type='text' name='column' />
                </div>

                <div class="tic-tac-toe__row">
                    <input class="tic-tac-toe__input" type='submit' value="Отправить">
                </div>

            </form>
        <#else>
            <form class="tic-tac-toe__form" method="get" action="/lab_03/game">
                <input class="tic-tac-toe__input" type="submit" value="Играть снова">
            </form>
        </#if>

        <div class="tic-tac-toe__message" >
            <#if message??>
                ${message}
            </#if>
        </div>

    </section>
    </body>
</html>