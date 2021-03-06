# Отчётные документы по итогам тестирования
## Краткое описание
* В ходе автоматизации тестирования были реализованы позитивные и негативные сценарии заполнения тестовой страницы.
*  Реализована поддержка двух БД - MySQL и PostgreSQL.
*  Для написания и развертывания тестовых сценариев были использованы:
  Intellij IDEA
  Java 11
  Docker 
  Junit 5
  Selenide
  Lombok для упрощения работы с классами и Faker для генерации данных
  Allure для подготовки отчетности
  MySql и PostgreSQL как заявленные поддерживаемые БД
  Приложение было протестировано по всем запланированным позитивным и негативным сценариям.
## Количество тест кейсов
  Была проведена автоматизация 12 тест-кейсов
  * 4 позитивных
  * 8 негативных
  
По результату автоматизированных тестов обнаружено 8 багов

По всем багам заведены [Issues](https://github.com/podnebessssni/Diplom/issues)

Итого процент успешных автоматизированных показателей составил 66,66%

![тесты](https://github.com/podnebessssni/Diplom/blob/master/documentation/Screenshot/report.png)

## Общие рекомендации
* Создать спецификацию для данного приложения
* В условиях отсутствия спецификации, требуется уточнить алгоритм заполнения БД в случае попытки оплаты/оформления кредита с карты со статусом DECLINED:
    * должна ли заполняться таблица order_entity данными? 
    * если запись в таблице order_entity создается, должен ли такой карте приписываться payment_id при попытке оплаты?
    * если запись в таблице order_entity создается, должен ли такой карте приписываться credit_id при попытке взять кредит?    
* Во всех случаях, когда поля формы остаются не заполненными, реализовать появление предупреждающей надписи "Поле обязательно для заполнения" вместо "Неверный формат".
* Уточнить требования к полю "Владелец", в случае, если данные должны соответствовать данным на карте, реализовать возможность вводить данные только латинскими буквами
* Возможно, была бы удобной опция сохранять данные в анкете при переключении между функциями "Купить" и "Купить в кредит". Сейчас анкета очищается каждый раз. 