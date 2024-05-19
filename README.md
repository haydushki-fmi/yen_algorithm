# Алгоритъм на Yen

Проект за курса по Мрежово програмиране във ФМИ на СУ.

## Изграждане и използване на приложението

### Изграждане

За изграждане на приложението са нужни JDK и Maven. При разработката е използван JDK 22, но и по-стари версии би трябвало да вършат работа -- може да се промени в `pom.xml`.

### Използване

Първо се стартира клас `server.Server`, като в него има статично поле за номер на порт, който може да бъде променян при нужда. По подразбиране е *1234*.

След това може да се стартират програми, изпълняващи `client.Client` класа. (Забележка: Ако портът на сървъра е променен, това трябва да бъде отразено и в класа за клиента.) Във всяка от тях от потребителя се очаква да въведе:

- На първия ред: **n** -- *брой ребра* в графа, *начален връх*, *краен връх* и **k**, където **k** е броят на най-къси пътища, които да бъдат намерени;
- На следващите **n** на брой редове: име на връх *начало*, име на връх *край*, *тегло* на реброто между съответните два върха.

За разделители се използват интервали.

След подаване на нужната информация, сървърът изпълнява алгоритъма на Йен и връща отговор:

- Ако няма път между двата върха се връща „EMPTY PATH“
- В противен случай се връщат първите **k** най-къси пътища, подредени в списък.

Всеки път е изразен чрез списък от върхове и теглото му.

## Основни моменти при реализацията

При реализирането на проекта отделните негови части са изградени в следните стъпки:

1. Създаване на класове за представяне на граф в паметта -- класове за връх, ребро, граф и път в него;
2. Имплементиране на алгоритъма на Dijkstra за най-къс път между два върха -- предоставяне на метод за намиране на най-къс път в даден граф;
3. Модификация на Дейкстра, така че да „игнорира“ определени ребра от графа -- идеята за игнорирането е да се избегне променяне на даденият граф;
4. Имплементиране на алгоритъма на Yen за k най-къси пътища между два върха -- реализиране на метод за намиране на k на брой най-къси пътища от даден начален връх до даден краен връх в граф;
5. Имплементиране на клас `Server` в приложението -- създава socket и за всеки клиент се създава отделна нишка, която да го обслужва
6. Имплементиране на клас `Client` -- прост потребителски интерфейс, който изпраща информация към сървъра и показва неговия отговор.

За алгоритмичната част от проекта са реализирани базови тестове, които да проверят коректността.
