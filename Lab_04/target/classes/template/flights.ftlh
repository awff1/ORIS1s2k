<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Онлайн табло</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; margin: 20px; }
        .title { text-align: center; margin-bottom: 20px; }
        .form { margin-bottom: 20px; display: flex; justify-content: center; gap: 15px; }
        .form-select, .form-button { padding: 5px 10px; font-size: 14px; }
        .tablo { display: flex; flex-direction: column; border: 1px solid #ccc; border-radius: 6px; overflow: hidden; }
        .tablo-row { display: flex; flex-direction: row; border-bottom: 1px solid #eee; }
        .tablo-head { font-weight: bold; background-color: #f5f5f5; }
        .tablo-row > div { padding: 8px 12px; flex: 1; text-align: center; }
        .tablo-row--no-flights { text-align: center; padding: 15px; font-style: italic; color: #555; }
    </style>
</head>
<body>

<h1 class="title">Онлайн табло</h1>

<form class="form" method="get" action="/lab_04/flights">
    <label class="form-label">Аэропорт:
        <select class="form-select" name="airport">
            <#list airports as airport>
                <option value="${airport.airportCode}" <#if airport.airportCode == selectedAirport>selected</#if>>
                    ${airport.airportName} (${airport.airportCode})
                </option>
            </#list>
        </select>
    </label>
    <label class="form-label">Тип:
        <select class="form-select" name="type">
            <option value="departures" <#if selectedType == "departures">selected</#if>>
                Вылет</option>
            <option value="arrivals" <#if selectedType == "arrivals">selected</#if>>Прилет</option>
        </select>
    </label>
    <button class="form-button" type="submit">Показать</button>
</form>

<div class="tablo">
    <div class="tablo-row tablo-head">
        <div class="row-item">Рейс</div>
        <div class="row-item">Время отправления</div>
        <div class="row-item">Время прилета</div>
        <div class="row-item">Состояние</div>
        <div class="row-item">Аэропорт</div>
    </div>

    <#if flights?? && flights?size gt 0>
        <#list flights as flight>
            <div class="tablo-row">
                <div class="row-item">${flight.flightId}</div>
                <div class="row-item">${flight.departureTime}</div>
                <div class="row-item">${flight.arrivalTime}</div>
                <div class="row-item">${flight.status}</div>
                <div class="row-item">${flight.airportName}</div>
            </div>
        </#list>
    <#else>
        <div class="tablo-row tablo-row--no-flights">
            <div class="row-item row-item--no-flights" colspan="5">Рейсов нет</div>
        </div>
    </#if>
</div>

</body>
</html>